package com.example.rentandhirecars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailInput = findViewById(R.id.email_input);
        resetButton = findViewById(R.id.reset_button);

        // Set the reset password button's onClickListener
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = emailInput.getText().toString().trim();

                if (userEmail.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // For simplicity, we will simulate password reset
                // You can add more logic here to send an actual password reset request (e.g., email API call)

                Toast.makeText(ResetPasswordActivity.this, "Password reset instructions sent to " + userEmail, Toast.LENGTH_SHORT).show();

                // Redirect to LoginActivity after resetting password
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();  // Finish the reset password activity
            }
        });
    }
}