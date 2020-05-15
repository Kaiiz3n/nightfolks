package com.kaizen.nightfolks;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kaizen.nightfolks.databinding.ActivityMainBinding;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.asDjBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", LENGTH_SHORT).show();
            }
        });
        setContentView(binding.getRoot());
    }

}
