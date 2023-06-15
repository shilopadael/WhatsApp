package com.example.chatapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatapp.Listeners.OnContactAddedListener;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.TokenEntity.TokenDao;
import com.example.chatapp.Models.UserEntity.User;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.databinding.FragmentAddContactBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactFragment extends Fragment {

    public OnContactAddedListener contactAddedListener;
    private FragmentAddContactBinding binding;

    private AppDB appDB;

    private UserDao userDao;
    private TokenDao tokenDao;

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


        //creating the DataBase
        appDB = Room.databaseBuilder(requireContext(), AppDB.class,"Users2").
                allowMainThreadQueries().build();
        userDao = appDB.userDao();
        tokenDao = appDB.tokenDao();
        contactDao = appDB.contactsDao();


        EditText editTextName = binding.editTextName;
        Button buttonAddContact = binding.buttonAddContact;

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tokenDao.getToken().getUserName();

                //TODO:: check i user exist /  reall will aske that contact from the server/!!!!!
                User user = userDao.getUserByUsername(username);
                if (user == null) {
                    Toast.makeText(requireContext(), "No such user!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    //add contacts
                    contactDao.insert(new Contact(editTextName.getText().toString(), null, user.getId()));

                    // Check if the listener is set and call the callback method
                    if (contactAddedListener != null) {
                        contactAddedListener.onContactAdded();
                    }
                }

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(AddContactFragment.this).commit();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}