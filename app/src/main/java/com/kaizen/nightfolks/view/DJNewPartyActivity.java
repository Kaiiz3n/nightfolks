package com.kaizen.nightfolks.view;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.kaizen.nightfolks.databinding.DjNewPartyBinding;

public class DJNewPartyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Necessary: Keyboard appearance doesn't make the layout look messy
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        DjNewPartyBinding binding = DjNewPartyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}

