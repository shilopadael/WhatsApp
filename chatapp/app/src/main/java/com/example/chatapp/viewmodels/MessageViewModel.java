package com.example.chatapp.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Repository.MessagesRepository;
import com.example.chatapp.Repository.UsersRepository;

import java.util.List;

public class MessageViewModel {

    private static MessageViewModel instance;
    private MessagesRepository mRepository;
    private LiveData<List<Message>> messages;
    private int size;
    private String token;
    private int id;

    private MessageViewModel(String ip, String token, int id) {
        super();
        this.token = token;
        this.id = id;
        MessageDao messageDao = LocalDatabase.getMessageDao(id);
        mRepository = new MessagesRepository(ip, token, messageDao, id);
        messages = mRepository.getAll();
    }

    public static MessageViewModel getInstance(String ip, String token, int id) {
        if(instance == null) {
            instance = new MessageViewModel(ip, token, id);
        } else if(instance.id != id) {
            instance = new MessageViewModel(ip, token, id);
        } else {
            instance.setToken(token);
            instance.setIp(ip);
            instance.mRepository.setToken(token);
        }
        return instance;
    }
    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public void add(String msg, TaskAPI task) {
        mRepository.add(msg, task);
    }

    public void reload() {
        mRepository.reload();
    }

    public void setIp(String ip) {
        mRepository.setIp(ip);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
