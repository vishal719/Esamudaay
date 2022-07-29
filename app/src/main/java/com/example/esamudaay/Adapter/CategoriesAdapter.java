package com.example.esamudaay.Adapter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esamudaay.R;
import com.example.esamudaay.models.CategoriesModel;


import java.util.ArrayList;

//public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.NewsViewHolder> {
//    ArrayList<CategoriesModel> list;
//    private RecyclerviewClickInterface recyclerviewClickInterface;
//
//
//    public CategoriesAdapter(ArrayList<CategoriesModel> list,RecyclerviewClickInterface recyclerviewClickInterface) {
//        this.list = list;
//        this.recyclerviewClickInterface = recyclerviewClickInterface;
//    }
//
//    @NonNull
//    @Override
//    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_row_view, parent, false);
//        return new NewsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        CategoriesModel model1 = list.get(position);
//
//
//        if(position==0)
//        {
//            holder.image.setColorFilter(Color.argb(255, 255, 255, 255));
//            holder.category.setCardBackgroundColor(Color.parseColor("#FF8400"));
//        }
//        holder.image.setImageResource(model1.getImage());
//        holder.category.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                recyclerviewClickInterface.onItemClick(v,position);
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class NewsViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView image;
//        CardView category;
//        public NewsViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            category= itemView.findViewById(R.id.card_category);
//            image= itemView.findViewById(R.id.categoryimage);
//            category.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    recyclerviewClickInterface.onItemClick(view, getAdapterPosition());
//                }
//            });
//
//
//        }
//    }
//}