package com.example.chatapp.Models;




//import androroom.RoomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//import com.example.chatapp.Models.AllChatsEntity.AllChats;
//import com.example.chatapp.Models.AllChatsEntity.AllChatsDao;
import com.example.chatapp.Models.ChatEntity.Chats;
import com.example.chatapp.Models.ChatEntity.ChatsDao;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.Convertors.Converters;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Models.TokenEntity.Token;
import com.example.chatapp.Models.TokenEntity.TokenDao;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;

@Database(entities = {Contact.class, Token.class, User.class, Message.class, Chats.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactsDao();

    public abstract UserDao userDao();

    public  abstract TokenDao tokenDao();

    public  abstract ChatsDao chatsDao();

    public  abstract MessageDao messageDao();

//    public  abstract AllChatsDao allChatsDao();


}
