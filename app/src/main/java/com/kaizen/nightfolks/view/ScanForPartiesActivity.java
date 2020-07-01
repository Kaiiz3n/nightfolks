package com.kaizen.nightfolks.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.kaizen.nightfolks.controller.BTNetwork.Participant;
import com.kaizen.nightfolks.databinding.ScanForPartyActivityBinding;
import com.kaizen.nightfolks.model.entities.Playlist;

public class ScanForPartiesActivity extends AppCompatActivity {
    private ScanForPartyActivityBinding scanForPartyActivityBinding;
    private Participant guru;
    private static boolean test = false;

    public static void setTest(boolean v) {
        test = v;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanForPartyActivityBinding = ScanForPartyActivityBinding.inflate(getLayoutInflater());
        if (!test) {
            guru = new Participant(this, "guru");
            if (guru.getPairedDevices().isEmpty()) {
                scanForPartyActivityBinding.partyName.setText("No Nearby DJ");

            } else {
                scanForPartyActivityBinding.partyName.setText(guru.getPairedDevices().iterator().next().getName());
            }
        }
        scanForPartyActivityBinding.connectBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {
                if (guru.getPairedDevices().isEmpty()) {
                    Toast.makeText(ScanForPartiesActivity.this, "No nearby DJ detected", Toast.LENGTH_SHORT).show();
                } else {
                    connectToDefaultDevice();//WARNING : default device must exist , error otherwise
                    Intent intent = new Intent(ScanForPartiesActivity.this, PartyActivity.class);
                    intent.putExtra("playlist", Playlist.createSongsList());
                    intent.putExtra("caller", "guru");
                    startActivity(intent);
                }

            }
        });
        setContentView(scanForPartyActivityBinding.getRoot());
    }

    /**
     * only a place holder to connect to default device
     * TODO
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void connectToDefaultDevice() {
        this.guru.discover();
        this.guru.pairDevice(0);
        this.guru.startConnection();
    }
}
