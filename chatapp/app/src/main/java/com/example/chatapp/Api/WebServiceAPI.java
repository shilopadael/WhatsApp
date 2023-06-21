package com.example.chatapp.Api;

import android.content.SharedPreferences;

import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.Schemes.Chats.AddContactResponeScheme;
import com.example.chatapp.Schemes.Chats.AddContactScheme;
import com.example.chatapp.Schemes.Chats.GetChatsScheme;
import com.example.chatapp.Schemes.Message;
import com.example.chatapp.Schemes.Messages.GetMessagesScheme;
import com.example.chatapp.Schemes.Messages.MessageToSend;
import com.example.chatapp.Schemes.User;
import com.example.chatapp.Schemes.UserPass;
import com.example.chatapp.Schemes.UserPassName;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("api/Chats")
    Call<List<GetChatsScheme>> getChats(@Header("Authorization") String auth, @Header("deviceType") String deviceType);

    @GET("api/Chats/{id}")
    Call<Chat> getChat(@Path("id") int id, @Header("Authorization") String auth);

    @Headers({"Accept:application/json",
            "deviceType:Android",
            "Content-Type:application/json"})
    @POST("api/Chats")
    Call<AddContactResponeScheme> createChat(@Body AddContactScheme chat, @Header("Authorization") String auth);

    @DELETE("api/Chats/{id}")
    Call<ResponseBody> deleteChat(@Path("id") int id, @Header("Authorization") String auth);

    @Headers({"Accept:application/json",
            "deviceType:Android",
            "Content-Type:application/json"})
    @POST("api/Chats/{id}/Messages")
    Call<Message> createMessage(@Path("id") int id, @Body MessageToSend message, @Header("Authorization") String auth);

    @GET("api/Chats/{id}/Messages")
    Call<List<GetMessagesScheme>> getMessages(@Path("id") int id, @Header("Authorization") String auth);

    @Headers({"Accept:application/json",
            "deviceType:Android",
            "Content-Type:application/json"})
    @POST("api/tokens")
    Call<String> createToken(@Body UserPass userPass, @Header("firebaseToken") String token, @Header("username") String username);

    @GET("api/Users/{username}")
    Call<User> getUser(@Path("username") String username, @Header("Authorization") String auth);

    @POST("api/Users")
    Call<User> createUser(@Body UserPassName user);
}
