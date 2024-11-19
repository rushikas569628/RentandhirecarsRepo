package com.example.rentandhirecars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    private Button back, chat, btnpay, btndsubmit;
    private EditText ETdays, ETcardnum, ETname;
    private TextView TVprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ETdays = findViewById(R.id.ETdays);
        TVprice = findViewById(R.id.TVprice);
        ETname = findViewById(R.id.ETname);
        ETcardnum = findViewById(R.id.ETcardnum);

        initComponents();
        clickListenHandler();
    }

    public void initComponents() {
        back = findViewById(R.id.back1);
        btnpay = findViewById(R.id.btnpay);
    }

    private void clickListenHandler() {
        back = findViewById(R.id.back1);
        chat = findViewById(R.id.chat);
        btnpay = findViewById(R.id.btnpay);
        btndsubmit = findViewById(R.id.btnprice);
        ETdays = findViewById(R.id.ETdays);
        TVprice = findViewById(R.id.TVprice);
        ETcardnum = findViewById(R.id.ETcardnum);
        ETname = findViewById(R.id.ETname);

        btndsubmit.setOnClickListener(v -> {
            int days;
            try {
                days = Integer.parseInt(ETdays.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(PaymentActivity.this, "Please enter valid number of days", Toast.LENGTH_SHORT).show();
                return;
            }
            int price = 100 + (days - 1) * 100;
            TVprice.setText("$" + price);
        });

        btnpay.setOnClickListener(v -> {
            String name = ETname.getText().toString().trim();
            String cardNum = ETcardnum.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                ETname.setError("Name is required");
                return;
            }
            if (TextUtils.isEmpty(cardNum)) {
                ETcardnum.setError("Card number is required");
                return;
            }
            if (cardNum.length() != 12 || !cardNum.matches("\\d+")) {
                ETcardnum.setError("Card number must be 12 digits");
                return;
            }

            Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, BookingCarActivity.class);
            startActivity(intent);
        });

        chat.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, MessageActivity.class);
            startActivity(intent);
        });
    }
}