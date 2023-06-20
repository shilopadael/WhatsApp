package com.example.chatapp.Models;




//import androroom.RoomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//import com.example.chatapp.Models.AllChatsEntity.AllChats;
//import com.example.chatapp.Models.AllChatsEntity.AllChatsDao;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.Convertors.Converters;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;

@Database(entities = {Contact.class, User.class, Message.class}, version = 10)
@TypeConverters(Converters.class)
public abstract class AppDB extends RoomDatabase {

    public abstract ContactDao contactsDao();

    public abstract UserDao userDao();

    public  abstract MessageDao messageDao();

//    public  abstract AllChatsDao allChatsDao();


}
