package com.example.chatapp.Models.ContactEntity;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chatapp.R;
import com.example.chatapp.Schemes.Chats.GetChatsScheme;
import com.google.android.material.timepicker.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Contact implements Comparable<Contact> {

    @PrimaryKey(autoGenerate = true)
    int userId;
    @ColumnInfo(name = "username")
    String username;
    String lastMessage, lastMessageTime, profilePicBase64, displayName, fullDate;

    @ColumnInfo(name = "timestamp")
    Long lastMessageTimeStamp;

    int profilePic, id;

    public Contact() {

    }

    @Override
    public int compareTo(Contact o) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fullDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date oDate = fullDate.parse(o.fullDate);
            Date thisDate = fullDate.parse(this.fullDate);
            return thisDate.compareTo(oDate);
        } catch (ParseException e) {
            Log.i(TAG, "compareTo: failed");
            return 0;
        }
    }

    public Contact(String username,
                   String lastMessage,
                   String lastMessageTime,
                   String profilePicBase64,
                   String displayName,
                   int id,
                   String fullDate,
                   Date date) {
        this.id = id;
        this.username = username;
        if (lastMessage.length() > 20) {
            this.lastMessage = lastMessage.substring(0, 20) + "...";
        } else {
            this.lastMessage = lastMessage;
        }
        this.lastMessageTime = lastMessageTime;
        this.profilePicBase64 = profilePicBase64;
        this.displayName = displayName;
        this.fullDate = fullDate;
        this.lastMessageTimeStamp = date.getTime();
    }

    public static void sort(ArrayList<Contact> contacts) {
        contacts.sort(Contact::compareTo);
    }

    public static Contact getFromChatScheme(GetChatsScheme chat) {
        int id = chat.getId();
        String displayName = chat.getDisplayName();
        String username = chat.getUser().getUsername();
        String pic = chat.getUser().getProfilePic();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fullDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (chat.getLastMessage() == null) {
            Date d = new Date();
            String toContactDate = fullDate.format(d);
            return new Contact(username, "", "No Message Yet", pic, displayName, id,
                    toContactDate, new Date());
        } else {
            String lastMessage = chat.getLastMessage().getContent();
            Date lastMessageDate = chat.getLastMessageDate();
            // comparing the date to the current date (day)
            StringBuilder stringBuilder = new StringBuilder();
            Date today = new Date();
            String day = dayFormat.format(today);
            String month = monthFormat.format(today);
            String year = yearFormat.format(today);

            String lastMessageDay = dayFormat.format(lastMessageDate);
            String lastMessageMonth = monthFormat.format(lastMessageDate);
            String lastMessageYear = yearFormat.format(lastMessageDate);
            if (!(day.equals(lastMessageDay) && month.equals(lastMessageMonth) && year.equals(lastMessageYear))) {
                // if the date is not today, then we will show the date
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(lastMessageDate);
                stringBuilder.append(formattedDate);
            } else {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
                String formattedTime = dateFormat.format(lastMessageDate);
                stringBuilder.append(formattedTime);
            }
            String lastMessageTime = fullDate.format(chat.getLastMessage().getCreated());
            return new Contact(username,
                    lastMessage,
                    stringBuilder.toString(),
                    pic,
                    displayName,
                    id,
                    lastMessageTime,
                    chat.getLastMessage().getCreated());
        }
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
