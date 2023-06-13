package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Models.Users;
import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.databinding.ActivitySignUpBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();



        RecyclerView lstUsers = binding.lstUsers;
        final UsersAdapter adapter = new UsersAdapter(this);
        lstUsers.setAdapter(adapter);
        lstUsers.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(R.drawable.ic_facebook, "Padael","Hola", 1, "dan","hey"));
        list.add(new Users(R.drawable.avatar3, "Padael","Hola", 1, "dan","hey"));
        list.add(new Users(R.drawable.avatar3, "Padael","Hola", 1, "dan","hey"));
        list.add(new Users(R.drawable.ic_google, "Padael","Hola", 1, "dan","hey"));
        list.add(new Users(R.drawable.avatar1, "Padael","Hola", 1, "dan","hey"));
        list.add(new Users(R.drawable.avatar3, "Padael","Hola", 1, "dan","hey"));
        list.add(new Users(R.drawable.send, "Padael","Hola", 1, "dan","hey"));
        adapter.setList(list);

    }
}