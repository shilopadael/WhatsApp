package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.example.chatapp.Adapter.UsersAdapter;
import com.example.chatapp.Fragments.AddContactFragment;
import com.example.chatapp.Listeners.OnContactAddedListener;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.databinding.ActivityChatBinding;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements OnContactAddedListener {

    ActivityChatBinding binding;
    EditText contacts;
    private AppDB appDB;
    private ContactDao contactDao;

    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class,"Users2").
                allowMainThreadQueries().build();
        contactDao = appDB.contactsDao();


        //take the token and the username from the token class.
        //if there is no token go to the login




        RecyclerView lstUsers = binding.lstUsers;
        adapter = new UsersAdapter(this);
        lstUsers.setAdapter(adapter);
        lstUsers.setLayoutManager(new LinearLayoutManager(this));
        adapter.setList((ArrayList<Contact>)contactDao.index());

//        ArrayList<Contact> list = new ArrayList<>();
//        list.add(new Contact(R.drawable.ic_facebook, "Padael","Hola", 1, "dan","hey"));
//        list.add(new Contact(R.drawable.avatar3, "Padael","Hola", 1, "dan","hey"));
//        list.add(new Contact(R.drawable.avatar3, "Padael","Hola", 1, "dan","hey"));
//        list.add(new Contact(R.drawable.ic_google, "Padael","Hola", 1, "dan","hey"));
//        list.add(new Contact(R.drawable.avatar1, "Padael","Hola", 1, "dan","hey"));
//        list.add(new Contact(R.drawable.avatar3, "Padael","Hola", 1, "dan","hey"));
//        list.add(new Contact(R.drawable.send, "Padael","Hola", 1, "dan","hey"));
//        adapter.setList(list);


    }
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        AddContactFragment addContactFragment = new AddContactFragment();
        addContactFragment.setOnContactAddedListener(this);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_logout) {
                    // Handle logout action
                    return true;
                } else if (itemId == R.id.menu_add_contact) {

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, addContactFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onContactAdded() {
        adapter.setList((ArrayList<Contact>) contactDao.index());

    }
}