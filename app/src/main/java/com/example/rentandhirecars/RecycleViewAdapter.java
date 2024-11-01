package com.example.rentandhirecars;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import java.util.Random;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    List<Cars> carsList;
    Button btn_book;
    Context context;
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

    public RecycleViewAdapter(List<Cars> carlist,Context context) {
        this.carsList=carlist;
        this.context=context;
    }

    @NonNull

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_car, parent, false);
//        return new MyViewHolder(view);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cars car = carsList.get(position);
        holder.tv_carName.setText(car.getName());
        // Load image from URL using Glide with a placeholder
        int randomIndex = new Random().nextInt(carImages.length);
        int randomImageResource = carImages[randomIndex];
        Glide.with(context)
                .load(randomImageResource)
                .placeholder(R.drawable.rent) // placeholder image resource
                .into(holder.img_car);

        holder.btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookingCarActivity.class);
                intent.putExtra("carName", car.getName());
                intent.putExtra("carImage", randomImageResource);
                intent.putExtra("Model", car.getModel());
                intent.putExtra("Milleage", car.getMilleage());
                context.startActivity(intent);
            }
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

            btn_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Redirect to the booking car activity
                    Intent intent = new Intent(context, BookingCarActivity.class);
                    // Pass any necessary data using intent.putExtra()
                    context.startActivity(intent);
                }
            });
        }
    }
}
