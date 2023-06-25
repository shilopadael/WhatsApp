package com.example.chatapp.Repository;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.chatapp.Api.ChatAPI;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.LocalDatabase;
import com.example.chatapp.Schemes.Chats.AddContactResponeScheme;
import com.example.chatapp.Schemes.Chats.GetChatsScheme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;

public class UsersRepository {

    private ContactDao contactDao;
    private ChatAPI chatAPI;
    private ContactLiveData contactList;
    private String token;

    public UsersRepository(String ip, String token) {
        this.token = token;
        contactDao = LocalDatabase.getDB().contactsDao();
        contactList = new ContactLiveData();
        chatAPI = new ChatAPI(ip, token);
    }

    public void setIp(String ip) {
        chatAPI.setIp(ip);
    }

    public void updateList(List<Contact> lst) {
        new Thread(() -> {
            contactDao.deleteAll();
            contactDao.insert(lst.toArray(new Contact[0]));
            contactList.postValue(contactDao.getAllContactByOrder());
        }).start();
    }

    public void removeContactById(int chatId, TaskAPI<String> task) {
        // removing the contact
        new Thread(() -> {
            chatAPI.deleteChat(chatId, new TaskAPI<String>() {
                @Override
                public void onSuccess(String s) {
                    task.onSuccess(s);
                }

                @Override
                public void onFailure(String message) {
                    task.onFailure(message);
                }
            });
        }).start();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        chatAPI.setBearer(token);
    }

    class ContactLiveData extends MutableLiveData<List<Contact>> {

        ContactLiveData() {
            super();
            setValue(new ArrayList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                contactList.postValue(contactDao.getAllContactByOrder());
            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactList;
    }

    public void add(Contact user) {
        new Thread(() -> {
            contactDao.insert(user);
            contactList.postValue(contactDao.getAllContactByOrder());
        }).start();
    }

    public void delete(Contact user) {
        new Thread(() -> {
            contactDao.delete(user);
            contactList.postValue(contactDao.getAllContactByOrder());
        }).start();
    }

    public void reload() {
        new Thread(() -> {
            chatAPI.getAllChat(new TaskAPI<List<GetChatsScheme>>() {
                @Override
                public void onSuccess(List<GetChatsScheme> getChatsSchemes) {
                    ArrayList<Contact> contacts = new ArrayList<>();
                    for (GetChatsScheme chat : getChatsSchemes) {
                        Contact contact = Contact.getFromChatScheme(chat);
                        contacts.add(contact);
                    }
                    contactDao.deleteAll();
                    contactDao.insert(contacts.toArray(new Contact[0]));
                    contactList.postValue(contactDao.getAllContactByOrder());
                }

                @Override
                public void onFailure(String message) {
                    // failed to get chats
                }
            });
        }).start();
    }

    public void addNewContact(UsersRepository context ,String username, TaskAPI<Boolean> task) {
        // adding new contact
        new Thread(() -> {
            chatAPI.addNewContact(username, new TaskAPI<AddContactResponeScheme>() {
                @Override
                public void onSuccess(AddContactResponeScheme addContactResponeScheme) {
                    String username = addContactResponeScheme.getUser().getUsername();
                    String displayName = addContactResponeScheme.getUser().getDisplayName();
                    String profilePic = addContactResponeScheme.getUser().getProfilePic();
                    int id = addContactResponeScheme.getId();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat fullDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date today = new Date();
                    String fullDateString = fullDate.format(today);
                    Contact newContact = new Contact(username,
                            "",
                            "No Message Yet",
                            profilePic,
                            displayName,
                            id,
                            fullDateString,
                            today);

                    // adding to the database
                    context.add(newContact);
                    task.onSuccess(true);
                }

                @Override
                public void onFailure(String message) {
                    // failed
                    task.onFailure(message);
                }
            });
        }).start();
    }
}
