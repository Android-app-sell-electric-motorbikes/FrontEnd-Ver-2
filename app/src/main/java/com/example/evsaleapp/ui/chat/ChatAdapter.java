package com.example.evsaleapp.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.evsaleapp.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context context;
    private List<ChatMessage> messageList;
    private String currentUserId;

    public ChatAdapter(Context context, List<ChatMessage> messageList, String currentUserId) {
        this.context = context;
        this.messageList = messageList;
        this.currentUserId = currentUserId;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage msg = messageList.get(position);
        holder.txtMessage.setText(msg.getMessage());
        holder.txtTime.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(msg.getTimestamp())));

        // chỉnh style cho người gửi/người nhận
        if (msg.getSenderId().equals(currentUserId)) {
            holder.txtMessage.setBackgroundResource(R.drawable.message_bg_right);
            ((ViewGroup) holder.txtMessage.getParent()).setGravity(android.view.Gravity.END);
        } else {
            holder.txtMessage.setBackgroundResource(R.drawable.message_bg_left);
            ((ViewGroup) holder.txtMessage.getParent()).setGravity(android.view.Gravity.START);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage, txtTime;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
