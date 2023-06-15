package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatapp.Adapter.ChatAdapter;
import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ChatEntity.Chats;
import com.example.chatapp.Models.ChatEntity.ChatsDao;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Models.TokenEntity.TokenDao;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.databinding.ActivityChatDetailBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    private AppDB appDB;
    private ContactDao contactDao;
    private ChatsDao chatsDao;
    private TokenDao tokenDao;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();


        //TODO:: get here thae chat id by calling GET /api/Chat/ with username

        //for now creating new Chat for those two usernames.


        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class,"Users2").
                allowMainThreadQueries().build();
        chatsDao = appDB.chatsDao();
        tokenDao = appDB.tokenDao();
        userDao = appDB.userDao();

        // Get the intent that started this activity
        Intent intent = getIntent();


        //extracting username
        final  String senderUsername = intent.getStringExtra("username");
        String appUsername = tokenDao.getToken().getUserName();
        String receiverId = intent.getStringExtra("userId");

        //updating tool bar
        binding.username.setText(senderUsername);
        binding.profilePic.setImageResource(intent.getIntExtra("profilePic",R.drawable.profile));

        //set the backArrow
        binding.backArrow.setOnClickListener(v -> {
            Intent intent1 = new Intent(ChatDetailActivity.this, ChatActivity.class);
            startActivity(intent1);
        });


        //extraction both usernames
        final Chats chat =chatsDao.getChatByUsernames(senderUsername,appUsername);

        if (chat == null) {
            User user1 = userDao.getUserByUsername(senderUsername);
            User user2 = userDao.getUserByUsername(appUsername);
            List<User> userList = new ArrayList<>();
            userList.add(user1);
            userList.add(user2);
            //create new chat
            final Chats chat1 = new Chats(0,userList ,null);
            chatsDao.insert(chat1);
        }


        //TODO:: here we will get the chat id from the server.
        final Chats chat2 =chatsDao.getChatById(4);


        final ArrayList<Message> messages = new ArrayList<>();

        // Retrieve the messages from the chat and add them to the messages list
        if (chat2 != null && chat2.getMessages() != null) {
            messages.addAll(chat2.getMessages());
        }


        final ChatAdapter chatAdapter = new ChatAdapter(messages, this);

        binding.charRecycleView.setAdapter(chatAdapter);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.charRecycleView.setLayoutManager(linearLayoutManager);
        chatAdapter.setList(messages);



        binding.send.setOnClickListener(v -> {
            String massage = binding.enterMessage.getText().toString();
            final Message messageModel = new Message(0,"new Date().getTime()",userDao.getUserByUsername(appUsername), massage);
            assert chat2 != null;
            chat2.addMessage(messageModel);
            chatsDao.update(chat2);
            chatAdapter.addMessageToList(messageModel);
            binding.enterMessage.setText("");
        });

        //creating new chat between them if there is no Cha












    }
}