package com.example.chatapp.Schemes.Chats;

import com.example.chatapp.Schemes.User;

import java.util.Date;

public class GetChatsMessageScheme {

    private int id;
    private Date created;
    private String content;


    public GetChatsMessageScheme(int id, Date created, String content) {
        this.id = id;
        this.created = created;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
