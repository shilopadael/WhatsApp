package com.example.chatapp.Api;

import com.example.chatapp.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAPI {

    private Retrofit retrofit;
    WebServiceAPI service;

    public ChatAPI() {
        retrofit = new Retrofit.Builder().baseUrl(String.valueOf(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(WebServiceAPI.class);
    }
}
