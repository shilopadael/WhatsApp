package com.example.chatapp.Fragments;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatapp.Adapter.ChatAdapter;
import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Api.ChatAPI;
import com.example.chatapp.Api.TaskAPI;
import com.example.chatapp.Listeners.OnContactAddedListener;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.TokenEntity.TokenDao;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.Schemes.Chats.AddContactResponeScheme;
import com.example.chatapp.databinding.FragmentAddContactBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AddContactFragment extends Fragment {

    public OnContactAddedListener contactAddedListener;
    private FragmentAddContactBinding binding;
    private UsersAdapter adapter;

    private AppDB appDB;
    private SharedPreferences sharedPreferences;

    private ChatAPI chatAPI;

    private ContactDao contactDao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void setOnContactAddedListener(OnContactAddedListener listener) {
        this.contactAddedListener = listener;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireActivity().getSharedPreferences("chatSystem", requireActivity().MODE_PRIVATE);
        String currentUserName = sharedPreferences.getString("username", "#");
        String ip = sharedPreferences.getString("ip", "http://10.0.2.2:5000/");
        String token = sharedPreferences.getString("token", "#");
        if(currentUserName.equals("#") || token.equals("#")){
            // go to login
            Toast.makeText(requireContext(), "Failed!", Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(AddContactFragment.this).commit();
            return;
        }
        //creating the DataBase
        appDB = Room.databaseBuilder(requireContext(), AppDB.class, currentUserName)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        contactDao = appDB.contactsDao();

        chatAPI = new ChatAPI(ip, token);

        EditText editTextName = binding.editTextName;
        Button buttonAddContact = binding.buttonAddContact;

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chatAPI.addNewContact(editTextName.getText().toString(), new TaskAPI<AddContactResponeScheme>() {
                    @Override
                    public void onSuccess(AddContactResponeScheme addContactResponeScheme) {
                        // updating the contact list
                        String username = addContactResponeScheme.getUser().getUsername();
                        String displayName = addContactResponeScheme.getUser().getDisplayName();
                        String profilePic = addContactResponeScheme.getUser().getProfilePic();
                        int id = addContactResponeScheme.getId();

                        Contact newContact = new Contact(username, "", "No Message Yet", profilePic, displayName, id);
                        contactDao.insert(newContact);
                        List<Contact> contacts = contactDao.index();
                        ArrayList<Contact> temp = new ArrayList<>(contacts);
                        adapter.setList(temp);
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().remove(AddContactFragment.this).commit();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setAdapter(UsersAdapter adapter) {
        this.adapter = adapter;
    }
}