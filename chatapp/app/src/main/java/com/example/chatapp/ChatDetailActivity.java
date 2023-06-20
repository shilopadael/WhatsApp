package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chatapp.Adapter.ChatAdapter;
import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Api.ChatAPI;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.Schemes.Messages.GetMessagesScheme;
import com.example.chatapp.Schemes.Token;
import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.databinding.ActivityChatDetailBinding;
import com.example.chatapp.viewmodels.MessageViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    private String ip;
    private String token;
    private ChatAdapter adapter;
    private MessageViewModel messageViewModel;
    private SharedPreferences sharedPreferences;
    private String OtherPersonUsername;
    private String OtherPersonDisplayName;
    private String OtherPersonProfilePic;
    private String me;
    private int currentChatId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        this.ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        this.token = sharedPreferences.getString("token", "#");

        Intent intent = getIntent();
        this.OtherPersonUsername = intent.getStringExtra("username");
        this.me = sharedPreferences.getString("username", "#");
        this.currentChatId = intent.getIntExtra("chatId", -1);
        this.OtherPersonProfilePic = intent.getStringExtra("profilePic");
        this.OtherPersonDisplayName = intent.getStringExtra("displayName");

        // validate information
        if (this.currentChatId == -1 ||
                this.me.equals("#") ||
                this.OtherPersonUsername == null ||
                this.OtherPersonProfilePic == null ||
                OtherPersonDisplayName == null ||
                this.token.equals("#")) {
            // go to login
            Toast.makeText(this, "Some thing went wrong, please try again.", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(ChatDetailActivity.this, SignInActivity.class);
            startActivity(intent1);
            finish();
        }

        this.messageViewModel = new MessageViewModel(this.ip, this.token, this.currentChatId);

        // updating tool bar username.
        binding.username.setText(OtherPersonDisplayName);

        // updating user image
        String base64ImageData = OtherPersonProfilePic.substring(OtherPersonProfilePic.indexOf(",") + 1);
        byte[] imageBytes = Base64.decode(base64ImageData, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(this)
                .load(bitmap)
                .apply(requestOptions)
                .into(binding.profilePic);

        // set the backArrow
        binding.backArrow.setOnClickListener(v -> {
            Intent intent1 = new Intent(ChatDetailActivity.this, ChatActivity.class);
            startActivity(intent1);
            finish();
        });

        this.adapter = new ChatAdapter(this, Integer.toString(this.currentChatId));
        messageViewModel.getMessages().observe(this, messages -> {
            this.adapter.setList(messages);
            binding.charRecycleView.scrollToPosition(messages.size() - 1);
            messageViewModel.setSize(messages.size());
        });

        binding.charRecycleView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.charRecycleView.setLayoutManager(linearLayoutManager);


        binding.send.setOnClickListener(v -> {
            String message = binding.enterMessage.getText().toString();
            if (message.isEmpty()) {
                return;
            }
            // Sending new message
            messageViewModel.add(message, new TaskAPI<Boolean>() {
                @Override
                public void onSuccess(Boolean o) {
                    // scrolling down the message
                    binding.charRecycleView.scrollToPosition(messageViewModel.getSize() - 1);
                }

                @Override
                public void onFailure(String message) {
//                    Toast.makeText(this, message, 5)
                }
            });
            binding.enterMessage.setText("");
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        this.messageViewModel.setIp(ip);
        this.messageViewModel.reload();
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currentChatId", Integer.toString(this.currentChatId));
        editor.putString("currentScreen", "ChatScreen");

    }

}