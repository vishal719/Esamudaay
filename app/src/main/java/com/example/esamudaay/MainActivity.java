package com.example.esamudaay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.esamudaay.databinding.ActivityGetStartedBinding;
import com.example.esamudaay.Adapter.VendorsAdapter;
import com.example.esamudaay.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import com.example.esamudaay.models.VendersModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<VendersModel> list;
    VendorsAdapter adapter;
    FirebaseDatabase database;



    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideStatus() {
        /* To make the status bar transparent*/

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();

        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbc", "Ravada Stores", R.drawable.ravada));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbcd", "Noodlers", R.drawable.noodler));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbcs", "Burger Up", R.drawable.burgerup));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-w", "Home Grow", R.drawable.home_grow));


        database = FirebaseDatabase.getInstance("https://esamudaay-4ae43-default-rtdb.asia-southeast1.firebasedatabase.app/");

        ArrayList<CategoriesModel> list1 = new ArrayList<>();
        list1.add(new CategoriesModel("Furniture", R.drawable.cabinet));
        list1.add(new CategoriesModel("Clothing", R.drawable.clothing));
        list1.add(new CategoriesModel("Electronics", R.drawable.computer));
        list1.add(new CategoriesModel("Food", R.drawable.food));
        list1.add(new CategoriesModel("Mechanical", R.drawable.mechanical));
        list1.add(new CategoriesModel("Medicine", R.drawable.medicine));
        list1.add(new CategoriesModel("Shoe", R.drawable.shoe));
        list1.add(new CategoriesModel("Wearable", R.drawable.wearable));
        list1.add(new CategoriesModel("Fitness", R.drawable.weightlifting));

        CategoriesAdapter adapter1 = new CategoriesAdapter(list1);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        binding.recyclerView3.setLayoutManager(manager);
        binding.recyclerView3.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();




        adapter = new VendorsAdapter(list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        binding.vendorsList.setLayoutManager(mLayoutManager);
        binding.vendorsList.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }
}