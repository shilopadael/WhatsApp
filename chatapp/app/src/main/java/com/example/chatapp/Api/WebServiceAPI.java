package com.example.chatapp.Api;

import android.content.SharedPreferences;

import com.example.chatapp.Models.TokenEntity.Token;
import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.Schemes.Message;
import com.example.chatapp.Schemes.User;
import com.example.chatapp.Schemes.UserPass;
import com.example.chatapp.Schemes.UserPassName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    static final String IP_SHARED_NAME = "ip";
    static final String ANDROID_HEADER = "Android";


    @GET("api/Chats")
    Call<List<Chat>> getChats(@Header("Authorization") String auth, @Header("deviceType") String deviceType);

    @GET("Chats/{id}")
    Call<Chat> getChat(@Path("id") int id, @Header("Authorization") String auth);

    @POST("Chats")
    Call<Chat> createChat(@Body Chat chat, @Header("Authorization") String auth);

    @DELETE("Chats/{id}")
    Call<Chat> deleteChat(@Path("id") int id, @Header("Authorization") String auth);

    @POST("Chats/{id}/Messages")
    Call<Message> createMessage(@Path("id") int id, @Body Message message, @Header("Authorization") String auth);

    @GET("Chats/{id}/Messages")
    Call<List<Message>> getMessages(@Path("id") int id, @Header("Authorization") String auth);

    @Headers({"Accept:application/json",
            "deviceType:Android",
            "Content-Type:application/json"})
    @POST("api/tokens")
    Call<String> createToken(@Body UserPass userPass, @Header("firebaseToken") String token, @Header("username") String username);

    @GET("Users/{username}")
    Call<User> getUser(@Path("username") String username, @Header("Authorization") String auth);

    @POST("api/Users")
    Call<User> createUser(@Body UserPassName user);
}
