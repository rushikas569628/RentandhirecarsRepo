package com.example.rentandhirecars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private EditText editTextMessage;
    private Button buttonSend;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Initialize views
        recyclerView = findViewById(R.id.chatRecyclerView);
        editTextMessage = findViewById(R.id.userMessageInput);
        buttonSend = findViewById(R.id.buttonSend);
        backButton = findViewById(R.id.backButton);

        // Set up RecyclerView
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        // Send button click listener
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = editTextMessage.getText().toString().trim();
                if (!userMessage.isEmpty()) {
                    // Add user message to the list
                    messageList.add(new Message("user", userMessage));
                    messageAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerView.scrollToPosition(messageList.size() - 1);

                    // Generate a chatbot response
                    generateBotResponse(userMessage);

                    // Clear the input field
                    editTextMessage.setText("");
                }
            }
        });

        // Back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the PaymentsActivity
                Intent intent = new Intent(MessageActivity.this, PaymentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void generateBotResponse(String userMessage) {
        // Simple bot response logic with prebuilt answers
        String botResponse;

        if (userMessage.toLowerCase().contains("how to rent a car")) {
            botResponse = "To rent a car, go to the Rent Car page, choose your desired car, select the dates, and confirm your booking.";
        } else if (userMessage.toLowerCase().contains("how to list my car")) {
            botResponse = "To list your car, go to the Car Listing page, fill in your car details, set a price, and publish it for others to rent.";
        } else if (userMessage.toLowerCase().contains("payment options")) {
            botResponse = "We accept various payment methods, including credit cards and digital wallets. Check the Payment page for more details.";
        } else if (userMessage.toLowerCase().contains("cancellation policy")) {
            botResponse = "You can cancel your booking up to 24 hours before the rental start time for a full refund. Check our Cancellation Policy for details.";
        } else {
            botResponse = "I'm here to help! Please ask me about renting, listing cars, payments, or our policies.";
        }

        // Add the bot response to the message list
        messageList.add(new Message("bot", botResponse));
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }
}