package com.example.chatapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends  RecyclerView.Adapter{

    List<Message> messages;
    String chatId;
    Context context;
    SharedPreferences sharedPreferences;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(Context context, String chatId) {
        this.messages = null;
        this.context = context;
        this.chatId = chatId;
        this.sharedPreferences = context.getSharedPreferences("chatSystem", Context.MODE_PRIVATE);
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
        String username = sharedPreferences.getString("username", "#");
        if (messages.get(position).getSender().equals(username)){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder) holder).senderMsg.setText(message.getContent());
            ((SenderViewHolder) holder).senderTime.setText(message.getCreated());
        } else {
            ((RecieverViewHolder)holder).receiverMsg.setText(message.getContent());
            ((RecieverViewHolder)holder).receiverTime.setText(message.getCreated());
        }


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setList(List<Message> list) {
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
