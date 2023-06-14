package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.Token;
import com.example.chatapp.Models.User;
import com.example.chatapp.Models.UserDao;
import com.example.chatapp.databinding.ActivityMainBinding;
import com.example.chatapp.databinding.ActivitySignInBinding;
import com.example.chatapp.databinding.ActivitySignUpBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    private AppDB appDB;
    private UserDao userDao;

    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class,"Users2").
                allowMainThreadQueries().build();
        userDao = appDB.userDao();


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
            // TODO: add sign in logic
            User user = userDao.getUserByUsername(username.getText().toString());
            if (user == null) {
                Toast.makeText(this, "Incorrect username or password, please try again!", Toast.LENGTH_SHORT).show();
                return;
            }
            //there is a user.
            boolean isCorrectPass = user.getPassword().equals(password.getText().toString());
            boolean isCorrectUsername = user.getUsername().equals(username.getText().toString());

            if (!isCorrectPass || !isCorrectUsername) {
                Toast.makeText(this, "Incorrect username or password, please try again!", Toast.LENGTH_SHORT).show();
            } else {

                appDB.tokenDao().insert(new Token("demo token", username.getText().toString()));
                // Navigate to the login activity
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });



    }
}