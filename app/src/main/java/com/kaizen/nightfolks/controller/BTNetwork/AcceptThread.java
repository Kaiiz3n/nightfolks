package com.kaizen.nightfolks.controller.BTNetwork;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class AcceptThread extends Thread {
    private UUID applicationUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final Context mContext;
    BluetoothAdapter mAdapter;
    private final BluetoothServerSocket mmServerSocket;

    public AcceptThread(BluetoothAdapter adapter, Context context) {
        this.mAdapter = adapter;
        this.mContext = context;
        BluetoothServerSocket tmp = null;

        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = adapter.listenUsingRfcommWithServiceRecord("NightFolks", applicationUUID);

        } catch (IOException e) {
            Log.e(TAG, "Socket's listen() method failed", e);
        }
        mmServerSocket = tmp;
    }


    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Log.e(TAG, "Socket's accept() method failed", e);
                break;
            }

            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                //manageMyConnectedSocket(socket);
                Toast.makeText(mContext, "connected", Toast.LENGTH_LONG).show();
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    Log.e(TAG, "Socket's close() method failed", e);
                }
                break;
            }
        }
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }

}
