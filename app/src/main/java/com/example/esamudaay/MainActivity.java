package com.example.esamudaay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.esamudaay.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            hideStatus();
        database = FirebaseDatabase.getInstance("https://esamudaay-4ae43-default-rtdb.asia-southeast1.firebasedatabase.app/");

        ArrayList<CategoriesModel> list = new ArrayList<>();
        list.add(new CategoriesModel("Furniture", R.drawable.cabinet));
        list.add(new CategoriesModel("Clothing", R.drawable.clothing));
        list.add(new CategoriesModel("Electronics", R.drawable.computer));
        list.add(new CategoriesModel("Food", R.drawable.food));
        list.add(new CategoriesModel("Mechanical", R.drawable.mechanical));
        list.add(new CategoriesModel("Medicine", R.drawable.medicine));
        list.add(new CategoriesModel("Shoe", R.drawable.shoe));
        list.add(new CategoriesModel("Wearable", R.drawable.wearable));
        list.add(new CategoriesModel("Fitness", R.drawable.weightlifting));

        CategoriesAdapter adapter= new CategoriesAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        binding.recyclerView3.setLayoutManager(manager);
        binding.recyclerView3.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
}