package com.example.chatapp.Firebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Api.TokenAPI;
import com.example.chatapp.MainActivity;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.R;
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
    private Activity activity;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        sharedPreferences = MainActivity.context.getSharedPreferences("chatSystem", MODE_PRIVATE);
        String currentScreen = sharedPreferences.getString("currentScreen", "none");
        ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        token = sharedPreferences.getString("token", "none");
        Map<String, String> data = message.getData();
        if(currentScreen.equals("ChatScreen")) {
            String chatId = data.get("chatId");
            String sender = data.get("sender");
            String currentChatId = sharedPreferences.getString("currentChatId", "-1");
            if(currentChatId.equals(chatId)) {
                // creating notification sound inside the chat and reloading the chat
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.knock_knock);
                mediaPlayer.start();
                MessageViewModel messageViewModel = MessageViewModel.getInstance(ip, token, Integer.parseInt(chatId));
                messageViewModel.reload();
            }
            else {
                // creating notification sound
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.notification);
                mediaPlayer.start();
            }
            // refresh chat
        }

        else if(currentScreen.equals("ContactScreen")) {
            // creating notification sound
            String l = data.get("extra");
            if(l == null) {
                // new message arrived
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.notification);
                mediaPlayer.start();
            } else if(l.equals("new-contact")){
                // adding new contact
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.new_contact);
                mediaPlayer.start();
            } else if(l.equals("remove-contact")){
                // adding new group
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.remove_contact);
                mediaPlayer.start();
            }

            // refresh contacts
            UserViewModel userViewModel = UserViewModel.getInstance(ip, token);
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
            editor.putString("firebaseToken", firebaseToken).apply();
            Log.d("token", "onNewToken: no user found");
        }

    }


    private void showNotificationToast(final Context context, final String message) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
