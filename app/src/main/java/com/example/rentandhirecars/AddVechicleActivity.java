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
        clickListenHandler();
        initComponents();
    }
    public void initComponents() {
//Register Button
        back = findViewById(R.id.back);
        addBTN = findViewById(R.id.addBTN);
        carNameET = findViewById(R.id.carNameET);
        imageURL = findViewById(R.id.ET_model);
        ET_km = findViewById(R.id.ET_km);
        ET_model= findViewById(R.id.ET_model);
    }
    public void add(View v) {
        String carName = carNameET.getText().toString();
        String imageUrl = imageURL.getText().toString();
        String ETkm = ET_km.getText().toString();
        String ETmodel = ET_model.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("carName", carName);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("Model",ETmodel);
        intent.putExtra("Milleage",ETkm);

        setResult(RESULT_OK, intent);
        finish();
    }
    private void clickListenHandler() {
        back = findViewById(R.id.back);
//back Listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Start the UserViewActivity
                Intent intent = new Intent(AddVechicleActivity.this, UserViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
