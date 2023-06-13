package com.example.chatapp.Schemes;

import java.util.Date;

public class Message {
    private int id;
    private Date created;
    private User sender;
    private String content;


    public Message(int id, Date created, User sender, String content) {
        this.id = id;
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

}
