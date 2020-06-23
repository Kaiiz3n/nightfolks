package com.kaizen.nightfolks.controller.BTNetwork;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class Participant implements IParticipant {
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "ParticipantActivity";
    private BluetoothAdapter mAdapter;
    Context mContext;
    public Set<BluetoothDevice> mBTDevices = new HashSet<>();
    private BluetoothDevice pairedDevice;
    private BTConnectionService btConnectionService;
    private List<String> mMessages;

    public Participant(Context context) {
        this.mContext = context;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.pairedDevice = null;
        btConnectionService = new BTConnectionService(context);
        mMessages = new ArrayList<>();
    }


    @Override
    public boolean isBTSupported() {
        return this.mAdapter != null;
    }

    @Override
    public void unSupportBT() {
        this.mAdapter = null;
    }

    @Override
    public boolean isBTEnabled() {
        return this.mAdapter.isEnabled();
    }

    @Override
    public Object getData() {
        return null;//TODO
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void discover() {
        if (this.mAdapter.isDiscovering()) {
            this.mAdapter.cancelDiscovery();
            Log.d(TAG, "Discover: Canceling discovery.");

            //check BT permissions in manifest
            this.checkBTPermissions();

            this.mAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            this.mContext.registerReceiver(startDiscoveryBroadcastReceiver, discoverDevicesIntent);
        }
        if (!this.mAdapter.isDiscovering()) {

            //check BT permissions in manifest
            this.checkBTPermissions();

            this.mAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            this.mContext.registerReceiver(startDiscoveryBroadcastReceiver, discoverDevicesIntent);
        }
    }

    //TODO : this can be problematic and need to be implemented on the Activity Level
    @Override
    public void enableBT() {
        if (!this.isBTEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ((Activity) mContext).startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            this.mContext.registerReceiver(enableBTBroadcastReceiver, BTIntent);
        }
    }

    private final BroadcastReceiver enableBTBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "on Receive: STATE_OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "on Receive: STATE_TURNING_OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "on Receive: STATE_ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "on Receive: STATE_TURNING_ON");
                        break;
                }
            }
        }
    };
    private final BroadcastReceiver discoverableBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);
                switch (mode) {
                    //Device is in Discoverable Mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d(TAG, "discoveryBroadcastReceiver: Discoverability Enabled.");
                        break;
                    //Device not in discoverable mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "discoveryBroadcastReceiver: Discoverability Disabled. Able to receive connections.");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "discoveryBroadcastReceiver: Discoverability Disabled. Not able to receive connections.");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "discoveryBroadcastReceiver: Connecting....");
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG, "discoveryBroadcastReceiver: Connected.");
                        break;
                }

            }
        }
    };
    private final BroadcastReceiver startDiscoveryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                //THINGS CAN HAPPEN HERE TODO !
            }
        }
    };

    @Override
    public void destroy() {
        this.mContext.unregisterReceiver(enableBTBroadcastReceiver);
        this.mContext.unregisterReceiver(discoverableBroadcastReceiver);
        this.mContext.unregisterReceiver(startDiscoveryBroadcastReceiver);
        this.mContext.unregisterReceiver(pairingBroadcastReceiver);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkBTPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.mContext.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCAfTION");
            permissionCheck += this.mContext.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {
                String[] permissions = {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION};
                ActivityCompat.requestPermissions(
                        (Activity) this.mContext, permissions, 1001);
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    /**
     * Broadcast Receiver that detects bond state changes (Pairing status changes)
     */
    private final BroadcastReceiver pairingBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case1: bonded already
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    Log.d(TAG, "PairingBroadcastReceiver: BOND_BONDED.");
                    pairedDevice = mDevice;
                }
                //case2: creating a bone
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "PairingBroadcastReceiver: BOND_BONDING.");
                }
                //case3: breaking a bond
                if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "PairingBroadcastReceiver: BOND_NONE.");
                }
            }
        }
    };

    //TODO change
    @Override
    public void pairDevice(int i) {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        this.mContext.registerReceiver(pairingBroadcastReceiver, filter);
        this.mAdapter.cancelDiscovery();

        //Should be changed , just when was running the prototype TODO
        Log.d(TAG, "onItemClick: You Clicked on a device.");
        String deviceName = mBTDevices.iterator().next().getName();
        String deviceAddress = mBTDevices.iterator().next().getAddress();

        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);

        //create the bond.
        //NOTE: Requires API 17+? I think this is JellyBean
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Log.d(TAG, "Trying to pair with " + deviceName);
            mBTDevices.iterator().next().createBond();
        }
    }

    @Override
    public void makeDiscoverable() {
        Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        this.mContext.startActivity(discoverableIntent);
        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        this.mContext.registerReceiver(discoverableBroadcastReceiver, intentFilter);
    }

//    @Override
//    public void connectTo(BluetoothDevice device) {
//        new ConnectThread(this.mAdapter, device, this.mContext).start();
//    }

//    @Override
//    public void accept() {
//        new AcceptThread(this.mAdapter, this.mContext).start();
//    }


    @Override
    public Set<BluetoothDevice> getPairedDevices() {
        return this.getBTAdapter().getBondedDevices();
    }

    @Override
    public BluetoothAdapter getBTAdapter() {
        return this.mAdapter;
    }

    @Override
    public Map getPairedDevicesAsMap() {
        HashMap<String, String> devices = new HashMap<String, String>();
        if (this.getPairedDevices().size() == 0) {
            return Collections.EMPTY_MAP;
        }
        // There are paired devices. Get the name and address of each paired device.
        for (BluetoothDevice device : this.getPairedDevices()) {
            devices.put(device.getName(), device.getAddress());
            // MAC address
        }
        return devices;
    }

    /**
     * create method for starting connection
     * Remember the connection will fail and app will crash if you haven't paired first
     */
    public void startConnection() {
        startBTConnection(pairedDevice, BTConnectionService.MY_UUID_INSECURE);
    }

    /**
     * Starting service method
     */
    private void startBTConnection(BluetoothDevice device, UUID uuid) {
        Log.d(TAG, "startBTConnection: Initializing RFCOM Bluetooth Connection.");
        btConnectionService.startClient(device, uuid);
    }

    @Override
    public void sendMsg(String msg) {
        this.btConnectionService.write(msg.getBytes());
    }

    BroadcastReceiver msgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msgPayload");
            mMessages.add(msg);
        }
    };

    @Override
    public String getMsg() {
        LocalBroadcastManager.getInstance(mContext)
                .registerReceiver(msgReceiver, new IntentFilter("incomingMessage"));
        return mMessages.get(mMessages.size() - 1);
    }
}
