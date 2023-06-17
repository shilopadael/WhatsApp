package com.example.chatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.chatapp.ChatDetailActivity;
//import com.example.chatapp.Models.AllChatsEntity.AllChatsDao;
import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.R;
import com.example.chatapp.Schemes.Chat;
import com.example.chatapp.SignInActivity;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    ArrayList<Contact> list;
    String username;
    Context context;


    public UsersAdapter(Context context, String username) {
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppDB appDB;

        appDB = Room.databaseBuilder(context, AppDB.class, username).
                allowMainThreadQueries().build();


        if (list != null) {
            Contact current = list.get(position);
            holder.userName.setText(current.getUsername());
            holder.lastMessage.setText(current.getLastMessage());
            holder.image.setImageResource(current.getProfilePic());
            holder.time.setText(current.getLastMessageTime());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChatDetailActivity.class);
                intent.putExtra("username", current.getUsername()); // Pass the selected user to the ChatDetailActivity
                intent.putExtra("profilePic", current.getProfilePic());
                intent.putExtra("userId", current.getUserId());

                //check if there a chat open already if not create new one for that user

                context.startActivity(intent);
            });


        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setList(ArrayList<Contact> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView userName, lastMessage, time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.userNameList);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            time = itemView.findViewById(R.id.userContactTime);

        }
    }
}
