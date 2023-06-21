package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Api.ChatAPI;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Firebase.FireBaseMessageService;
import com.example.chatapp.Fragments.AddContactFragment;
import com.example.chatapp.Listeners.OnContactAddedListener;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.Schemes.Chats.GetChatsScheme;
import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.viewmodels.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;
    public static Context context;
    private UserViewModel userViewModel;
    private FireBaseMessageService fireBaseMessageService;
    private SharedPreferences sharedPreferences;
    private String ip;
    private String token;
    private String username;
    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        context = getApplicationContext();

        // binding
        TextView displayName = binding.toolBarDisplayName;
        RecyclerView lstUsers = binding.lstUsers;

        // initializing the variables
        fireBaseMessageService = new FireBaseMessageService();
        fireBaseMessageService.setActivity(this);

        // getting the ip and token from shared preferences
        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        token = sharedPreferences.getString("token", "#");
        username = sharedPreferences.getString("username", "#");
        if (username.equals("#") || token.equals("#")) {
            // go to login
            Toast.makeText(this, "Failed To Log In! try again", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }

        // checking if the old username is the same username in the localStorage
        UserDao users = LocalDatabase.getDB().userDao();
        List<User> lst = users.getAllUsers();
        if (lst == null || lst.isEmpty()) {
            // setting the current user
            String password = sharedPreferences.getString("password", "#");
            users.deleteAll();
            User currentUser = new User(username, token);
            currentUser.setOnline(true);
            currentUser.setPassword(password);
            users.insert(currentUser);
        } else {
            User onlyUser = lst.get(0);
            if (!onlyUser.getUsername().equals(username)) {
                String password = sharedPreferences.getString("password", "#");
                // changing the only username and deleting all the contact and room database
                AppDB app = LocalDatabase.getDB();
                // deleting old user messages
                LocalDatabase.eraseDatabase(app.contactsDao().getChatIdList());
                // deleting old user contacts
                app.contactsDao().deleteAll();
                users.deleteAll();
                User currentUser = new User(username, token);
                currentUser.setOnline(true);
                currentUser.setPassword(password);
                users.insert(currentUser);
            } else {
                onlyUser.setOnline(true);
                users.update(onlyUser);
            }
        }

        // initializing the database Room
        adapter = new UsersAdapter(this, username);

        // ViewModel for live data for the contact list.
        userViewModel = UserViewModel.getInstance(ip, token);

        ChatAPI api = new ChatAPI(ip, token);
        api.getUserInformation(username, new TaskAPI<com.example.chatapp.Schemes.User>() {
            @Override
            public void onSuccess(com.example.chatapp.Schemes.User user) {
                displayName.setText(user.getDisplayName());
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ChatActivity.context, "Failed to retrieve the displayName, setting the username.", Toast.LENGTH_SHORT).show();
                displayName.setText(username);
            }
        });


        lstUsers.setAdapter(adapter);
        lstUsers.setLayoutManager(new LinearLayoutManager(this));

        // callback function when ever the contact list changed.
        userViewModel.getUsers().observe(this, contacts -> {
            adapter.setList((ArrayList<Contact>) contacts);
        });

        ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // Not needed for swipe functionality
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Get the position of the swiped item
                int position = viewHolder.getAdapterPosition();

                List<Contact> itemList = userViewModel.getUsers().getValue();
                if (itemList != null) {
                    int chatId = itemList.get(position).getId();
                    Contact contactToDelete = itemList.get(position);
                    itemList.remove(position);
                    userViewModel.updateList(itemList);
                    Context thisContext = getApplicationContext();

                    userViewModel.removeContactById(chatId, new TaskAPI<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(thisContext, s, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(String message) {
                            Toast.makeText(thisContext, message, Toast.LENGTH_SHORT).show();
                            // adding the contact back to the view model
                            userViewModel.add(contactToDelete);
                        }
                    });

                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallback);
        itemTouchHelper.attachToRecyclerView(binding.lstUsers);

        binding.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(ChatActivity.this, SettingsActivity.class);
                startActivity(settingIntent);
            }
        });

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userViewModel.reload();
                binding.refreshLayout.setRefreshing(false);
            }
        });
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        AddContactFragment addContactFragment = new AddContactFragment();
        addContactFragment.setUserViewModel(userViewModel);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_logout) {
                    // Handle logout action
                    List<User> users = LocalDatabase.getDB().userDao().getAllUsers();
                    User thisUser = users.get(0);
                    thisUser.setOnline(false);
                    LocalDatabase.getDB().userDao().deleteAll();
                    LocalDatabase.getDB().userDao().insert(thisUser);
                    Intent intent = new Intent(context, SignInActivity.class);
                    startActivity(intent);
                    finish();
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
    protected void onResume() {
        super.onResume();
        // reloading the contact
        // updating the ip
        this.ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currentScreen", "ContactScreen").apply();
        userViewModel.setIp(this.ip);
        userViewModel.reload();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}