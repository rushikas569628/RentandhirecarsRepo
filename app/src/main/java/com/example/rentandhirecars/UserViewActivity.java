package com.example.rentandhirecars;


import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserViewActivity extends AppCompatActivity {
    Button addCar;

    List<Cars> carlist = new ArrayList<Cars>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final int ADD_VEHICLE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        addCar = findViewById(R.id.addCar);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserViewActivity.this, AddVechicleActivity.class);
                startActivityForResult(intent, ADD_VEHICLE_REQUEST_CODE);
            }
        });

        recyclerView = findViewById(R.id.carList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //mAdapter = new RecycleViewAdapter(carlist, UserViewActivity.this);
        //recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_VEHICLE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String carName = data.getStringExtra("carName");
            String imageUrl = data.getStringExtra("imageUrl");
            String Model = data.getStringExtra("Model");
            String Milleage = data.getStringExtra("Milleage");
            // Create a new car object and add it to the list
            Cars newCar = new Cars(carlist.size(), carName, imageUrl,Model,Milleage);
            carlist.add(newCar);
            // Notify the adapter that the data set has changed
            mAdapter.notifyDataSetChanged();
        }
    }
}

