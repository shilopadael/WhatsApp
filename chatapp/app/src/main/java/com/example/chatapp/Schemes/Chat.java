package com.example.chatapp.Schemes;

import java.util.Date;
import java.util.List;

public class Chat {

    private int id;
    private User user;
    private Message lastMessage;

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

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Chat(int id, User user, Message lastMessage) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public String getDisplayName() {
        return this.user.getDisplayName();
    }

    public String getLastMessage() {
        return this.lastMessage.getContent();
    }

    public Date getLastMessageDate() {
        return this.lastMessage.getCreated();
    }

}
