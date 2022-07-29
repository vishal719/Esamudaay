package com.example.esamudaay.Adapter;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!model1.getFailurereasons().get(0).equals("null")){

                    FailureAdapter adapter = new FailureAdapter(model1.getFailurereasons());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);


                    Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.error_dialog);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);

                dialog.show();


                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                            dialog.cancel();
                            return true;
                        }
                        return false;
                    }
                });
            }
            }
        });

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