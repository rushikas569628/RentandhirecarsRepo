package com.example.rentandhirecars;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookingCarActivity extends AppCompatActivity {
    private Button bookingCar;
    private TextView carNameTextView;
    private TextView tvmodel, tvkm;
    private ImageView carImageView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_car);
        initcompo();
        clickListenHandler();

        // Retrieve intent data
        Intent intent = getIntent();
        if (intent != null) {
            String carName = intent.getStringExtra("carName");
            String model = intent.getStringExtra("Model");
            String mileage = intent.getStringExtra("Milleage");
            int carImageResource = intent.getIntExtra("carImage", R.drawable.rent);

            // Set car details
            carNameTextView.setText("Name: " + carName);
            tvmodel.setText("Model: " + model);
            tvkm.setText("Mileage: " + mileage);

            // Load car image using Picasso
            Picasso.get().load(carImageResource).placeholder(R.drawable.rent).into(carImageView);
        }
    }

    public void initcompo() {
        tvmodel = findViewById(R.id.tv_model);
        tvkm = findViewById(R.id.tv_km);
        carNameTextView = findViewById(R.id.carNameTextView);
        carImageView = findViewById(R.id.carImageView);
        bookingCar = findViewById(R.id.bookNow);
        backButton = findViewById(R.id.backButton);
    }

    private void clickListenHandler() {
        // Booking Listener
        bookingCar.setOnClickListener(v -> {
            Intent intent = new Intent(BookingCarActivity.this, PaymentActivity.class);
            startActivity(intent);
        });

        // Back button listener
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookingCarActivity.this, UserViewActivity.class);
            startActivity(intent);
        });
    }
}
