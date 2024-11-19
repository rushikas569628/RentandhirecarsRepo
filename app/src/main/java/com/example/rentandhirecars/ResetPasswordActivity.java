package com.example.rentandhirecars;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button resetButton;
    private Button backButton; // Declare the back button
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Initialize views
        emailInput = findViewById(R.id.email_input);
        resetButton = findViewById(R.id.reset_button);
        backButton = findViewById(R.id.back_button);
        mAuth = FirebaseAuth.getInstance();

        // Set the reset password button's onClickListener
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = emailInput.getText().toString().trim();

                if (!TextUtils.isEmpty(userEmail)) {
                    resetPassword(userEmail); // Pass userEmail to the resetPassword method
                } else {
                    emailInput.setError("Email field can't be empty");
                }
            }
        });

        // Set the back button's onClickListener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to LoginActivity
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Method to reset the password
    private void resetPassword(String userEmail) {
        resetButton.setVisibility(View.INVISIBLE); // Disable button to prevent multiple clicks
        mAuth.sendPasswordResetEmail(userEmail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ResetPasswordActivity.this, "Password reset instructions sent to " + userEmail, Toast.LENGTH_SHORT).show();
                        // Redirect to LoginActivity after success
                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPasswordActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        resetButton.setVisibility(View.VISIBLE); // Re-enable button on failure
                    }
                });
    }
}
