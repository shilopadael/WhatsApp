package com.example.chatapp.Models.Convertors;

import androidx.room.TypeConverter;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.UserEntity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static User fromString(String value) {
        return new Gson().fromJson(value, User.class);
    }

    @TypeConverter
    public static String fromUser(User user) {
        return new Gson().toJson(user);
    }

    @TypeConverter
    public static List<User> fromUserListString(String value) {
        Type listType = new TypeToken<List<User>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromUserList(List<User> userList) {
        return new Gson().toJson(userList);
    }

    @TypeConverter
    public static List<Message> fromMessageListString(String value) {
        Type listType = new TypeToken<List<Message>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromMessageList(List<Message> messageList) {
        return new Gson().toJson(messageList);
    }
}
