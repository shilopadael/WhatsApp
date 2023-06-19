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
    private MessagesRepository mRepository;

    private LiveData<List<Message>> messages;
    private int size;

    public MessageViewModel(String ip, String token, int id) {
        super();
        MessageDao messageDao = LocalDatabase.getMessageDao(id);
        mRepository = new MessagesRepository(ip, token, messageDao, id);
        messages = mRepository.getAll();
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
}
