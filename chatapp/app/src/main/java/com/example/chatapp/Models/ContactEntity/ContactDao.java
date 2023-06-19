package com.example.chatapp.Models.ContactEntity;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();
    @Query("SELECT * FROM Contact ORDER BY timestamp DESC")
    List<Contact> getAllContactByOrder();

    @Query("SELECT * FROM Contact WHERE userId=:id")
    Contact get(int id);

    @Query("SELECT * FROM Contact WHERE username=:username")
    Contact getByUserName(String username);

    @Query("UPDATE Contact SET lastMessage = :newLastMessage, lastMessageTime = :newLastMessageTime WHERE userId = :userId")
    void updateLastMessageAndTime(String newLastMessage, String newLastMessageTime, int userId);

    @Query("SELECT databaseId FROM Contact")
    List<Integer> getChatIdList();


    @Insert
    void insert(Contact...contacts);

    @Update
    void update(Contact...contacts);

    @Delete
    void delete(Contact...contacts);

    @Query("DELETE FROM Contact")
    void deleteAll();
}
