package com.example.chatapp.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Repository.UsersRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UsersRepository mRepository;

    private LiveData<List<Contact>> users;

    public UserViewModel(String ip, String token){
        super();
        mRepository =  new UsersRepository(ip, token);
        users = mRepository.getAll();
    }

    public LiveData<List<Contact>> getUsers() {
        return users;
    }
    public void add(Contact user) {
        mRepository.add(user);
    }

    public void updateList(List<Contact> lst) {
        mRepository.updateList(lst);
    }

    public void addNewContact(String username, TaskAPI<Boolean> task) {
        mRepository.addNewContact(mRepository ,username, task);
    }

    public void removeContactById(int chatId, TaskAPI<String> task) {
        mRepository.removeContactById(chatId, task);
    }
    public void delete(Contact user) {
        mRepository.delete(user);
    }
    public void reload() {
        mRepository.reload();
    }

    public void setIp(String ip) {
        mRepository.setIp(ip);
    }


}
