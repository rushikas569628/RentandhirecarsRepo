package com.example.rentandhirecars;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserViewActivity extends AppCompatActivity {
    Button addCar;

    List<Cars> carlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final int ADD_VEHICLE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        // Initialize the car list
        fillCarList();

        // Handle "Add Car" button click
        addCar = findViewById(R.id.addCar);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserViewActivity.this, AddVechicleActivity.class);
                startActivityForResult(intent, ADD_VEHICLE_REQUEST_CODE);
            }
        });

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.carList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(carlist, UserViewActivity.this);
        recyclerView.setAdapter(mAdapter);


        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Define a map for menu item actions
        Map<Integer, Runnable> menuActions = new HashMap<>();

        // Logout logic
        menuActions.put(R.id.nav_logout, () -> {
            Toast.makeText(UserViewActivity.this, "Logout successful", Toast.LENGTH_SHORT).show();
            // Add your logout logic (e.g., clear user session, navigate to login page)
            startActivity(new Intent(UserViewActivity.this, LoginActivity.class));
        });

        // Chat navigation logic
        menuActions.put(R.id.nav_chat, () -> {
            Toast.makeText(UserViewActivity.this, "Chat selected", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserViewActivity.this, MessageActivity.class));
        });

        // About navigation logic
        menuActions.put(R.id.nav_about, () -> {
            Toast.makeText(UserViewActivity.this, "About selected", Toast.LENGTH_SHORT).show();
            // Navigate to AboutActivity
            Intent aboutIntent = new Intent(UserViewActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        });

        // Set a listener for item selection in the BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Runnable action = menuActions.get(item.getItemId());
            if (action != null) {
                action.run();
                return true; // Return true to indicate the event is handled
            }
            return false; // Return false for unhandled menu items
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_VEHICLE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String carName = data.getStringExtra("carName");
            String imageUrl = data.getStringExtra("imageUrl");
            String model = data.getStringExtra("Model");
            String mileage = data.getStringExtra("Milleage");
            // Create a new car object and add it to the list
            Cars newCar = new Cars(carlist.size(), carName, imageUrl, model, mileage);
            carlist.add(newCar);
            // Notify the adapter that the data set has changed
            mAdapter.notifyDataSetChanged();
        }
    }

    private void fillCarList() {
        Cars c1 = new Cars(1, "Hyundai Sonata", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/2020_Hyundai_Sonata_SEL_%28Quartz_White%29%2C_front_right.jpg/420px-2020_Hyundai_Sonata_SEL_%28Quartz_White%29%2C_front_right.jpg", "2018", "30");
        Cars c2 = new Cars(2, "Hyundai Elantra", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/Hyundai_Avante_CN7_white_%2810%29_%28cropped%29.jpg/420px-Hyundai_Avante_CN7_white_%2810%29_%28cropped%29.jpg", "2017", "35");
        Cars c3 = new Cars(3, "Honda City", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/2014_Honda_City_%28GM6_MY14%29_VTi_sedan_%282015-07-15%29_01.jpg/420px-2014_Honda_City_%28GM6_MY14%29_VTi_sedan_%282015-07-15%29_01.jpg", "2019", "30");
        Cars c4 = new Cars(4, "Honda Civic", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/2017_Honda_Civic_SR_VTEC_1.0_Front.jpg/420px-2017_Honda_Civic_SR_VTEC_1.0_Front.jpg", "2020", "35");
        Cars c5 = new Cars(5, "Honda Accord", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg/420px-Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg", "2021", "25");
        Cars c6 = new Cars(6, "Volkswagen Passat", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/VW_Passat_B8_Limousine_2.0_TDI_Highline.JPG/420px-VW_Passat_B8_Limousine_2.0_TDI_Highline.JPG", "2015", "28");
        Cars c7 = new Cars(7, "Volkswagen Vento", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Vw_vento_polo_sedan.png/420px-Vw_vento_polo_sedan.png", "2016", "30");
        Cars c8 = new Cars(8, "Skoda Rapid", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Skoda_Rapid.jpg/270px-Skoda_Rapid.jpg", "2017", "32");
        Cars c9 = new Cars(9, "Kia Seltos", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Kia_Seltos_SP2_PE_Snow_White_Pearl_%286%29_%28cropped%29.jpg/420px-Kia_Seltos_SP2_PE_Snow_White_Pearl_%286%29_%28cropped%29.jpg", "2018", "30");

        carlist.addAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
    }
}