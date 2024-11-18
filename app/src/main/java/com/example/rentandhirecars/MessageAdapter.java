package com.example.rentandhirecars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messageList;

    // Constructor to initialize the message list
    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for messages
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);

        // Set the message text based on the sender
        holder.textViewMessage.setText(message.getContent());

        // Check the sender and set the background resource accordingly
        if (message.getSender().equals("user")) {
            holder.textViewMessage.setBackgroundResource(R.drawable.user_message_background); // Custom background for user message
            holder.textViewMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END); // Align text to the end (right)
        } else {
            holder.textViewMessage.setBackgroundResource(R.drawable.bot_message_background); // Custom background for bot message
            holder.textViewMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START); // Align text to the start (left)
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // ViewHolder class to hold the message item view
    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
        }
    }
}