package com.example.esamudaay.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.esamudaay.R;
import java.util.ArrayList;

public class FailureAdapter extends RecyclerView.Adapter<FailureAdapter.Notes> {
    public FailureAdapter(ArrayList<String> list) {
        this.list = list;
    }

    ArrayList<String> list;
    @NonNull
    @Override
    public Notes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.failure_rowview,parent,false);
        return new Notes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notes holder, int position) {
        holder.text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Notes extends RecyclerView.ViewHolder {
    TextView text;

    public Notes(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.zzzz);
    }
}}