package com.example.chatapp.Models;

public class Users {

    String profilePic,username,password, userId, lastMessage, status;

    public Users(){

    }

    public Users(String profilePic, String username, String password, String userId, String lastMessage, String status) {
        this.profilePic = profilePic;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public Users(String profilePic, String username, String password) {
        this.profilePic = profilePic;
        this.username = username;
        this.password = password;
    }
}
