package com.example.chatapp.Firebase;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.viewmodels.MessageViewModel;
import com.example.chatapp.viewmodels.UserViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseMessageService extends FirebaseMessagingService {
    private static final String TAG = "FIRE_BASE";

    private MessageViewModel messageViewModel;
    private UserViewModel userViewModel;
    private Intent currentIntent;
    private String locationScreen;
    private int currentChatId;
    private String currentUsername;

    public MessageViewModel getMessageViewModel() {
        return messageViewModel;
    }

    public void setMessageViewModel(MessageViewModel messageViewModel) {
        this.messageViewModel = messageViewModel;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    public Intent getCurrentIntent() {
        return currentIntent;
    }

    public void setCurrentIntent(Intent currentIntent) {
        this.currentIntent = currentIntent;
    }

    public String getLocationScreen() {
        return locationScreen;
    }

    public void setLocationScreen(String locationScreen) {
        this.locationScreen = locationScreen;
    }

    public int getCurrentChatId() {
        return currentChatId;
    }

    public void setCurrentChatId(int currentChatId) {
        this.currentChatId = currentChatId;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        // getting the current intent
        if(locationScreen.equals("ChatScreen")) {

        } else if(locationScreen.equals("ContactScreen")) {
            // generate the notification, updating the current chat id

        }
    }
}
