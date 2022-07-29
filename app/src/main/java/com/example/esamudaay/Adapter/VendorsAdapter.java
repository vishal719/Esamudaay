package com.example.esamudaay.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.esamudaay.R;
import com.example.esamudaay.models.VendersModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VendorsAdapter extends RecyclerView.Adapter<VendorsAdapter.viewHolder> {

    ArrayList<VendersModel> list;

    public VendorsAdapter(ArrayList<VendersModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendors_row_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        VendersModel model = list.get(position);

        Glide.with(holder.itemView.getContext()).load(model.getImage()).into(holder.image);
        holder.name.setText(model.getName());

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

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.vendor_image);
            name = itemView.findViewById(R.id.vendor_name);
        }
    }
}
