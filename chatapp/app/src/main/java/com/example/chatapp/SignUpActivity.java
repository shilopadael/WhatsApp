package com.example.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.User;
import com.example.chatapp.Models.UserDao;
import com.example.chatapp.databinding.ActivityMainBinding;
import com.example.chatapp.databinding.ActivitySignUpBinding;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    ActivitySignUpBinding binding;
    private Uri imageUri;
    private EditText username, password, confirmPassword;
    private boolean isImageUploaded = false;
    private boolean isUsernameValid = false;
    private boolean isPasswordValid = false;
    private boolean isConfirmPasswordValid = false;

    private AppDB appDB;

    private UserDao userDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        //creating the DataBase
        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class,"Users2").
                allowMainThreadQueries().build();
        userDao = appDB.userDao();

        // creating the event listeners
        Button signUp = binding.btnSignUp;
        Button btnAddPicture = binding.btnAddPicture;
        TextView alreadyHaveAccount = binding.tVAlreadyHaveAccount;

        username = binding.signUpUsername;
        password = binding.signUpPassword;
        confirmPassword = binding.signUpConfirmPassword;


        // adding event listeners
        signUp.setOnClickListener(v -> {
            if(  isUsernameValid && isPasswordValid && isConfirmPasswordValid){
                // if that user already in the dataBase
                if (userDao.getUserByUsername(username.getText().toString()) != null) {
                    Toast.makeText(this, "Username already exist", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new User object with the entered username and password
                User newUser = new User(username.getText().toString(), password.getText().toString());
                userDao.insert(newUser);

                // Navigate to the login activity
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddPicture.setOnClickListener(v -> {
            // uploading the profile picture to the database
            openFileChooser();
        });

        alreadyHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

        // check username validity
        username.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (username.getText().toString().length() < 4 || username.getText().toString().length() > 12) {
                    username.setError("Username must be at least 4-12 characters long");
                    isUsernameValid = false;
                } else {
                    isUsernameValid = true;
                }
            }
        });

        // check password validity
        password.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (password.getText().toString().length() < 8 || password.getText().toString().length() > 20) {
                    password.setError("Password must be at least 8-20 characters long");
                    isPasswordValid = false;
                } else {
                    isPasswordValid = true;
                }
            }
        });

        // check if passwords match
        confirmPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String pas1 = password.getText().toString();
                String pas = confirmPassword.getText().toString();
                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    confirmPassword.setError("Passwords do not match");
                    isConfirmPasswordValid = false;
                } else {
                    isConfirmPasswordValid = true;
                }
            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView profilePic = binding.signUpDisplayPicture;
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(this)
                        .load(imageUri)
                        .apply(requestOptions)
                        .into(profilePic);
                isImageUploaded = true;
            } else {
                // Set a placeholder image if no image is available
                profilePic.setImageResource(R.drawable.profile);
                isImageUploaded = false;
            }
        }
    }
}