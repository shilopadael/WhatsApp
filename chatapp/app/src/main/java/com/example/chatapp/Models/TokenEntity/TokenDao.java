package com.example.chatapp.Models.TokenEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TokenDao {
    @Query("SELECT * FROM token LIMIT 1")
    Token getToken();

    @Insert
    void insert(Token token);

    @Query("DELETE FROM token")
    void deleteToken();
}
