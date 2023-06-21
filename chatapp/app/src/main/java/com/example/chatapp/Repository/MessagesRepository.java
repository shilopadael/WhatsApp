package com.example.chatapp.Repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.chatapp.Api.ChatAPI;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Schemes.Chats.GetChatsScheme;
import com.example.chatapp.Schemes.Messages.GetMessagesScheme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesRepository {

    private MessageDao messageDao;
    private int chatId;
    private ChatAPI chatAPI;
    private MessagesLiveData messageList;
    private String token;

    public MessagesRepository(String ip, String token, MessageDao messageDao, int id) {
        this.messageDao = messageDao;
        this.messageList = new MessagesLiveData();
        this.chatAPI = new ChatAPI(ip, token);
        this.chatId = id;
        this.token = token;
    }

    public void setIp(String ip) {
        chatAPI.setIp(ip);
    }


    public void setToken(String token) {
        this.token = token;
        chatAPI.setBearer(token);
    }


    class MessagesLiveData extends MutableLiveData<List<Message>> {

        MessagesLiveData() {
            super();
            setValue(new ArrayList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                messageList.postValue(messageDao.getAllMessages());
            }).start();
        }
    }

    public LiveData<List<Message>> getAll() {
        return messageList;
    }

    public void add(String msg, TaskAPI<Boolean> task) {
        new Thread(() -> {
            // sending new message to the server
            chatAPI.sendNewMessage(this.chatId, msg, new TaskAPI<com.example.chatapp.Schemes.Message>() {
                @Override
                public void onSuccess(com.example.chatapp.Schemes.Message message) {
                    Message msg = fromMessagePostToMessageEntity(message);
                    messageDao.insert(msg);
                    messageList.postValue(messageDao.getAllMessages());
                    updateContactLastMessage(msg);
                    task.onSuccess(true);
                }

                @Override
                public void onFailure(String message) {
                    // failed
                    task.onFailure("failed");
                }
            });
        }).start();
    }


    public void reload() {
        new Thread(() -> {
            chatAPI.getAllMessagesByChatId(chatId, new TaskAPI<List<GetMessagesScheme>>() {
                @Override
                public void onSuccess(List<GetMessagesScheme> getMessagesSchemes) {
                    messageDao.deleteAll();
                    for(GetMessagesScheme msg : getMessagesSchemes) {
                        Message m = fromMessageSchemeToMessage(msg);
                        messageDao.insert(m);
                    }
                    messageList.postValue(messageDao.getAllMessages());
                }

                @Override
                public void onFailure(String message) {
                    // failed
                }
            });
            messageList.postValue(messageDao.getAllMessages());
        }).start();
    }

    private Message fromMessageSchemeToMessage(GetMessagesScheme getMessagesScheme) {
        // date format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = getMessagesScheme.getCreated();
        String dateStr = formatter.format(date);
        return new Message(getMessagesScheme.getId(),
                dateStr,
                getMessagesScheme.getSender().getUsername(),
                getMessagesScheme.getContent());
    }

    private void updateContactLastMessage(Message msg) {
        AppDB app = LocalDatabase.getDB();
        ContactDao contactDao = app.contactsDao();
        String messageContent = msg.getContent();
        if (messageContent.length() > 25) {
            messageContent = messageContent.substring(0, 25) + "...";
        }
        contactDao.updateLastMessageAndTime(messageContent, msg.getCreated(), this.chatId);
//        Contact contact = contactDao.get(this.chatId);
//        contactDao.delete(contact);
//        contactDao.insert(contact);
    }

    private Message fromMessagePostToMessageEntity(com.example.chatapp.Schemes.Message message) {
        // date format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = message.getCreated();
        String dateStr = formatter.format(date);
        Message message1 = new Message(message.getId(),
                dateStr,
                message.getSender().getUsername(),
                message.getContent());
        return message1;
    }
}
