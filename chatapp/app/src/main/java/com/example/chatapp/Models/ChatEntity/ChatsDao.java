package com.example.chatapp.Models.ChatEntity;


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

    @Query("SELECT * FROM Chats " +
            "INNER JOIN User u1 ON u1.id = (SELECT id FROM User WHERE username = :username1) " +
            "INNER JOIN User u2 ON u2.id = (SELECT id FROM User WHERE username = :username2) " +
            "WHERE EXISTS (SELECT 1 FROM Chats c WHERE c.id = Chats.id AND u1.id IN (c.users) AND u2.id IN (c.users)) " +
            "LIMIT 1")
    Chats getChatByUsernames(String username1, String username2);




    @Insert
    void insert(Chats chats);

    @Update
    void update(Chats chats);

    @Query("DELETE FROM chats WHERE id = :chatId")
    void delete(int chatId);

}
