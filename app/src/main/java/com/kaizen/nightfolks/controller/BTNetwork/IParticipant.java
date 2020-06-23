package com.kaizen.nightfolks.controller.BTNetwork;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.Map;
import java.util.Set;

public interface IParticipant<Type> {

    /**
     * Need to be called on onDestroy() in the view
     * Unregisters all broadcasters
     */
    void destroy();

    /**
     * @return {true} if the device has a BluetoothAdapter
     */
    boolean isBTSupported();

    /**
     * set DefaultAdapter to null
     */
    void unSupportBT();

    /**
     * Check if Bluetooth is enabled on the device
     */
    boolean isBTEnabled();

    /**
     * @return Data stored in the communication
     */
    Type getData();

    /**
     * Start discovering for nearby devices
     */
    void discover();

    /**
     * Enable bluetooth on the bluetooth adapter
     */
    void enableBT();

    /**
     * Pair with the i-th device from a ListView/RecyclerView
     *
     * @param i
     */
    void pairDevice(int i);

    /**
     * Make a device discoverable (enable it automatically)
     */
    void makeDiscoverable();

    /**
     * @return a Set of Paired Devices
     */
    Set<BluetoothDevice> getPairedDevices();

    /**
     * @return the device's bluetooth adapter
     */
    BluetoothAdapter getBTAdapter();

    /**
     * @return Map with <Name,Address> as Key,Value Pair
     */
    Map<String, String> getPairedDevicesAsMap();

    /**
     * Send Msg to the buffer(the two devices need paired & connected)
     *
     * @param msg
     */
    void sendMsg(String msg);

    /**
     * @return Message from the buffer
     */
    String getMsg();
}
