
package np.ua.awis_mobile.bluetoothapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import np.ua.awis_mobile.posinteract.CmpDriver;
import np.ua.awis_mobile.posinteract.lib.CommandResult;
import np.ua.awis_mobile.posinteract.lib.GoodInfo;
import np.ua.awis_mobile.posinteract.lib.ResponseCallback;
import np.ua.awis_mobile.posinteract.lib.SaleGoodInfo;
import np.ua.awis_mobile.posinteract.lib.command.CommandGetCounters;
import np.ua.awis_mobile.posinteract.lib.command.CommandPrintAddingWithdrawalAmounts;
import np.ua.awis_mobile.posinteract.lib.command.CommandPrintCancellationOpenCheck;
import np.ua.awis_mobile.posinteract.lib.command.CommandPrintPaymentCashCheck;
import np.ua.awis_mobile.posinteract.lib.command.CommandPrintReturnCashCheck;
import np.ua.awis_mobile.posinteract.lib.command.CommandPrintZReport;
import np.ua.awis_mobile.posinteract.lib.command.CommandPrintZeroCheck;

import static java.util.Collections.singletonList;

/**
 * Example of use
 */
public class CmpExampleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = CmpExampleActivity.class.getSimpleName();
    private static final int REQUEST_ENABLE_BT = 123;

    //Test data
    private static final GoodInfo TEST_GOOD_INFO = new GoodInfo(1, "Item name", 1);
    private static final String TEST_OPERATOR_NAME = "operator1";
    private static final String TEST_CASH_BOX_NUMBER = "1";
    private static final float TEST_PAYMENT_SUM = 333F;
    private static final SaleGoodInfo TEST_SALE_GOOD_INFO = new SaleGoodInfo(
            TEST_GOOD_INFO.getGoodCode(), 1, TEST_PAYMENT_SUM, "Comment"
    );

    private static final String DEVICE_MAC = "device_mac";
    private final CustomHandler mHandler = new CustomHandler(this);
    private BluetoothAdapter mBluetoothAdapter;
    private BlueToothService mBlueToothPrintService;
    private CmpDriver mDriver;
    private String mMacDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_init_connect).setOnClickListener(this);
        findViewById(R.id.btn_init_disconnect).setOnClickListener(this);
        findViewById(R.id.btn_print_zero_check).setOnClickListener(this);
        findViewById(R.id.btn_print_x_report).setOnClickListener(this);
        findViewById(R.id.btn_print_z_report).setOnClickListener(this);
        findViewById(R.id.btn_payment).setOnClickListener(this);
        findViewById(R.id.btn_money_back).setOnClickListener(this);
        findViewById(R.id.btn_service_back).setOnClickListener(this);
        findViewById(R.id.btn_print_cancellation).setOnClickListener(this);

        mBlueToothPrintService = new BlueToothService(mHandler);
        mDriver = new CmpDriver(mBlueToothPrintService);
        mMacDevice = (savedInstanceState != null)
                ? savedInstanceState.getString(DEVICE_MAC, "")
                : readAddressFromStorage();

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this,
                    "Bluetooth is not supported on this hardware platform",
                    Toast.LENGTH_SHORT).show();
            Log.e(TAG, "initBlueTooth: " + "no bluetooth");
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            initConnect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }

    private void initConnect() {
        if (mMacDevice.isEmpty()) {
            enablePrinterOperation(false);
            startActivityForResult(new Intent(this, DeviceListActivity.class),
                    DeviceListActivity.REQUEST_CONNECT_DEVICE
            );
        } else {
            connectDevice();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!mMacDevice.isEmpty()) {
            outState.putString(DEVICE_MAC, mMacDevice);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_connect_device) {
            Intent serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, DeviceListActivity.REQUEST_CONNECT_DEVICE);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DeviceListActivity.REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    mMacDevice = data.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    writeAddressToStorage(mMacDevice);
                    initConnect();
                }
                break;
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up connection
                    initConnect();
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, R.string.msg_bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_init_connect) {
            initConnect();
        } else if (i == R.id.btn_init_disconnect) {
            stopService();
        } else if (i == R.id.btn_print_zero_check) {
            printZeroCheck();
        } else if (i == R.id.btn_print_x_report) {
            printXreport();
        } else if (i == R.id.btn_print_z_report) {
            printZreport();
        } else if (i == R.id.btn_payment) {
            printPayment();
        } else if (i == R.id.btn_money_back) {
            printReturnCashCheck();
        } else if (i == R.id.btn_service_back) {
            printAddingWithdrawalAmounts();
        } else if (i == R.id.btn_print_cancellation) {
            printCancellation();
        }
    }

    private void printAddingWithdrawalAmounts() {
        mDriver.doRequest(new CommandPrintAddingWithdrawalAmounts(Integer.valueOf(TEST_CASH_BOX_NUMBER), 20f, ""));
    }

    private void printReturnCashCheck() {
        List<SaleGoodInfo> saleGoodInfoList = singletonList(TEST_SALE_GOOD_INFO);
        mDriver.doRequest(new CommandPrintReturnCashCheck(
                Integer.valueOf(TEST_CASH_BOX_NUMBER),
                TEST_PAYMENT_SUM,
                new ArrayList<>(saleGoodInfoList)
        ));
    }

    private void printPayment() {
        List<SaleGoodInfo> saleGoodInfoList = singletonList(TEST_SALE_GOOD_INFO);
        mDriver.doRequest(new CommandPrintPaymentCashCheck(
                Integer.valueOf(TEST_CASH_BOX_NUMBER),
                TEST_PAYMENT_SUM,
                new ArrayList<>(saleGoodInfoList)
        ));
    }

    private void printZreport() {
        mDriver.doRequest(new CommandPrintZReport());
    }

    private void printXreport() {
        mDriver.doRequest(new CommandGetCounters(6));
    }

    private void printZeroCheck() {
        mDriver.addResponseCallback(new ResponseCallback() {
            @Override
            public void responseObject(CommandResult result) {
                Log.i(TAG, "responseObject: " + result.toString());
            }
        });

        List<GoodInfo> objects = singletonList(TEST_GOOD_INFO);
        ArrayList<GoodInfo> nomenclatures = new ArrayList<>(objects);
        mDriver.doRequest(new CommandPrintZeroCheck(
                new SimpleDateFormat("ddMMyy", Locale.US).format(new Date()),
                new SimpleDateFormat("HHmm", Locale.US).format(new Date()),
                TEST_OPERATOR_NAME,
                TEST_CASH_BOX_NUMBER,
                nomenclatures));
    }

    private void printCancellation() {
        mDriver.doRequest(new CommandPrintCancellationOpenCheck());
    }

    private void stopService() {
        if (mBlueToothPrintService != null) {
            mBlueToothPrintService.stop();
        }
    }

    private void connectDevice() {
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mMacDevice);
        // Attempt to connect to the device
        mBlueToothPrintService.connect(device);
    }

    private String readAddressFromStorage() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(DEVICE_MAC, "");
    }

    private void enablePrinterOperation(boolean isEnable) {
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ll_printer_operations);

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            child.setEnabled(isEnable);
        }
    }

    private void writeAddressToStorage(String address) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(DEVICE_MAC, address);
        editor.apply();
    }

    /**
     * Show device messages on the UI
     *
     * @param message message to show
     */
    private void showDeviceStatus(@StringRes int message) {
        Snackbar.make(findViewById(R.id.cl_main), message,
                Snackbar.LENGTH_SHORT)
                .show();
    }


    private static class CustomHandler extends Handler {
        private final WeakReference<CmpExampleActivity> mHandlerWeakReference;

        CustomHandler(CmpExampleActivity activity) {
            mHandlerWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            CmpExampleActivity activity = mHandlerWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case BlueToothService.MESSAGE_STATE_CHANGE:
                        switch (msg.arg1) {
                            case BlueToothService.STATE_CONNECTED:
                                activity.showDeviceStatus(R.string.msg_connected);
                                activity.enablePrinterOperation(true);
                                break;
                            case BlueToothService.STATE_CONNECTING:
                                activity.showDeviceStatus(R.string.msg_connecting);
                                break;
                            case BlueToothService.STATE_LISTEN:
                            case BlueToothService.STATE_NONE:
                                activity.showDeviceStatus(R.string.msg_no_connect);
                                activity.enablePrinterOperation(false);
                                break;
                            default:
                                break;
                        }
                        break;
                    case BlueToothService.MESSAGE_WRITE:
                        byte[] writeBuf = (byte[]) msg.obj;
                        // construct a string from the buffer
                        String writeMessage = new String(writeBuf);
                        Log.i(TAG, "handleMessage: MESSAGE_WRITE: " + writeMessage);
                        break;
                    case BlueToothService.MESSAGE_READ:
                        byte[] readBuf = (byte[]) msg.obj;
                        // construct a string from the valid bytes in the buffer
                        String readMessage = new String(readBuf, 0, msg.arg1);
                        Log.i(TAG, "handleMessage: MESSAGE_READ: " + readMessage);
                        break;
                    case BlueToothService.MESSAGE_DEVICE_NAME:
                        // save the connected device's name
                        String mConnectedDeviceName = msg.getData().getString(BlueToothService.KEY_DEVICE_NAME);
                        Toast.makeText(activity,
                                activity.getString(R.string.msg_connected_to_device,
                                        mConnectedDeviceName), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }
    }


}
