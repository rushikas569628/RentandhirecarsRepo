package com.example.rentandhirecars;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        backButton = findViewById(R.id.backButton);

        // Display about information
        TextView aboutTextView = findViewById(R.id.aboutTextView);
        aboutTextView.setText("Welcome to Rent and Hire Cars! \n\nWe offer the best car rental and hiring services for all your travel needs. Explore our collection of cars, add your preferences, and enjoy a smooth ride!");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the PaymentsActivity
                Intent intent = new Intent(AboutActivity.this, UserViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}