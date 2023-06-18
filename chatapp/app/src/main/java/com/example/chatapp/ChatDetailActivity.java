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
import com.example.chatapp.Schemes.Messages.GetMessagesScheme;
import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.databinding.ActivityChatDetailBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    private AppDB appDB;
    private ContactDao contactDao;
    private String ip;
    private ChatAdapter adapter;

    private ChatAPI chatAPI;
    private MessageDao messageDao;

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
        chatAPI = new ChatAPI(ip, sharedPreferences.getString("token", "#"));

        Intent intent = getIntent();

        this.OtherPersonUsername = intent.getStringExtra("username");
        this.me = sharedPreferences.getString("username", "#");
        this.currentChatId = intent.getIntExtra("chatId", -1);
        this.OtherPersonProfilePic = intent.getStringExtra("profilePic");
        this.OtherPersonDisplayName = intent.getStringExtra("displayName");

        if (this.currentChatId == -1 ||
                this.me.equals("#") ||
                this.OtherPersonUsername == null ||
                this.OtherPersonProfilePic == null ||
                OtherPersonDisplayName == null) {
            // go to login
            Toast.makeText(this, "Some thing went wrong, please try again.", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(ChatDetailActivity.this, SignInActivity.class);
            startActivity(intent1);
            finish();
        }

        String chatNameId = Integer.toString(currentChatId);
        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, chatNameId)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        AppDB generalDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, this.me)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        messageDao = appDB.messageDao();
        contactDao = generalDB.contactsDao();

        // Get the intent that started this activity

        // extracting username

        // updating tool bar
        binding.username.setText(OtherPersonDisplayName);
        String base64ImageData = OtherPersonProfilePic.substring(OtherPersonProfilePic.indexOf(",") + 1);
        byte[] imageBytes = Base64.decode(base64ImageData, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(this)
                .load(bitmap)
                .apply(requestOptions)
                .into(binding.profilePic);

        //set the backArrow
        binding.backArrow.setOnClickListener(v -> {
            Intent intent1 = new Intent(ChatDetailActivity.this, ChatActivity.class);
            startActivity(intent1);
            finish();
        });

        // retriving the messages from the messageDao

        List<Message> allMessages = messageDao.getAllMessages();

        ArrayList<Message> messages = new ArrayList<>(allMessages);

        this.adapter = new ChatAdapter(messages, this, Integer.toString(this.currentChatId));

        binding.charRecycleView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.charRecycleView.setLayoutManager(linearLayoutManager);
        this.adapter.setList(messages);

        binding.charRecycleView.scrollToPosition(messages.size() - 1);


        binding.send.setOnClickListener(v -> {
            String message = binding.enterMessage.getText().toString();
            if (message.isEmpty()) {
                return;
            }
            // sending the message to the server
            chatAPI.sendNewMessage(this.currentChatId, message, new TaskAPI<com.example.chatapp.Schemes.Message>() {
                @Override
                public void onSuccess(com.example.chatapp.Schemes.Message message) {
                    // adding the message to the database
                    fromMessagePostToMessageEntity(message);
                    updateLastMessageContact(message);
                    // waiting for the message to be added to the database and then scrolling down
                    binding.charRecycleView.scrollToPosition(messages.size() - 1);
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(ChatDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
            binding.enterMessage.setText("");
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        chatAPI.setIp(ip);

        // retrieving all the messages
        chatAPI.getAllMessagesByChatId(this.currentChatId, new TaskAPI<List<GetMessagesScheme>>() {
            @Override
            public void onSuccess(List<GetMessagesScheme> getMessagesSchemes) {
                // deleting all the messages
                messageDao.deleteAll();
                for (GetMessagesScheme getMessagesScheme : getMessagesSchemes) {
                    fromMessageSchemeToMessage(getMessagesScheme);
                }

                // displaying the messages
                List<Message> allMessages = messageDao.getAllMessages();
                adapter.setList(allMessages);
                binding.charRecycleView.scrollToPosition(allMessages.size() - 1);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ChatDetailActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fromMessageSchemeToMessage(GetMessagesScheme getMessagesScheme) {
        // date format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = getMessagesScheme.getCreated();
        String dateStr = formatter.format(date);
        Message message = new Message(getMessagesScheme.getId(),
                dateStr,
                getMessagesScheme.getSender().getUsername(),
                getMessagesScheme.getContent());
        messageDao.insert(message);
    }

    private void fromMessagePostToMessageEntity(com.example.chatapp.Schemes.Message message) {
        // date format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = message.getCreated();
        String dateStr = formatter.format(date);
        Message message1 = new Message(message.getId(),
                dateStr,
                message.getSender().getUsername(),
                message.getContent());
        this.adapter.addMessageToList(message1);
    }

    private void updateLastMessageContact(com.example.chatapp.Schemes.Message message) {
        Contact contact = contactDao.getByUserName(this.OtherPersonUsername);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        String dateStr = formatter.format(message.getCreated());

        String messageContent = message.getContent();
        if (messageContent.length() > 20) {
            messageContent = messageContent.substring(0, 20) + "...";
        }

        contact.setLastMessage(messageContent);
        contact.setLastMessageTime(dateStr);

        List<Contact> contactList = contactDao.index();
        Contact toRemove = null;
        for (Contact contact1 : contactList) {
            if (contact1.getUsername().equals(contact.getUsername())) {
                toRemove = contact1;
                break;
            }
        }
        contactList.remove(toRemove);
        contactList.add(0, contact);

        // Update the IDs of the contacts in the list if necessary

        contactDao.deleteAll();
        contactDao.insert(contactList.toArray(new Contact[0]));
    }
}