package com.kaizen.nightfolks.model;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.kaizen.nightfolks.model.database.PartyDao;
import com.kaizen.nightfolks.model.database.PartyDatabase;
import com.kaizen.nightfolks.model.entities.Party;
import com.kaizen.nightfolks.util.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PartyDatabaseTest {
    private PartyDao partyDao;
    private PartyDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PartyDatabase.class).allowMainThreadQueries().build();
        partyDao = db.getPartyDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void writePartyAndReadShouldReturnCorrectParty() {
        TestUtils.populateDb(db, 1);
        Party party = partyDao.getPartyList().get(0);
        Party byHost = partyDao.getPartyByHost("Kaiz3n");
        assertEquals(byHost, party);
    }

    @Test
    public void partyDatabase_shouldGetPopulatedCorrectly() {
        TestUtils.populateDb(db, 10);
        assertEquals(10, partyDao.getPartyCount());
    }

    @Test
    public void writeParty_dateShouldBeCorrect() {
        TestUtils.populateDb(db, 1);
        Party party = partyDao.getPartyList().get(0);
        assertTrue(TestUtils.areDateClose(Calendar.getInstance().getTime(), party.getDate()));
    }
}
