package com.example.rentandhirecars;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    private Button back;
    private Button btnpay,btndsubmit;
    private EditText ETdays;
    private TextView TVprice;
    private EditText ETcardnum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ETdays = findViewById(R.id.ETdays);
        TVprice = findViewById(R.id.TVprice);
        initComponents();
        clickListenHandler();

    }

    public void initComponents() {
        //Register Button
        back = findViewById(R.id.back1);
        btnpay = findViewById(R.id.btnpay);

    }
    private void clickListenHandler() {
        back = findViewById(R.id.back1);
        btnpay = findViewById(R.id.btnpay);
        btndsubmit = findViewById(R.id.btnprice);
        ETdays = findViewById(R.id.ETdays);
        TVprice = findViewById(R.id.TVprice);
        ETcardnum = findViewById(R.id.ETcardnum);
        btndsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int days = Integer.parseInt(ETdays.getText().toString());
                int price = 100 + (days - 1) * 100;
                TVprice.setText("$" + price);
            }
        });
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
            }
        });

        //login Listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the UserViewActivity
                Intent intent = new Intent(PaymentActivity.this, UserViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
