package com.kaizen.nightfolks.controller.partyManager.asyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.kaizen.nightfolks.model.database.PartyDatabase;
import com.kaizen.nightfolks.model.entities.Party;
import com.kaizen.nightfolks.view.helpers.Dialogs;

import java.lang.ref.WeakReference;

public class CreatePartyAsyncTask extends PartyAsyncTask<Void, Void, Void> {
    Party party;
    ProgressDialog partyLoading;

    public CreatePartyAsyncTask(Party party, final Context context, PartyDatabase partyDB) {
        this.weakContext = new WeakReference<>(context);
        this.partyLoading = new ProgressDialog(context);
        this.party = party;
        this.partyDB = partyDB;
    }

    @Override
    protected void onPreExecute() {//TODO fix this
        super.onPreExecute();
        this.partyLoading.setMessage("Loading...");
        this.partyLoading.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        partyDB.getPartyDao().insertParty(this.party);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        partyLoading.dismiss();
        if (party.getId() != 0) {
            Dialogs.partyCreationFailureDialog(weakContext.get());
        } else {
            Toast.makeText(weakContext.get(),
                    "Party created successfully", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}