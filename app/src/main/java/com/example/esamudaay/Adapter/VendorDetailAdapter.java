package com.example.esamudaay.Adapter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esamudaay.R;
import com.example.esamudaay.models.CategoriesModel;
import com.example.esamudaay.models.VendorDetailModel;


import java.util.ArrayList;

public class VendorDetailAdapter extends RecyclerView.Adapter<VendorDetailAdapter.NewsViewHolder> {
    ArrayList<VendorDetailModel> list;


    public VendorDetailAdapter(ArrayList<VendorDetailModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendors_detail_row_view, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VendorDetailModel model1 = list.get(position);
        holder.name.setText(model1.getProductname());
        holder.vendor.setText(model1.getBusinessname());
        holder.sku.setText(model1.getSkuid());

        if(model1.getFailurereasons().size()>0) {
            holder.complaince.setText("Error!");

        }
        else
            holder.complaince.setText("Approved");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView vendorimage;
        TextView complaince, name, sku, vendor;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            vendorimage= itemView.findViewById(R.id.vendor_image);
            name= itemView.findViewById(R.id.product_name);
            vendor= itemView.findViewById(R.id.vendor_name);
            sku= itemView.findViewById(R.id.sku);
            complaince= itemView.findViewById(R.id.complaince);


        }
    }
}