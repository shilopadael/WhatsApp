package com.example.chatapp.Firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Api.TokenAPI;
import com.example.chatapp.Api.UserAPI;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.viewmodels.MessageViewModel;
import com.example.chatapp.viewmodels.UserViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;
import java.util.Map;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FireBaseMessageService extends FirebaseMessagingService {
    private String ip;
    private String token;
    private SharedPreferences sharedPreferences;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        sharedPreferences = getApplicationContext().getSharedPreferences("chatSystem", MODE_PRIVATE);
        String currentScreen = sharedPreferences.getString("currentScreen", "http://10.0.2.2:5000/");
        ip = sharedPreferences.getString("ip", "none");
        token = sharedPreferences.getString("token", "none");

        if(currentScreen.equals("ChatScreen")) {
            Map<String, String> data = message.getData();
            String chatId = data.get("chatId");
            String sender = data.get("sender");

            // refresh chat
            assert chatId != null;
            MessageViewModel messageViewModel = new MessageViewModel(ip, token, Integer.parseInt(chatId));
            messageViewModel.reload();
        }

        else if(currentScreen.equals("ContactScreen")) {
            // refresh contacts
            UserViewModel userViewModel = new UserViewModel(ip, token);
            userViewModel.reload();
        }

    }

    @Override
    public void onNewToken(@NonNull String firebaseToken) {
        super.onNewToken(token);
        // sending back to the server
        sharedPreferences = getApplicationContext().getSharedPreferences("chatSystem", MODE_PRIVATE);
        ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();
        TokenAPI tokenAPI = new TokenAPI(ip, firebaseToken);
        UserDao userDao = LocalDatabase.getDB().userDao();
        List<User> users = userDao.getAllUsers();
        if(users != null && users.size() > 0) {
            User user = users.get(0);
            String username = user.getUsername();
            String password = user.getPassword();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    tokenAPI.signIn(username, password, new TaskAPI<String>() {
                        @Override
                        public void onSuccess(String s) {
                            editor.putString("token", s);
                            editor.apply();
                        }

                        @Override
                        public void onFailure(String message) {
                            Log.d("FAIL", "failed to resend firebaseToken " + message);
                        }
                    });
                }
            }).start();
        }
        else {
            Log.d("token", "onNewToken: no user found");
        }

    }
}
