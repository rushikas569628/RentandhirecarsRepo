package com.example.rentandhirecars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailRegister;
    private EditText passwordRegister;
    private EditText confirmPassword;
    private EditText fullName; // Added Full Name field
    private Button signUp;
    private SharedPreferences sharedPreferences;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Set up the toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button on the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_credentials", Context.MODE_PRIVATE);

        // Initialize views
        fullName = findViewById(R.id.fullName); // Initialize Full Name field
        emailRegister = findViewById(R.id.emailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        confirmPassword = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signUp);
        fAuth = FirebaseAuth.getInstance();

        // Check if the user is already logged in
        if (fAuth.getCurrentUser() != null) {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        // Set onClickListener for signUp button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString().trim(); // Get Full Name
                String email = emailRegister.getText().toString().trim();
                String password = passwordRegister.getText().toString();
                String confirm = confirmPassword.getText().toString();

                // Validate Full Name
                if (TextUtils.isEmpty(name)) {
                    fullName.setError("Full Name is required");
                    return;
                }

                if (!isValidName(name)) {
                    fullName.setError("Full Name must contain only alphabets");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    emailRegister.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordRegister.setError("Password is required");
                    return;
                }

                if (!isValidPassword(password)) {
                    passwordRegister.setError("Password must be at least 6 characters, including letters, numbers, and symbols");
                    return;
                }

                // Check if password and confirm password match
                if (!password.equals(confirm)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store the email, password, and full name in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("name", name); // Store Full Name
                editor.apply();

                // Register user with Firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    // Password validation for specific pattern
    private boolean isValidPassword(String password) {
        // Regex to check for at least one letter, one number, one special character, and 6+ characters
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{6,}$";
        return password.matches(passwordPattern);
    }

    // Full Name validation for alphabets only
    private boolean isValidName(String name) {
        // Regex to check for alphabets only
        String namePattern = "^[A-Za-z ]+$";
        return name.matches(namePattern);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}