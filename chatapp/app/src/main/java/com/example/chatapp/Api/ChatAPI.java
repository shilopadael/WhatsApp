package com.example.chatapp.Api;

import com.example.chatapp.R;
import com.example.chatapp.Schemes.Chat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAPI {

    private Retrofit retrofit;
    private WebServiceAPI service;
    private String token;
    private String bearerToken;
    private String ip;

    public ChatAPI(String ip, String token) {
        this.ip = ip;
        this.token = token;
        this.bearerToken = "Bearer " + token;
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // Adjust the timeout duration as needed
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(String.valueOf(this.ip))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofit.create(WebServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
            retrofit = null;
            service = null;
        }
    }

    public void getAllChat(TaskAPI<List<Chat>> taskAPI) {
        if(service == null || retrofit == null) {
            // failed to get all chat
            return;
        }

        // sending get request
        service.getChats(this.bearerToken, "Android").enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                int status = response.raw().code();
                if (status == 401) {
                    // failed to get all chat
                } else if(status == 500) {
                    // failed to get all chat
                } else if(status == 400) {
                    // failed to get all chat
                }
                else if(status == 404) {
                    // failed to get all chat
                } else if(status == 200) {
                    List<Chat> chats = response.body();
                    // success to get all chat
                    taskAPI.onSuccess(chats);
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                // failed to get all chat
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void setIp(String ip) {
        try {
            this.ip = ip;
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // Adjust the timeout duration as needed
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(String.valueOf(this.ip))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofit.create(WebServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
            retrofit = null;
            service = null;
        }
    }
}
