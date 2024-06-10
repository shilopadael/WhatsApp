package com.example.chatapp.Schemes.Chats;

import androidx.annotation.Nullable;

import com.example.chatapp.Schemes.Message;
import com.example.chatapp.Schemes.User;

import java.util.Date;

public class GetChatsScheme {

    private int id;
    private User user;
    @Nullable
    private GetChatsMessageScheme lastMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GetChatsMessageScheme getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(GetChatsMessageScheme lastMessage) {
        this.lastMessage = lastMessage;
    }

    public GetChatsScheme(int id, User user, GetChatsMessageScheme lastMessage) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public String getDisplayName() {
        return this.user.getDisplayName();
    }

    public Date getLastMessageDate() {
        return this.lastMessage.getCreated();
    }

}
