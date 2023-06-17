package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.example.chatapp.Adapter.FragmentsAdapter;
import com.example.chatapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
//import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
//
//
//
//public class MainActivity extends AppCompatActivity {
//
//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
//        binding.tabLayout.setupWithViewPager(binding.viewPager);
//
//
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
//            String newToken = instanceIdResult.getToken();
//
//        });
//
//
//
//
//
//    }
//}