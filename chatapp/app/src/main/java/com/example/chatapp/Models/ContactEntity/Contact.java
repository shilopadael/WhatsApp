package com.example.chatapp.Models.ContactEntity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatapp.R;

@Entity
public class Contact {


    @PrimaryKey(autoGenerate = true)
    int userId;
    @ColumnInfo(name = "username")
    String username;
    String lastMessage, lastMessageTime, profilePicBase64, displayName;

    int profilePic, id;

    public Contact(){

    }


    public Contact(String username,
                   String lastMessage,
                   String lastMessageTime,
                   String profilePicBase64,
                     String displayName,
                   int id) {
        this.id = id;
        this.username = username;
        if(lastMessage.length() > 20) {
            this.lastMessage = lastMessage.substring(0, 20) + "...";
        } else {
            this.lastMessage = lastMessage;
        }
        this.lastMessageTime = lastMessageTime;
        this.profilePicBase64 = profilePicBase64;
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getProfilePicBase64() {
        return profilePicBase64;
    }

    public void setProfilePicBase64(String profilePicBase64) {
        this.profilePicBase64 = profilePicBase64;
    }

    public void setId(int id) {
        this.id = id;
    }

}
