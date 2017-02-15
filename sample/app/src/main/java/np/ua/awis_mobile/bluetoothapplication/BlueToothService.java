package np.ua.awis_mobile.bluetoothapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import np.ua.awis_mobile.posinteract.lib.DeviceInteraction;
import np.ua.awis_mobile.posinteract.lib.PrinterReaderPipe;
import np.ua.awis_mobile.posinteract.lib.utils.ByteUtils;

/**
 * Bluetooth print service
 * Created by Andrey Holovko on 1/23/17.
 */
@SuppressWarnings("WeakerAccess")
public class BlueToothService implements DeviceInteraction {
    //** Command types message to sent from service Handler
    /**
     * Bluetooth connection state changed
     */
    public static final int MESSAGE_STATE_CHANGE = 1; //** state changed
    /**
     * Read data from device
     */
    public static final int MESSAGE_READ = 2; //
    /**
     * Write data to device
     */
    public static final int MESSAGE_WRITE = 3;
    /**
     * Send device name
     */
    public static final int MESSAGE_DEVICE_NAME = 4;
    /**
     * Key for device name
     */
    public static final String KEY_DEVICE_NAME = "device_name";

    // Constants that indicate the current connection state
    /**
     * We're doing nothing
     */
    public static final int STATE_NONE = 0;
    /**
     * Now listening for incoming connections
     */
    public static final int STATE_LISTEN = 1;
    /**
     * Now initiating an outgoing connection
     */
    public static final int STATE_CONNECTING = 2;
    /**
     * Now connected to a remote device
     */
    public static final int STATE_CONNECTED = 3;

    //TODO set uuid from device
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static final String TAG = "BlueToothService";

    private final BluetoothAdapter mBluetoothAdapter;
    private final Handler mHandler;
    private ConnectThread mConnectThread; //thread to connect
    private ConnectedThread mConnectedThread; //thread to interact
    private PrinterReaderPipe mEventListener;
    private int mState; // connection state

    /**
     * Create service interaction with bluetooth device
     *
     * @param handler for message to UI
     */
    public BlueToothService(Handler handler) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        mHandler = handler;
    }

    /**
     * Set the current state of the chat connection
     *
     * @param state An integer defining the current connection state
     */
    private synchronized void setState(int state) {
        Log.d(TAG, "setState() " + mState + " -> " + state);
        mState = state;
        // Give the new state to the Handler so the UI Activity can update
        mHandler.obtainMessage(MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
    }

    /**
     * Connect to bluetooth
     *
     * @param device to connect
     */
    public synchronized void connect(BluetoothDevice device) {
        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
        setState(STATE_CONNECTING);
    }

    private synchronized void connected(BluetoothSocket socket, BluetoothDevice
            device) {
        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        setState(STATE_CONNECTED);
        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        // Send the name of the connected device back to the UI Activity
        Message msg = mHandler.obtainMessage(MESSAGE_DEVICE_NAME);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DEVICE_NAME, device.getName());
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        Log.d(TAG, "stop");
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        setState(STATE_NONE);
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     *
     * @param out The bytes to write
     * @see ConnectedThread#write(byte[])
     */
    @Override
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    @Override
    public void setResponseListener(PrinterReaderPipe readerPipe) {
        mEventListener = readerPipe;
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mMSocket;
        private final BluetoothDevice mMDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mMSocket
            // because mMSocket is final.
            BluetoothSocket tmp = null;
            mMDevice = device;
            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's create() method failed", e);
            }
            mMSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            mBluetoothAdapter.cancelDiscovery();
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mMSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
                try {
                    mMSocket.close();
                } catch (IOException closeException) {
                    Log.e(TAG, "Could not close the client socket", closeException);
                }
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (this) {
                mConnectThread = null;
            }
            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            // Start the connected thread
            connected(mMSocket, mMDevice);
        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mMSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the client socket", e);
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mMSocket;
        private final InputStream mMInStream;
        private final OutputStream mMOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mMSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mMInStream = tmpIn;
            mMOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            // Keep listening to the InputStream while connected
            while (mState == STATE_CONNECTED) {
                try {
                    // Read from the InputStream
                    bytes = mMInStream.read(buffer);
                    if (bytes > 0) {
                        mEventListener.response(buffer);
                    }

                    // Send the obtained bytes to the UI Activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    //               connectionLost();
                    // Start the service over to restart listening mode
                    //                BluetoothChatService.this.start();
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         *
         * @param buffer The bytes to write
         */
        void write(byte[] buffer) {
            try {
                Log.i(TAG, "write: bytes" + ByteUtils.byteArrToStringHexArr(buffer));
                mMOutStream.write(buffer);

                // Share the sent message back to the UI Activity
                mHandler.obtainMessage(MESSAGE_WRITE, -1, -1, buffer)
                        .sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Exception during write", e);
            }
        }

        /**
         * Cancel thread currently running a connection
         */
        void cancel() {
            try {
                mMSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
}
