package com.example.esamudaay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.esamudaay.Adapter.VendorsAdapter;
import com.example.esamudaay.databinding.ActivityMainBinding;
import com.example.esamudaay.models.CategoriesModel;
import com.example.esamudaay.models.VendersModel;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;
    ArrayList<VendersModel> list,list2;
    VendorsAdapter adapter;
    FirebaseDatabase database;
    ArrayList<CategoriesModel> list1;



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

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }}

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

        binding.cardView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance("https://esamudaay-4ae43-default-rtdb.asia-southeast1.firebasedatabase.app/");
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list1 = new ArrayList<>();
        binding.notfound.setVisibility(View.INVISIBLE);
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbc", "Ravada Stores", R.drawable.ravada2,"Food",""));
        list.add(new VendersModel("4a1bc143-a3c0-4249-a942-f30b5801aa30", "Zara", R.drawable.zara_2,"Food","Clothing"));
        list.add(new VendersModel("c09f2b53-ae0d-435f-b428-761586c696a1", "Poorva", R.drawable.poorva,"Food","Medicine"));
        list.add(new VendersModel("e0aa3966-6880-42f8-84a6-ed31d3e349a2", "Bake master", R.drawable.bake_master,"Medicine","Food"));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbcs", "Burger Up", R.drawable.burgerup,"Food",""));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-w", "Home Grow", R.drawable.home_grow,"Food",""));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbc", "Sweets", R.drawable.ravada,"Food",""));
        list.add(new VendersModel("0635ecff-8fde-4185-8cd8-167efda42bbcd", "Noodlers", R.drawable.noodler,"Food",""));

        for(int i=0;i<list.size();i++){
            list2.add(list.get(i));
        }

        list1.add(new CategoriesModel("Furniture", binding.furnitureimage ,binding.furniture ));
        list1.add(new CategoriesModel("Clothing",binding.clothingimage ,binding.clothing));
        list1.add(new CategoriesModel("Electronics", binding.electimage ,binding.elect));
        list1.add(new CategoriesModel("Food", binding.foodimage ,binding.food));
        list1.add(new CategoriesModel("Mechanical", binding.mechanicalimage ,binding.mechanical));
        list1.add(new CategoriesModel("Medicine", binding.medimage ,binding.med));
        list1.add(new CategoriesModel("Shoe", binding.shoeimage ,binding.shoe));
        list1.add(new CategoriesModel("Wearable", binding.wearableimage ,binding.wearable));
        list1.add(new CategoriesModel("Fitness", binding.fitnessimage ,binding.fitness));

        binding.food.setCardBackgroundColor(Color.parseColor("#FF8400"));
        binding.foodimage.setColorFilter(Color.argb(255, 255, 255, 255));

        binding.furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.furniture.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.furnitureimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                list.clear();
                adapter.notifyDataSetChanged();
                binding.notfound.setVisibility(View.VISIBLE);

            }
        });

        binding.clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.clothing.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.clothingimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                binding.notfound.setVisibility(View.INVISIBLE);

                list.clear();
                adapter.notifyDataSetChanged();
                for(int j=0;j<list2.size();j++)
                {

                    String category1 = list2.get(j).getCategory1();
                    String category2 = list2.get(j).getCategory2();
                    if(category1.equals("Clothing") || category2.equals("Clothing"))
                        list.add(list2.get(j));

                }
                adapter.notifyDataSetChanged();
            }
        });

        binding.elect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.elect.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.electimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                list.clear();
                adapter.notifyDataSetChanged();
                binding.notfound.setVisibility(View.VISIBLE);

            }
        });

        binding.food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.food.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.foodimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                binding.notfound.setVisibility(View.INVISIBLE);

                list.clear();
                adapter.notifyDataSetChanged();
                for(int j=0;j<list2.size();j++)
                {

                    String category1 = list2.get(j).getCategory1();
                    String category2 = list2.get(j).getCategory2();
                    if(category1.equals("Food") || category2.equals("Food"))
                        list.add(list2.get(j));

                }
                adapter.notifyDataSetChanged();
            }
        });



        binding.mechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.mechanical.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.mechanicalimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                list.clear();
                adapter.notifyDataSetChanged();
                binding.notfound.setVisibility(View.VISIBLE);

            }
        });


        binding.med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.med.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.medimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                binding.notfound.setVisibility(View.INVISIBLE);

                list.clear();
                adapter.notifyDataSetChanged();
                for(int j=0;j<list2.size();j++)
                {

                    String category1 = list2.get(j).getCategory1();
                    String category2 = list2.get(j).getCategory2();
                    if(category1.equals("Medicine") || category2.equals("Medicine"))
                        list.add(list2.get(j));

                }
                adapter.notifyDataSetChanged();
            }
        });

        binding.shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.shoe.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.shoeimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                list.clear();
                adapter.notifyDataSetChanged();
                binding.notfound.setVisibility(View.VISIBLE);

            }
        });


        binding.wearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.wearable.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.wearableimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                list.clear();
                adapter.notifyDataSetChanged();
                binding.notfound.setVisibility(View.VISIBLE);

            }
        });


        binding.fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getImage().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.categoryfront));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#73D1D1D1"));

                    binding.fitness.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.fitnessimage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                list.clear();
                adapter.notifyDataSetChanged();
                binding.notfound.setVisibility(View.VISIBLE);

            }
        });
        adapter = new VendorsAdapter(list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        binding.vendorsList.setLayoutManager(mLayoutManager);
        binding.vendorsList.setAdapter(adapter);

        adapter.notifyDataSetChanged();



    }

}