package com.example.chatapp.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Chats {


    @PrimaryKey(autoGenerate = true)
    int id;
    List<User> users;
    List<Message> messages;

    public Chats(int id, List<User> users, List<Message> messages) {
        this.id = id;
        this.users = users;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
