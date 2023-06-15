package com.example.chatapp.Models.ChatEntity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.UserEntity.User;

import java.util.ArrayList;
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

    public void addMessage(Message message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        this.messages.add(message);}
}
