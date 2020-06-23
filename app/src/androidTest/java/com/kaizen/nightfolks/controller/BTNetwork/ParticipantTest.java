package com.kaizen.nightfolks.controller.BTNetwork;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4ClassRunner.class)
/**
 * Test commented on purpose,real device is needed. Can't perform automatically
 */
public class ParticipantTest {
    public static final String MSG_FROM_THE_OTHER_SIDE = "I m the Participant Zero ";
    private Participant participant;
    //should be mocked
    private Context mContext;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

//    @Before
//    public void setup() throws Exception {
//        participant = new Participant(mContext);
//    }

    @Test
    public void testBTAdapterSupported() {
//        assertTrue(participant.isBTSupported());
//        //set adapter to null
//        participant.unSupportBT();
//        //adapter is now null
//        assertFalse(participant.isBTSupported());
//        //reset adapter
//        participant = new Participant(mContext);
//        assertTrue(participant.isBTSupported());
        assertTrue(false);

    }

    @Test
    public void testBTAdapterEnabled() {
//        assertFalse(participant.isBTEnabled());
//        //enable BTAdapter
//        participant.enableBT();
//        //reset adapter
//        assertTrue(participant.isBTEnabled());
        assertTrue(false);

    }

    @Test
    public void pairDeviceTest() {
//        //enable BTAdapter
//        participant.enableBT();
//        participant.discover();
//        participant.pairDevice(0);
//        //device is expected to pair
//        assertFalse(participant.getPairedDevices().isEmpty());
        assertTrue(false);
    }

    @Test
    public void connectionTest() {
        //enable BTAdapter
//        participant.enableBT();
//        participant.discover();
//        participant.pairDevice(0);
//        //Paired device must respond here
//        participant.startConnection();
//        Assert.assertEquals(participant.getBTAdapter().getState(), BluetoothAdapter.STATE_CONNECTED);
        assertTrue(false);
    }

    @Test
    public void communicationTest() {
        //enable BTAdapter
//        participant.enableBT();
//        participant.discover();
//        participant.pairDevice(0);
//        //Paired device must respond here
//        participant.startConnection();
//        Assert.assertEquals(participant.getBTAdapter().getState(), BluetoothAdapter.STATE_CONNECTED);
//
//        participant.sendMsg(MSG_FROM_THE_OTHER_SIDE);
//        Assert.assertEquals(MSG_FROM_THE_OTHER_SIDE, participant.getMsg());
        //Breakpoint after this , the paired device ( with Testing App(Activity Context) open)
        // should receive the message
        assertTrue(false);
    }
}