//package com.example.chatapp.Models.AllChatsEntity;
//
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.List;
//
//@Dao
//public interface AllChatsDao {
//    @Insert
//    void insert(AllChats allChats);
//
//    @Query("SELECT * FROM AllChats")
//    List<AllChats> getAllChats();
//
//    @Query("SELECT * FROM AllChats WHERE usernameId = :id")
//    default AllChats getChatById(int id) {
//        return null;
//    }
//
//    @Query("DELETE FROM AllChats")
//    void deleteAllChats();
//}
