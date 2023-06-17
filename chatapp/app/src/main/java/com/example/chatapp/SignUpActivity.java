package com.example.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Api.UserAPI;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.Schemes.User;
import com.example.chatapp.databinding.ActivitySignUpBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SignUpActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    ActivitySignUpBinding binding;
    private Uri imageUri;
    private SharedPreferences sharedPreferences;
    private EditText username, password, confirmPassword, displayName;
    private boolean isImageUploaded = false;
    private boolean isUsernameValid = false;
    private boolean isPasswordValid = false;
    private boolean isConfirmPasswordValid = false;
    private String base64Image;
    private UserAPI userAPI;

    private AppDB appDB;

    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        String ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000");
        userAPI = new UserAPI(ip);


        //creating the DataBase
//        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class,"Users2").
//                allowMainThreadQueries().build();
//        userDao = appDB.userDao();

        // creating the event listeners
        Button signUp = binding.btnSignUp;
        Button btnAddPicture = binding.btnAddPicture;
        TextView alreadyHaveAccount = binding.tVAlreadyHaveAccount;

        username = binding.signUpUsername;
        password = binding.signUpPassword;
        confirmPassword = binding.signUpConfirmPassword;
        displayName = binding.signUpDisplayName;


        // adding event listeners
        signUp.setOnClickListener(v -> {
            if (isUsernameValid && isPasswordValid && isConfirmPasswordValid && isImageUploaded) {
                // checking if the display name is empty
                if (displayName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "You must have a display name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // if that user already in the dataBase
//                if (userDao.getUserByUsername(username.getText().toString()) != null) {
//                    Toast.makeText(this, "Username already exist", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Create a new User object with the entered username and password
//                User newUser = new User(username.getText().toString(), password.getText().toString());
//                userDao.insert(newUser);
                Intent intent = new Intent(this, SignInActivity.class);
                userAPI.signUp(username.getText().toString(), password.getText().toString(),
                        displayName.getText().toString(), base64Image, new TaskAPI<User>() {
                            @Override
                            public void onSuccess(User user) {
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(String message) {
                                // if the user already exist or failed to sign up
                                Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        });
                // Navigate to the login activity
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

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("chatSystem", MODE_PRIVATE);
        String ip = sharedPreferences.getString("ip", "");
        userAPI = new UserAPI(ip);
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
                // saving the img as a string in base64
                base64Image = convertImageToBase64(imageUri);
                if (base64Image != null) {
                    Glide.with(this)
                            .load(imageUri)
                            .apply(requestOptions)
                            .into(profilePic);
                    isImageUploaded = true;
                } else {
                    Toast.makeText(this, "Error uploading image", Toast.LENGTH_SHORT).show();
                    isImageUploaded = false;
                }
            } else {
                // Set a placeholder image if no image is available
                profilePic.setImageResource(R.drawable.profile);
                isImageUploaded = false;
            }
        }
    }

    private String convertImageToBase64(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);

            // Resize the image
            int maxWidth = 400; // Set your desired maximum width
            int maxHeight = 400; // Set your desired maximum height
            Bitmap resizedBitmap = resizeImage(originalBitmap, maxWidth, maxHeight);

            // Compress the image
            int compressionQuality = 80; // Set your desired compression quality (0-100)
            Bitmap compressedBitmap = compressImage(resizedBitmap, compressionQuality);

            // Convert the compressed bitmap to base64 string
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            compressedBitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            String base64jpeg = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

            return "data:image/jpeg;base64," + base64jpeg;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public Bitmap compressImage(Bitmap originalBitmap, int compressionQuality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, outputStream);
        byte[] compressedData = outputStream.toByteArray();

        return BitmapFactory.decodeByteArray(compressedData, 0, compressedData.length);
    }

    public Bitmap resizeImage(Bitmap originalBitmap, int maxWidth, int maxHeight) {
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();
        float scaleFactor = Math.min(((float) maxWidth) / originalWidth, ((float) maxHeight) / originalHeight);

        int resizedWidth = Math.round(originalWidth * scaleFactor);
        int resizedHeight = Math.round(originalHeight * scaleFactor);

        return Bitmap.createScaledBitmap(originalBitmap, resizedWidth, resizedHeight, false);
    }
}