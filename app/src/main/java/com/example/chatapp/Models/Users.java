package com.example.chatapp.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    

    String username,password, lastMessage, status;
    @PrimaryKey(autoGenerate = true)
    int userId;

    int profilePic;

    public Users(){

    }

    public Users(int profilePic, String username, String password, int userId, String lastMessage, String status) {
        this.profilePic = profilePic;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.status = status;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users(int profilePic, String username, String password) {
        this.profilePic = profilePic;
        this.username = username;
        this.password = password;
    }
}
