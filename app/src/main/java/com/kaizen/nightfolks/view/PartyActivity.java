package com.kaizen.nightfolks.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kaizen.nightfolks.controller.BTNetwork.Participant;
import com.kaizen.nightfolks.controller.utility.AppData;
import com.kaizen.nightfolks.databinding.PartyActivityBinding;

import org.apache.commons.lang3.StringUtils;

public class PartyActivity extends AppCompatActivity {
    private Participant participant;
    private PartyActivityBinding partyActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        participant = (Participant) AppData.getInstance().getParticipant();

        partyActivityBinding = PartyActivityBinding.inflate(getLayoutInflater());
        String caller = getIntent().getExtras().getString("caller");
        if (StringUtils.equals(caller, "guru")) {
            partyActivityBinding.historyBtn.setVisibility(View.GONE);
        }
        partyActivityBinding.historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryPopUp historyPopUp = new HistoryPopUp(participant.getId(), PartyActivity.this);
                historyPopUp.showPopupWindow(v);
            }
        });
        setContentView(partyActivityBinding.getRoot());
    }
}