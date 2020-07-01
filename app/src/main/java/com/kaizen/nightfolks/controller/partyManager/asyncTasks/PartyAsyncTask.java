package com.kaizen.nightfolks.controller.partyManager.asyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kaizen.nightfolks.model.database.PartyDatabase;

import java.lang.ref.WeakReference;

public abstract class PartyAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    WeakReference<Context> weakContext;//avoid context leaking
    PartyDatabase partyDB;
    private ProgressDialog partyLoading;


}
