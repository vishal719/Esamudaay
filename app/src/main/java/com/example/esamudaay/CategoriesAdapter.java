package com.example.esamudaay;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.NewsViewHolder> {
    ArrayList<CategoriesModel> list;

    public CategoriesAdapter(ArrayList<CategoriesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_row_view, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        CategoriesModel model1 = list.get(position);
        holder.image.setImageResource(model1.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        CardView category;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            category= itemView.findViewById(R.id.card_category);
            image= itemView.findViewById(R.id.categoryimage);


        }
    }
}