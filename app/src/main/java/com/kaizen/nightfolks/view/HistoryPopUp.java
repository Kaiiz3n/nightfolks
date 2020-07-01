package com.kaizen.nightfolks.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kaizen.nightfolks.R;
import com.kaizen.nightfolks.controller.partyManager.PartyService;
import com.kaizen.nightfolks.model.entities.Party;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class HistoryPopUp extends Activity {
    private String djId;
    final Context context;
    TableLayout historyTable;

    public HistoryPopUp(String djId, Context context) {
        this.djId = djId;
        this.context = context;

    }


    public void showPopupWindow(final View view) {
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.history_popup, null);
        historyTable = popupView.findViewById(R.id.history_table);
        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //TODO maybe here & delete HistoryPopup
        //Initialize the elements of our window, install the handler

        new PartyService(this).getPartiesByHost(djId);
        //Handler for clicking on the inactive zone of the window

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void fillTable(List<Party> partiesList) {
        for (Party p : partiesList) {
            TableRow row = new TableRow(context);
            TextView partyName = new TextView(context);
            TextView partyDate = new TextView(context);
            TextView partyMusicGenres = new TextView(context);
            String datePattern = "dd-MM-yyyy HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
            String date = simpleDateFormat.format(p.getDate());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
            lp.setMargins(4, 4, 4, 4);
            partyName.setText(p.getName());
            partyName.setLayoutParams(lp);

            partyDate.setText(date);
            partyDate.setLayoutParams(lp);

            partyMusicGenres.setText(p.getGenre());
            partyMusicGenres.setLayoutParams(lp);

            row.addView(partyName);
            row.addView(partyDate);
            row.addView(partyMusicGenres);
            historyTable.addView(row);
        }
    }
}
