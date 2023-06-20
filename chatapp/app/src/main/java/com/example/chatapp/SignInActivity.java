package com.example.chatapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Api.TokenAPI;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    private AppDB appDB;
    private UserDao userDao;

    private TokenAPI tokenAPI;
    private String firebaseToken;

    private SharedPreferences sharedPreferences;

    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        // getting localDatabase
        UserDao userDao = LocalDatabase.getDB().userDao();

        // checking if the user is already logged in
        List<User> users = userDao.getAllUsers();

        // signing in if the user didn't logged out
        if (users != null && users.size() > 0 && users.get(0).isOnline()) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
            finish();
        }


        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        String ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        firebaseToken = task.getResult();
                        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("firebaseToken", firebaseToken);
                        tokenAPI = new TokenAPI(ip, firebaseToken);
                    }
                });



        // creating the event listeners
        TextView signUp = binding.tVClickSignUp;
        Button signIn = binding.btnSignIn;
        ImageView btnSettings = binding.btnSettings;

        username = binding.signInEtUsername;
        password = binding.signInEtPassword;


        // adding event listeners
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        signIn.setOnClickListener(v -> {
            // getting the username and password
            if(tokenAPI == null) {
                Toast.makeText(SignInActivity.this, "Please wait for the token to be generated", Toast.LENGTH_SHORT).show();
                return;
            }
            String username = this.username.getText().toString();
            String password = this.password.getText().toString();
            tokenAPI.signIn(username, password, new TaskAPI<String>() {
                @Override
                public void onSuccess(String token) {
                    // save the token
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    // go to the main activity
                    Intent intent = new Intent(SignInActivity.this, ChatActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        String ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        if(tokenAPI != null) {
            tokenAPI.setIp(ip);
        }
    }
}