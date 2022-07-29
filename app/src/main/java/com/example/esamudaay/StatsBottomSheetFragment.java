package com.example.esamudaay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esamudaay.models.VendorDetailModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class StatsBottomSheetFragment extends BottomSheetDialogFragment {
    TextView one, two, tvCPP, tvJava;
    PieChart pieChart;
    int size;
    int complaint=0, noncomplaint=0;
ArrayList<VendorDetailModel> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats_bottom_sheet, container, false);

        one = view.findViewById(R.id.complaintpie);
        two = view.findViewById(R.id.noncompliantpie);
        pieChart = view.findViewById(R.id.piechart);
        list =new ArrayList<>();

        VendorDetailActivity activity = (VendorDetailActivity) getActivity();
        list= activity.getlist();

        size=list.size();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getFailurereasons().get(0).equals("null"))
            {
               complaint++;
            }
            else
                noncomplaint++;
        }
        pieChart.addPieSlice(
                new PieModel(
                        "Compliant",
                        Integer.parseInt(String.valueOf(complaint)),
                        Color.parseColor("#00DD9E")));
        pieChart.addPieSlice(
                new PieModel(
                        "Non-Compliant",
                        Integer.parseInt(String.valueOf(noncomplaint)),
                        Color.parseColor("#DE0000")));
        pieChart.startAnimation();
        return  view;
    }
}