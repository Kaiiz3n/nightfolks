package com.kaizen.nightfolks.controller.partyManager;

import android.content.Context;

import com.kaizen.nightfolks.controller.partyManager.asyncTasks.CreatePartyAsyncTask;
import com.kaizen.nightfolks.controller.partyManager.asyncTasks.GetPartyListAsyncTask;
import com.kaizen.nightfolks.model.database.PartyDatabase;
import com.kaizen.nightfolks.model.entities.Party;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static com.kaizen.nightfolks.controller.utility.Constants.BY_HOST;

public class PartyService {
    private PartyDatabase partyDB;
    private final Context context;

    public PartyService(final Context context) {
        this.partyDB = PartyDatabase.getDatabase(context);
        this.context = context;
    }

    public void createParty(String name, String genre, String host) {
        Party party = PartyFactory.createParty(name, genre, host);
        new CreatePartyAsyncTask(party, context, partyDB).execute();
    }

    public void getPartiesByHost(String hostId) {
        GetPartyListAsyncTask getPartyListAsyncTask = new GetPartyListAsyncTask(Pair.of(BY_HOST, hostId), partyDB, context);
        getPartyListAsyncTask.execute();
    }
}
