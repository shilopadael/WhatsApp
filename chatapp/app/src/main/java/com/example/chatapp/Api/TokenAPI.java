package com.example.chatapp.Api;

import android.content.SharedPreferences;

import com.example.chatapp.Schemes.UserPass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAPI {

    private String ip;
    private Retrofit retrofit;
    private WebServiceAPI service;
    private String firebaseToken;

    public TokenAPI(String ip, String firebaseToken) {
        this.ip = ip;
        this.firebaseToken = firebaseToken;
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

    public void signIn(String username, String password, TaskAPI<String> taskAPI) {
        // Send request
        if(service == null || retrofit == null) {
            taskAPI.onFailure("Cannot fetch from the given IP, please set a new IP at your settings.");
            return;
        }
        UserPass userPass = new UserPass(username, password);
        // trying to get token
        service.createToken(userPass, this.firebaseToken, username).enqueue(new Callback<String> () {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int status = response.raw().code();
                if (status == 401) {
                    taskAPI.onFailure("Invalid username or password");
                } else if(status == 500) {
                    taskAPI.onFailure("Error at server.");
                } else if(status == 400) {
                    taskAPI.onFailure("Invalid request.");
                }
                else if(status == 404) {
                    taskAPI.onFailure("Not found.");
                } else if(status == 200) {
                    String token = response.body();
                    taskAPI.onSuccess(token);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                taskAPI.onFailure(t.getMessage());
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
