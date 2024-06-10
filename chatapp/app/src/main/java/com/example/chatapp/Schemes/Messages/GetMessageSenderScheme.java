package com.example.chatapp.Schemes.Messages;

public class GetMessageSenderScheme {

    private String username;

    public GetMessageSenderScheme(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
