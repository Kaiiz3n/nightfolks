package com.kaizen.nightfolks.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kaizen.nightfolks.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mainActivityBinding.asDjBtn.setOnClickListener(v -> {
            Intent djIntent = new Intent(MainActivity.this, DJNewPartyActivity.class);
            djIntent.putExtra("caller", "dj");
            startActivity(djIntent);
        });
        mainActivityBinding.asPartyGuru.setOnClickListener(v -> {
                    Intent guruIntent = new Intent(MainActivity.this, ScanForPartiesActivity.class);
                    guruIntent.putExtra("caller", "guru");
                    startActivity(guruIntent);
                }

        );
        setContentView(mainActivityBinding.getRoot());
    }
}
