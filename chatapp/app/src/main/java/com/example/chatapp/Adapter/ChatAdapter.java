package com.example.chatapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.chatapp.Models.AppDB;
import com.example.chatapp.Models.ContactEntity.Contact;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Models.TokenEntity.Token;
import com.example.chatapp.Models.TokenEntity.TokenDao;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.R;

import java.util.ArrayList;

public class ChatAdapter extends  RecyclerView.Adapter{

    ArrayList<Message> messages;
    Context context;
    AppDB appDB;
    TokenDao tokenDao;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == SENDER_VIEW_TYPE) {
                View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
                return new SenderViewHolder(view);
            }
            else {
                View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver, parent, false);
                return new RecieverViewHolder(view);

            }
    }

    @Override
    public int getItemViewType(int position) {
        appDB = Room.databaseBuilder(context, AppDB.class,"Users2").
                allowMainThreadQueries().build();
        tokenDao = appDB.tokenDao();
        if (messages.get(position).getSender().getUsername().equals(tokenDao.getToken().getUserName())){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageDao messageDao = appDB.messageDao();
        Message message = messages.get(position);

        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder) holder).senderMsg.setText(message.getContent());
        } else {
            ((RecieverViewHolder)holder).receiverMsg.setText(message.getContent());
        }


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setList(ArrayList<Message> list) {
        this.messages = list;
        notifyDataSetChanged();
    }
    public void addMessageToList(Message message){
        this.messages.add(message);
        notifyDataSetChanged();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsg ,receiverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMsg = itemView.findViewById(R.id.reciverText);
            receiverTime = itemView.findViewById(R.id.receiverTime);
        }
    }
    public class SenderViewHolder extends  RecyclerView.ViewHolder{
        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }
}
