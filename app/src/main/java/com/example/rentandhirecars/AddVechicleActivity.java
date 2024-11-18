package com.example.rentandhirecars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class AddVechicleActivity extends AppCompatActivity {
    private Button back;
    private Button addBTN;
    private EditText carNameET;
    private EditText imageURL;
    private EditText ET_km;
    private EditText ET_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vechicle);
        initComponents();
        clickListenHandler();
    }

    public void initComponents() {
        // Register Button
        back = findViewById(R.id.back);
        addBTN = findViewById(R.id.addBTN);
        carNameET = findViewById(R.id.carNameET);
        imageURL = findViewById(R.id.ET_model);
        ET_km = findViewById(R.id.ET_km);
        ET_model = findViewById(R.id.ET_model);
    }

    public void add(View v) {
        // Retrieve text from inputs
        String carName = carNameET.getText().toString().trim();
        String imageUrl = imageURL.getText().toString().trim();
        String ETkm = ET_km.getText().toString().trim();
        String ETmodel = ET_model.getText().toString().trim();

        // Validation logic
        boolean isValid = true;

        if (carName.isEmpty()) {
            carNameET.setError("Car Name is required");
            isValid = false;
        }
        if (imageUrl.isEmpty()) {
            imageURL.setError("Image URL is required");
            isValid = false;
        }
        if (ETkm.isEmpty()) {
            ET_km.setError("Mileage is required");
            isValid = false;
        }
        if (ETmodel.isEmpty()) {
            ET_model.setError("Model is required");
            isValid = false;
        }

        // If all inputs are valid, proceed; otherwise, stay on the page
        if (isValid) {
            Intent intent = new Intent();
            intent.putExtra("carName", carName);
            intent.putExtra("imageUrl", imageUrl);
            intent.putExtra("Model", ETmodel);
            intent.putExtra("Milleage", ETkm);

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void clickListenHandler() {
        // Back Button Listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });
    }
}
