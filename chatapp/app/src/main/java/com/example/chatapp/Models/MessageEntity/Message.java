package com.example.chatapp.Models.MessageEntity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatapp.Models.UserEntity.User;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    int id;

    private String created;
    private User sender;
    private String content;

    public Message(int id, String created, User sender, String content) {
        this.id = id;
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
