package com.example.chatapp.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TokenDao {
    @Query("SELECT * FROM token LIMIT 1")
    Token getToken();

    @Insert
    void insert(Token token);

    @Query("DELETE FROM token")
    void deleteToken();
}
