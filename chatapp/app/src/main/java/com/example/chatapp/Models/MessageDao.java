package com.example.chatapp.Models;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> getAllMessages();

    @Insert
    void insert(Message message);

    // Add other necessary methods for updating, deleting, or querying messages

}
