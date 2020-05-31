package com.kaizen.nightfolks.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kaizen.nightfolks.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.asDjBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DJNewPartyActivity.class))
        );
        setContentView(binding.getRoot());
    }

}
