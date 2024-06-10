package com.example.chatapp.Api;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chatapp.Schemes.User;
import com.example.chatapp.Schemes.UserPassName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    // handle the sign up
    private String ip;
    private Retrofit retrofit;
    private WebServiceAPI service;

    public UserAPI(String ip) {
        this.ip = ip;
        try {
            retrofit = new Retrofit.Builder().baseUrl(String.valueOf(this.ip))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofit.create(WebServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
            retrofit = null;
            service = null;
        }
    }

    // handle sign up
    public void signUp(String username, String password, String displayName, String profilePic, TaskAPI<User> taskAPI) {
        // Create the user object
        if(service == null || retrofit == null) {
            taskAPI.onFailure("Cannot fetch from the given IP, please check your settings.");
            return;
        }
        UserPassName user = new UserPassName(username, password, displayName, profilePic);

        // Send request
        service.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                int status = response.raw().code();
                if (status == 409) {
                    taskAPI.onFailure("Username already exists");
                } else if(status == 500) {
                    taskAPI.onFailure("Error at server.");
                } else if(status == 400) {
                    taskAPI.onFailure("Invalid request.");
                }
                else if(status == 404) {
                    taskAPI.onFailure("Not found.");
                } else if(status == 200) {
                    User newUser = response.body();
                    taskAPI.onSuccess(newUser);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                taskAPI.onFailure("Failed to sign up, maybe user already exists.");
            }
        });
    }

    public void setIp(String ip) {
        try {
            this.ip = ip;
            retrofit = new Retrofit.Builder().baseUrl(String.valueOf(this.ip))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofit.create(WebServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
            retrofit = null;
            service = null;
        }
    }
}
