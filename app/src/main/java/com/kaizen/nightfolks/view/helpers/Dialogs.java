package com.kaizen.nightfolks.view.helpers;

import android.app.AlertDialog;
import android.content.Context;

public class Dialogs {
    public static AlertDialog partyCreationFailureDialog(final Context context) {
        return new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("An Error occured when creating the party")
                .setPositiveButton("OK", ((dialog, which) -> dialog.cancel()))
                .create();
    }
}
