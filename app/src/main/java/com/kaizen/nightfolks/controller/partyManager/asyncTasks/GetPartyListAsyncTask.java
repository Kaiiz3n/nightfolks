package com.kaizen.nightfolks.controller.partyManager.asyncTasks;

import android.content.Context;

import com.kaizen.nightfolks.model.database.PartyDatabase;
import com.kaizen.nightfolks.view.HistoryPopUp;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import static com.kaizen.nightfolks.controller.utility.Constants.BY_HOST;

public class GetPartyListAsyncTask extends PartyAsyncTask<Void, Void, List> {
    //have to follow (criteria,value) pattern: e.g (BY_HOST,hostId)
    private Pair<String, String> by;

    public GetPartyListAsyncTask(Pair by, PartyDatabase partyDB, final Context context) {
        this.by = by;
        this.partyDB = partyDB;
        this.weakContext = new WeakReference<>(context);
    }

    @Override
    protected List doInBackground(Void... voids) {
        switch (this.by.getKey()) {
            //Goal: Extensibility
            case BY_HOST:
                return partyDB.getPartyDao().getPartiesByHost(by.getValue());

        }
        return Collections.EMPTY_LIST;
    }

    @Override
    protected void onPostExecute(List result) {
        super.onPostExecute(result);
        ((HistoryPopUp) weakContext.get()).fillTable(result);
    }
}
