package com.example.chatapp.Api;

public interface TaskAPI<T> {

    void onSuccess(T t);

    void onFailure(String message);
}
