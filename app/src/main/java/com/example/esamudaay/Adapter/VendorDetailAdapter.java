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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esamudaay.R;
import com.example.esamudaay.models.CategoriesModel;
import com.example.esamudaay.models.VendorDetailModel;


import java.util.ArrayList;
import java.util.Random;

public class VendorDetailAdapter extends RecyclerView.Adapter<VendorDetailAdapter.NewsViewHolder> {
    ArrayList<VendorDetailModel> list;
    ArrayList<Integer> pic = new ArrayList<>();
    private Random randomGenerator;

    public VendorDetailAdapter(ArrayList<VendorDetailModel> list, ArrayList<Integer> pic) {
        this.list = list;
        this.pic=pic;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendors_detail_row_view, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder,  int position) {
        VendorDetailModel model1 = list.get(position);
        holder.name.setText(model1.getProductname());
        holder.vendor.setText(model1.getBusinessname());
        holder.sku.setText("Sku : " + model1.getSkuid());
        randomGenerator= new Random();
        int index = randomGenerator.nextInt(pic.size());

        holder.vendorimage.setImageResource(pic.get(index));
        if(!model1.getFailurereasons().get(0).equals("null")) {
            holder.complaince.setText("Error!");
            holder.complaince.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.unavailable_button));
            holder.complaince.setTextColor(Color.parseColor("#FA4627"));

        }
        else
            holder.complaince.setText("Approved");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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