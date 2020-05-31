package com.kaizen.nightfolks.util;

import com.kaizen.nightfolks.model.PartyFactory;
import com.kaizen.nightfolks.model.database.PartyDao;
import com.kaizen.nightfolks.model.database.PartyDatabase;
import com.kaizen.nightfolks.model.entities.Party;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static Party createDummyParty() {
        return PartyFactory.createParty("Party1", "Type1", "Kaiz3n");
    }

    private static List<Party> createDummyParties(int nbOfParties) {
        List<Party> partyList = new ArrayList<>(nbOfParties);
        for (int i = 0; i < nbOfParties; i++) {
            partyList.add(PartyFactory.createParty("Party" + i, "Type" + i, "Kaiz3n"));
        }
        return partyList;
    }

    public static void populateDb(PartyDatabase db, int nbOfParties) {
        PartyDao dao = db.getPartyDao();
        List<Party> partyList = createDummyParties(nbOfParties);
        partyList.forEach(dao::insertParty);
    }

    public static boolean areDateClose(Date date1, Date date2) {
        return Math.abs(date1.getTime() - date2.getTime()) <= 1000;
    }
}
