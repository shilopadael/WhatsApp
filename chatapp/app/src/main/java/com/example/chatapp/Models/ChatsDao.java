package com.example.chatapp.Models;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChatsDao {

    @Query("SELECT * FROM chats")
    List<Chats> getAllChats();

    @Query("SELECT * FROM chats WHERE id = :chatId")
    Chats getChatById(int chatId);

    @Insert
    void insert(Chats chats);

    @Update
    void update(Chats chats);

    @Query("DELETE FROM chats WHERE id = :chatId")
    void delete(int chatId);

}
