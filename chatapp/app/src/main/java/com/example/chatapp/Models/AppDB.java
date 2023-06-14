package com.example.chatapp.Models;




//import androroom.RoomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.chatapp.Models.Convertors.Converters;

@Database(entities = {Contact.class, Token.class, User.class, Message.class, Chats.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactsDao();

    public abstract UserDao userDao();

    public  abstract TokenDao tokenDao();

    public  abstract ChatsDao chatsDao();

    public  abstract MessageDao messageDao();


}
