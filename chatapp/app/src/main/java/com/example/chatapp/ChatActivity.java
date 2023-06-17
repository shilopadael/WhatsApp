package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Api.ChatAPI;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Fragments.AddContactFragment;
import com.example.chatapp.Listeners.OnContactAddedListener;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.databinding.ActivityChatBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements OnContactAddedListener {

    ActivityChatBinding binding;
    EditText contacts;
    private AppDB appDB;
    private ContactDao contactDao;
    private ChatAPI chatAPI;
    private SharedPreferences sharedPreferences;

    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        String ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        String username = sharedPreferences.getString("username", "#");
        String token = sharedPreferences.getString("token", "#");
        if(username.equals("#")){
            // go to login
            Toast.makeText(this, "Failed To Log In! try again", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, username).
                allowMainThreadQueries().build();
        contactDao = appDB.contactsDao();
        chatAPI = new ChatAPI(ip, token);

        //take the token and the username from the token class.
        //if there is no token go to the login
        RecyclerView lstUsers = binding.lstUsers;
        adapter = new UsersAdapter(this, username);
        lstUsers.setAdapter(adapter);
        lstUsers.setLayoutManager(new LinearLayoutManager(this));
        adapter.setList((ArrayList<Contact>)contactDao.index());


    }
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        AddContactFragment addContactFragment = new AddContactFragment();
        addContactFragment.setOnContactAddedListener(this);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_logout) {
                    // Handle logout action
                    return true;
                } else if (itemId == R.id.menu_add_contact) {

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, addContactFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onContactAdded() {
        adapter.setList((ArrayList<Contact>) contactDao.index());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // fetching the contacts from the server
        chatAPI.getAllChat(new TaskAPI<List<Chat>>() {
            @Override
            public void onSuccess(List<Chat> chats) {
                contactDao.deleteAll();
                for (Chat chat : chats) {
                    fromChatToContact(chat);
                }
                // deleting the old contacts
                adapter.setList((ArrayList<Contact>) contactDao.index());
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ChatActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fromChatToContact(Chat chat){
        int id = chat.getId();
        String displayName = chat.getDisplayName();
        String lastMessage = chat.getLastMessage();
        Date lastMessageDate = chat.getLastMessageDate();
        // comparing the date to the current date (day)
        StringBuilder stringBuilder = new StringBuilder();
        Date today = new Date();
        if(!today.equals(lastMessageDate)){
            // if the date is not today, then we will show the date
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(lastMessageDate);
            stringBuilder.append(formattedDate);
        } else {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            String formattedTime = dateFormat.format(lastMessageDate);
            stringBuilder.append(formattedTime);
        }
        Contact contact = new Contact(displayName, lastMessage, id, stringBuilder.toString());
        contactDao.insert(contact);
    }
}