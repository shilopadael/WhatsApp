package com.example.chatapp.Models.MessageEntity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Schemes.Messages.GetMessageSenderScheme;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String created;
    int dbId;
    private String sender;
    private String content;

    public Message(int dbID, String created, String sender, String content) {
        this.dbId = dbID;
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

    public Message() {

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
