package com.example.chatapp.Schemes.Messages;

import java.util.Date;

public class GetMessagesScheme {
    private int id;
    private Date created;
    private GetMessageSenderScheme sender;
    private String content;

    public GetMessagesScheme(int id, Date created, GetMessageSenderScheme sender, String content) {
        this.id = id;
        this.created = created;
        this.sender = sender;
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

    public GetMessageSenderScheme getSender() {
        return sender;
    }

    public void setSender(GetMessageSenderScheme sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
