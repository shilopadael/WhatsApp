package com.example.chatapp.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Token {


    @PrimaryKey(autoGenerate = true)
    int id;
    String token;
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Token(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
