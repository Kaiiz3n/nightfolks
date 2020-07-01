package com.kaizen.nightfolks.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.kaizen.nightfolks.controller.BTNetwork.Participant;
import com.kaizen.nightfolks.controller.partyManager.PartyService;
import com.kaizen.nightfolks.controller.utility.AppData;
import com.kaizen.nightfolks.controller.utility.DeviceIdentifier;
import com.kaizen.nightfolks.databinding.DjNewPartyBinding;
import com.kaizen.nightfolks.model.entities.Playlist;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DJNewPartyActivity extends AppCompatActivity {
    private DjNewPartyBinding newPartyBinding;
    private PartyService partyService;
    private Participant dj;
    private AppData appData;
    private static boolean test = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appData = AppData.getInstance();
        //Necessary: Keyboard appearance doesn't make the layout look messy
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        newPartyBinding = DjNewPartyBinding.inflate(getLayoutInflater());
        newPartyBinding.createPartyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPartyValid()) {
                    String partyName = newPartyBinding.partyName.getText().toString();
                    String partyGenre = getPartyGenres();
                    String djId = DeviceIdentifier.getUniqueIdentifierFromSecure(DJNewPartyActivity.this);
                    partyService = new PartyService(DJNewPartyActivity.this);
                    partyService.createParty(partyName, partyGenre, djId);
                    //just for testing purposes
                    if (!test) {
                        dj = new Participant(DJNewPartyActivity.this, djId);
                        dj.makeDiscoverable();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//TODO refactor
                        dj.discover();
                    }
                    Intent intent = new Intent(DJNewPartyActivity.this, PartyActivity.class);
                    intent.putExtra("djId", dj.getId());
                    intent.putExtra("playlist", Playlist.createSongsList());
                    // Workaround to the serialization headache at this point
                    appData.setParticipant(dj);
                    startActivity(intent);
                    finish();
                }
            }
        });

        setContentView(newPartyBinding.getRoot());
    }

    private String getPartyGenres() {
        //could have used StringJoiner , but API min_level is 15
        List<String> genres = new ArrayList<>();
        ChipGroup musicGenres = newPartyBinding.musicGenres;
        // we know its 6 but brute forcing is always a bad idea(extensibility in mind)
        int nbOfMusicGenres = musicGenres.getChildCount();
        for (int i = 0; i < nbOfMusicGenres; i++) {
            Chip chip = (Chip) musicGenres.getChildAt(i);
            if (chip.isChecked()) {
                genres.add(chip.getText().toString());
            }
        }
        return StringUtils.join(genres, ",");
    }

    private boolean isMusicGenreSpecified() {
//        return newPartyBinding.musicGenres.getCheckedChipId() != -1;
        ChipGroup musicGenres = newPartyBinding.musicGenres;
        for (int i = 0; i < musicGenres.getChildCount(); i++) {
            Chip chip = (Chip) musicGenres.getChildAt(i);
            if (chip.isChecked()) return true;
        }
        return false;
    }

    private boolean isPartyValid() {
        if (!isMusicGenreSpecified()) {
            newPartyBinding.musicGenres.setBackgroundColor(Color.RED);
            return false;
        }
        if (Objects.requireNonNull(newPartyBinding.partyName.getText()).length() < 6) {
            newPartyBinding.partyName.setError("Party Name must contain atleast 6 characters");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        if (dj != null)
            this.dj.destroy();
        super.onDestroy();
    }

    public static void setTest(boolean test) {
        DJNewPartyActivity.test = test;
    }
}

