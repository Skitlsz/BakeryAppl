package com.example.bakeryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Messaging extends ArrayAdapter<Message> {
    private static final int VIEW_TYPE_SENDER = 0;
    private static final int VIEW_TYPE_RECEIVER = 1;

    private DatabaseReference messagesRef;

    public Messaging(Context context, List<Message> messages) {
        super(context, 0, messages);
        messagesRef = FirebaseDatabase.getInstance().getReference("messages");
    }

    public Messaging(Context context) {
        super(context, 0);
        messagesRef = FirebaseDatabase.getInstance().getReference("messages");
    }

    @Override
    public int getItemViewType(int position) {
        Message message = getItem(position);
        if (message.isSentBySender()) {
            return VIEW_TYPE_SENDER;
        } else {
            return VIEW_TYPE_RECEIVER;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2; // Number of view types (sender and receiver)
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        Message message = getItem(position);

        if (convertView == null) {
            int layoutRes = (viewType == VIEW_TYPE_SENDER) ? R.layout.item_message_sender : R.layout.item_message_receiver;
            convertView = LayoutInflater.from(getContext()).inflate(layoutRes, parent, false);
        }

        TextView messageTextView = convertView.findViewById(R.id.messageTextView);
        messageTextView.setText(message.getText());

        // Save the message to Firebase
        messagesRef.push().setValue(message);

        return convertView;
    }
}
