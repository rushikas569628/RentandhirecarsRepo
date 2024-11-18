package com.example.rentandhirecars;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    List<Cars> carsList;
    Context context;

    public RecycleViewAdapter(List<Cars> carlist, Context context) {
        this.carsList = carlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_car, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cars car = carsList.get(position);

        // Set the car name
        holder.tv_carName.setText(car.getName());

        // Match the car name to its respective image
        int carImageResource = getCarImageResource(car.getName());
        Glide.with(context)
                .load(carImageResource)
                .placeholder(R.drawable.rent) // Placeholder image while loading
                .into(holder.img_car);

        // Set click listener for booking button
        holder.btn_book.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookingCarActivity.class);
            intent.putExtra("carName", car.getName());
            intent.putExtra("carImage", carImageResource);
            intent.putExtra("Model", car.getModel());
            intent.putExtra("Milleage", car.getMilleage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_car;
        TextView tv_carName;
        Button btn_book;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_car = itemView.findViewById(R.id.img_car);
            tv_carName = itemView.findViewById(R.id.tv_carName);
            btn_book = itemView.findViewById(R.id.btn_book);
        }
    }

    /**
     * Helper method to match car names with their respective images.
     */
    private int getCarImageResource(String carName) {
        switch (carName) {
            case "Honda Accord":
                return R.drawable.accord1;
            case "Honda City":
                return R.drawable.city1;
            case "Honda Civic":
                return R.drawable.civic1;
            case "Hyundai Elantra":
                return R.drawable.elantra1;
            case "Volkswagen Passat":
                return R.drawable.passat1;
            case "Skoda Rapid":
                return R.drawable.rapid1;
            case "Kia Seltos":
                return R.drawable.seltos1;
            case "Hyundai Sonata":
                return R.drawable.sonata1;
            case "Volkswagen Vento":
                return R.drawable.vento1;
            default:
                return R.drawable.rent; // Default image
        }
    }
}
