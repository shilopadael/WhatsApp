package com.example.chatapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.chatapp.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private final String SHARED_PREF_NAME = "ip";
    private ActivitySettingsBinding binding;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences("chatSystem", MODE_PRIVATE);
        getSupportActionBar().hide();

        Button btnSetIp = binding.btnSaveServerIp;
        btnSetIp.setOnClickListener(v -> {
            // on click setting the shared preferences
            sharedPref.edit().putString(SHARED_PREF_NAME, binding.eTSetServerIp.getText().toString()).apply();
        });


    }
}