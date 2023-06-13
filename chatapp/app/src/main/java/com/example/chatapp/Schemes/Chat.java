package com.example.chatapp.Schemes;

import java.util.List;

public class Chat {

    private int id;
    List<User> users;
    List<Message> messages;

    public Chat(int id, List<User> users, List<Message> messages) {
        this.id = id;
        this.users = users;
        this.messages = messages;
    }

}
