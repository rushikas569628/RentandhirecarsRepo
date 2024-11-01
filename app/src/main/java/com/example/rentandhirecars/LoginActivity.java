package com.example.rentandhirecars;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    private TextView register;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
// Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_credentials", Context.MODE_PRIVATE);
// Initialize views
        register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
// Set onClickListener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
// Retrieve stored email and password from SharedPreferences
                String storedEmail = sharedPreferences.getString("email", "");
                String storedPassword = sharedPreferences.getString("password", "");
// Check if email and password are empty
                if (userEmail.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
// Check if the entered email and password match the stored credentials
                if (userEmail.equals(storedEmail) && userPassword.equals(storedPassword)) {
// Start the UserViewActivity if login is successful
                    Intent intent = new Intent(LoginActivity.this, UserViewActivity.class);
                    startActivity(intent);
                    finish(); // Finish current activity to prevent going back to login page
                } else {
// Show an error message if login fails
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
// Set onClickListener for register TextView
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}