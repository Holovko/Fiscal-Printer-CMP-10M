package np.ua.awis_mobile.posinteract;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import np.ua.awis_mobile.posinteract.lib.CommandResult;
import np.ua.awis_mobile.posinteract.lib.Constants;
import np.ua.awis_mobile.posinteract.lib.DeviceInteraction;
import np.ua.awis_mobile.posinteract.lib.DriverInteraction;
import np.ua.awis_mobile.posinteract.lib.PrinterReaderPipe;
import np.ua.awis_mobile.posinteract.lib.ResponseCallback;
import np.ua.awis_mobile.posinteract.lib.command.Command;
import np.ua.awis_mobile.posinteract.lib.utils.LogResult;
import np.ua.awis_mobile.posinteract.lib.utils.Result;

import static np.ua.awis_mobile.posinteract.lib.utils.ByteUtils.addByteToEndArr;
import static np.ua.awis_mobile.posinteract.lib.utils.ByteUtils.unsignedToBytes;


/**
 * Class for interaction with printer Datecs CMP-10
 */
public class CmpDriver implements DriverInteraction, PrinterReaderPipe {
    private static final String TAG = "CmpDriver";
    private DeviceInteraction mDeviceInteraction;
    private ResponseCallback mCallBack;
    private Command mPrinterCommand;
    private List<LogResult> mResultsLog;
    private boolean mSingleOperation;
    //State variables
    private volatile int mCurrentNumber;
    private volatile int mCurrentIndex;
    private volatile boolean mNeedNewIndex;
    private LogResult mLogResult;

    /**
     * @param deviceInteraction read and write listeners
     */
    public CmpDriver(DeviceInteraction deviceInteraction) {
        mDeviceInteraction = deviceInteraction;
        deviceInteraction.setResponseListener(this);
    }

    @Override
    public void doRequest(Command printerCommand) {
        setInitialValues(printerCommand);
        doWork();
    }

    private void setInitialValues(Command printerCommand) {
        mPrinterCommand = printerCommand;
        mCurrentIndex = 0;
        mNeedNewIndex = true;
        mResultsLog = new ArrayList<>();
    }

    private void doWork() {
        Command commandToDo = mPrinterCommand.getCommandList().get(mCurrentIndex);
        mDeviceInteraction.write(getWriteData(commandToDo, true));
        mLogResult = new LogResult(commandToDo.getClass().getSimpleName());
    }

    @Override
    public void response(byte[] buffer) {
        if (buffer.length != 0) {
            try {
                CommandResult result = parseResponse(buffer);
                handleResult(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleResult(CommandResult result) {
        logResult(result);
        if (result.getCommandResult() == Result.NO_ERRORS) {
            mCurrentIndex += 1;
            if (mPrinterCommand.getCommandList().size() > mCurrentIndex) {
                doWork();
            } else {
                Log.i(TAG, "Result log: ");
                for (LogResult logResult : mResultsLog) {
                    Log.i(TAG, logResult.getResultMessage()
                            + ": " + logResult.getCommandName()
                            + "=" + logResult.getResponseResult());
                }
            }
        }
        mCallBack.responseObject(result);
    }

    private void logResult(CommandResult result) {
        Iterator<Map.Entry<String, String>> iterator = result.getResponseData().entrySet().iterator();
        if (iterator.hasNext()) {
            mLogResult.setResponseResult(iterator.next().getValue());
            mLogResult.setResultMessage(result.getCommandResult().getMessage());
            mResultsLog.add(mLogResult);
        }
    }

    @Override
    public void addResponseCallback(ResponseCallback callback) {
        mCallBack = callback;
    }

    private CommandResult parseResponse(byte[] readBytes) throws Exception {
        HashMap<String, String> printerData = new HashMap<>();

        if (readBytes[0] == Constants.ACK[0]) {
            readBytes = Arrays.copyOfRange(readBytes, 1, readBytes.length);
        }
        if (readBytes[0] == Constants.SYN[0]) {
            for (int i = 0; i < readBytes.length; i++) {
                if (readBytes[i] != Constants.SYN[0]) {
                    readBytes = Arrays.copyOfRange(readBytes, i, readBytes.length);
                    break;
                }
            }
        }
        if (readBytes[0] != Constants.SOH[0]) {
            return null;
        }
        String sErrorCode = new StringBuilder()
                .append((char) (readBytes[4] & 0xFF))
                .append((char) (readBytes[5] & 0xFF))
                .append((char) (readBytes[6] & 0xFF))
                .append((char) (readBytes[7] & 0xFF))
                .toString();
        Result result = Result.getByCode(Integer.parseInt(sErrorCode, 16));
        int lenByteArr = readBytes.length;
        int lastDataIdx = 8;
        Log.i(TAG, "parseResponse: " + result.getMessage());

        int element = 0;
        byte[] tByteArr = new byte[0];
        for (int i = 9; i < lenByteArr; i++) {
            if (readBytes[i] == 59) {
                switch (element) {
                    case 0:
                        printerData.put(String.valueOf(i), new String(tByteArr, "windows-1251"));
                        break;
                    default:
                        break;
                }
                tByteArr = new byte[0];
                element++;
                continue;
            }
            tByteArr = addByteToEndArr(tByteArr, readBytes[i]);
        }
        return new CommandResult(result, printerData);
    }

    /**
     * Message form <SOH><len><seq><cmd><pwd><data><ENQ><bcc><ETX>
     *
     * @param command                 to form
     * @param isNeedNewSequenceNumber need generate new sequence number <seq>
     * @return command in bytes
     */
    public byte[] getWriteData(Command command, boolean isNeedNewSequenceNumber) {
        byte[] commandData = formCommand(command);

        byte[] message = {0x00};
        int sequenceNumber = getSequenceNumber(isNeedNewSequenceNumber);
        Log.i(TAG, "sequenceNumber: " + sequenceNumber);
        message = addByteToEndArr(message, sequenceNumber);
        message = addByteToEndArr(message, command.getCode());
        message = addByteToEndArr(message, command.getPassword());
        message = addByteToEndArr(message, 59);
        message = addByteToEndArr(message, commandData);
        message = addByteToEndArr(message, Constants.ENQ);
        if (message.length > (255 - 32)) {
            return null;
        }
        //Form <len>
        byte lenByte = (byte) (message.length + 32);
        message[0] = lenByte;
        //Form <bcc>
        int sum = 0;
        for (byte b : message) {
            sum += unsignedToBytes(b);
        }
        String hex = Integer.toHexString(sum);
        char[] hexArr = hex.toCharArray();
        byte[] crcArr = {0x00, 0x00, 0x00, 0x00};
        if (hexArr.length == 4) {
            crcArr[3] = (byte) Integer.parseInt(("3" + hexArr[3]), 16);
            crcArr[2] = (byte) Integer.parseInt(("3" + hexArr[2]), 16);
            crcArr[1] = (byte) Integer.parseInt(("3" + hexArr[1]), 16);
            crcArr[0] = (byte) Integer.parseInt(("3" + hexArr[0]), 16);
        } else if (hexArr.length == 3) {
            crcArr[3] = (byte) Integer.parseInt(("3" + hexArr[2]), 16);
            crcArr[2] = (byte) Integer.parseInt(("3" + hexArr[1]), 16);
            crcArr[1] = (byte) Integer.parseInt(("3" + hexArr[0]), 16);
            crcArr[0] = (byte) Integer.parseInt(("30"), 16);
        } else if (hexArr.length == 2) {
            crcArr[3] = (byte) Integer.parseInt(("3" + hexArr[1]), 16);
            crcArr[2] = (byte) Integer.parseInt(("3" + hexArr[0]), 16);
            crcArr[1] = (byte) Integer.parseInt(("30"), 16);
            crcArr[0] = (byte) Integer.parseInt(("30"), 16);
        } else if (hexArr.length == 1) {
            crcArr[3] = (byte) Integer.parseInt(("3" + hexArr[0]), 16);
            crcArr[2] = (byte) Integer.parseInt(("30"), 16);
            crcArr[1] = (byte) Integer.parseInt(("30"), 16);
            crcArr[0] = (byte) Integer.parseInt(("30"), 16);
        }
        //Form full message <SOH><len><seq><cmd><pwd><data><ENQ><bcc><ETX>
        byte[] destination = Constants.SOH;
        destination = addByteToEndArr(destination, message);
        destination = addByteToEndArr(destination, crcArr);
        destination = addByteToEndArr(destination, Constants.ETX);
        return destination;
    }


    private int getSequenceNumber(boolean isNeedNewSequenceNumber) {
        int number;
        if (mCurrentNumber == 0) {
            number = 32;
            mCurrentNumber = number;
            return number;
        }
        number = mCurrentNumber + 1;
        if (mCurrentNumber == number) {
            number += 1;
        }
        if (number > 127) {
            number = 32;
        }
        mCurrentNumber = number;
        return number;
    }

    /**
     * Generate <cmd>
     *
     * @param command to form
     * @return code command in byte
     */
    private byte[] formCommand(Command command) {
        byte[] destination = new byte[0];
        List<byte[]> params = command.getCommandParameters();
        for (byte[] i : params) {
            destination = addByteToEndArr(destination, i);
            destination = addByteToEndArr(destination, 59);
        }
        return destination;
    }

}
