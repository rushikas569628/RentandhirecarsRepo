package com.example.rentandhirecars;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.Random;
public class BookingCarActivity extends AppCompatActivity {
    private Button bookingCar;
    private TextView carNameTextView;
    private TextView tvmodel,tvkm;
    private ImageView carImageView;
    private Button backButton;

    private int[] carImages = {
            R.drawable.accord1,
            R.drawable.city1,
            R.drawable.civic1,
            R.drawable.elantra1,
            R.drawable.passat1,
            R.drawable.rapid1,
            R.drawable.rent,
            R.drawable.seltos1,
            R.drawable.sonata1,
            R.drawable.vento1
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_car);
        initcompo();
        clickListenHandler();
        Intent intent = getIntent();
        if (intent != null) {
            String carName = intent.getStringExtra("carName");
            String carImage = intent.getStringExtra("carImage");
            String model = intent.getStringExtra("Model");
            String km = intent.getStringExtra("Milleage");
            carNameTextView.setText("Name: "+carName);
            tvmodel.setText("Model: "+model);
            tvkm.setText("Mileage: "+km);
// Generate a random index to select a random photo
            int randomIndex = new Random().nextInt(carImages.length);
            Picasso.get().load(carImages[randomIndex]).into(carImageView);
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
        bookingCar=findViewById(R.id.bookNow);

//booking Listener
        bookingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Start the UserViewActivity
                Intent intent = new Intent(BookingCarActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        // Listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the activity to go back
            }
        });
    }
}