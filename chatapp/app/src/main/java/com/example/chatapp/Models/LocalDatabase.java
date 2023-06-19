package com.example.chatapp.Models;

import androidx.room.Room;

import com.example.chatapp.MainActivity;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalDatabase {

    private static AppDB appDB = null;
    private static LocalDatabase instance = null;
    private static final Map<Integer, MessageDao> messageDaoMap = new HashMap<>();

    private LocalDatabase() {
        appDB = Room.databaseBuilder(MainActivity.context, AppDB.class, "chatSystem")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    public static LocalDatabase getInstance() {
        if(instance == null ){
            instance = new LocalDatabase();
        }
        return instance;
    }

    public static AppDB getDB() {
        if(instance == null ){
            instance = new LocalDatabase();
        }
        return appDB;
    }

    public static MessageDao getMessageDao(int id) {
        if(messageDaoMap.get(id) == null) {
            // creating a new one
            String name = Integer.toString(id);
            AppDB app = Room.databaseBuilder(MainActivity.context, AppDB.class, name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
            MessageDao messageDao = app.messageDao();
            messageDaoMap.put(id, messageDao);
        }
        return messageDaoMap.get(id);
    }

    public static void eraseDatabase(List<Integer> lst) {
        if(lst == null || lst.isEmpty()) {
            return;
        }
        for(int i : lst) {
            String name = Integer.toString(i);
            File fileToDelete = MainActivity.context.getDatabasePath(name);
            boolean flag = fileToDelete.delete();
        }
    }
}
