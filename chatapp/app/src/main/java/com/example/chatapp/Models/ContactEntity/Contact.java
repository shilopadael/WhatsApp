package com.example.chatapp.Models.ContactEntity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatapp.R;

@Entity
public class Contact {
    

    String username, lastMessage;
    @PrimaryKey(autoGenerate = true)
    int userId;

    int profilePic, id;

    public Contact(){

    }

    public Contact(String username, String lastMessage, int profilePic, int id) {
        this.username = username;
        this.lastMessage = lastMessage;
        this.profilePic = profilePic;
        this.id = id;
    }
    public Contact(String username, String lastMessage, int id) {
        this(username, lastMessage, R.drawable.avatar, id); // Set default id value to 0
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUserId() {
        return userId;
    }


    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public int getId() {
        return id;
    }

}
