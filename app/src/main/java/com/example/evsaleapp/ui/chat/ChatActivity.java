package com.example.evsaleapp.ui.chat;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.evsaleapp.R;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editMessage;
    private ImageButton btnSend;

    private DatabaseReference chatRef;
    private List<ChatMessage> messageList;
    private ChatAdapter adapter;
    private String currentUserId = "user1"; // giả lập user hiện tại
    private String storeId = "store_admin"; // ID cửa hàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerViewChat);
        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);

        messageList = new ArrayList<>();
        adapter = new ChatAdapter(this, messageList, currentUserId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        chatRef = FirebaseDatabase.getInstance().getReference("chats");

        loadMessages();

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = editMessage.getText().toString().trim();
        if (text.isEmpty()) return;

        ChatMessage msg = new ChatMessage(currentUserId, storeId, text, System.currentTimeMillis());
        chatRef.push().setValue(msg);
        editMessage.setText("");
    }

    private void loadMessages() {
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    ChatMessage msg = snap.getValue(ChatMessage.class);
                    if (msg != null &&
                            ((msg.getSenderId().equals(currentUserId) && msg.getReceiverId().equals(storeId)) ||
                                    (msg.getSenderId().equals(storeId) && msg.getReceiverId().equals(currentUserId)))) {
                        messageList.add(msg);
                    }
                }
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messageList.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}
