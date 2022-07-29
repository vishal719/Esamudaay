package com.example.esamudaay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.esamudaay.Adapter.VendorDetailAdapter;
import com.example.esamudaay.Adapter.VendorsAdapter;
import com.example.esamudaay.databinding.ActivityMainBinding;
import com.example.esamudaay.databinding.ActivityVendorDetailBinding;
import com.example.esamudaay.models.CategoriesModel;
import com.example.esamudaay.models.SortModel;
import com.example.esamudaay.models.VendersModel;
import com.example.esamudaay.models.VendorDetailModel;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class VendorDetailActivity extends AppCompatActivity {

    ActivityVendorDetailBinding binding;
    FirebaseDatabase database;
    ArrayList<VendorDetailModel> list, list2;
    ArrayList<SortModel> list1;




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
        binding = ActivityVendorDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list= new ArrayList<>();
        list2= new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://esamudaay-4ae43-default-rtdb.asia-southeast1.firebasedatabase.app/");

        ArrayList<Integer>  foodimages= new ArrayList<>();
        foodimages.add(R.drawable.a1);
        foodimages.add(R.drawable.a2);
        foodimages.add(R.drawable.a3);
        foodimages.add(R.drawable.a4);
        foodimages.add(R.drawable.a5);
        foodimages.add(R.drawable.a6);
        foodimages.add(R.drawable.a7);
        foodimages.add(R.drawable.a8);


        VendorDetailAdapter adapter = new VendorDetailAdapter(list, foodimages);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(VendorDetailActivity.this,LinearLayoutManager.VERTICAL,false);
        binding.productrecycler.setLayoutManager(mLayoutManager);
        binding.productrecycler.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(VendorDetailActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://api.test.esamudaay.com/api/v1/businesses/0635ecff-8fde-4185-8cd8-167efda42bbc/report", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RESPONSE FINAL", String.valueOf(response.length()));
                for (int i = 0; i < response.length(); i++) {

                    try {
                        ArrayList<String> error = new ArrayList<>();

                        JSONObject responseObj = response.getJSONObject(i);
                        String sku_id = responseObj.getString("sku_id");
                        String product_name = responseObj.getString("product_name");
                        String business_name = responseObj.getString("business_name");
                        String failure_reasons = responseObj.getString("failure_reasons");

                        if(failure_reasons.startsWith("null")){
                            error.add("null");
                        }
                        else{
                            JSONArray array = responseObj.getJSONArray("failure_reasons");

                            if(array.length()>0)
                            {
                                for(int j=0; j<array.length();j++){
                                    error.add(array.getString(j));
                                }
                            }

                        }
//                        System.out.println("failure list = "+ failure_reasons);

                        list.add(new VendorDetailModel(sku_id,product_name,business_name,error));
                        list2.add(new VendorDetailModel(sku_id,product_name,business_name,error));
                    adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.d("CATCH", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d( "Fail to get the data..",""+error.toString());
            }
        });
        queue.add(jsonArrayRequest);

        list1 = new ArrayList<>();
        list1.add(new SortModel( binding.all ,binding.cardAll ));
        list1.add(new SortModel(binding.compliant ,binding.cardComplaint));
        list1.add(new SortModel( binding.noncompliant ,binding.cardNoncompliant));
        list1.add(new SortModel( binding.stats ,binding.cardstats));
        binding.cardAll.setCardBackgroundColor(Color.parseColor("#FF8400"));
        binding.all.setTextColor(Color.argb(255, 255, 255, 255));
        binding.cardAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getText().setTextColor(ContextCompat.getColor(VendorDetailActivity.this,R.color.graphLine));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

                    binding.cardAll.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.all.setTextColor(Color.argb(255, 255, 255, 255));
                }
            }
        });

        binding.cardComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++) {
                    list1.get(i).getText().setTextColor(ContextCompat.getColor(VendorDetailActivity.this, R.color.graphLine));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

                    binding.cardComplaint.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.compliant.setTextColor(Color.argb(255, 255, 255, 255));

                }
            }
        });

        binding.cardNoncompliant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getText().setTextColor(ContextCompat.getColor(VendorDetailActivity.this,R.color.graphLine));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

                    binding.cardNoncompliant.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.noncompliant.setTextColor(Color.argb(255, 255, 255, 255));
                }
            }
        });

        binding.cardstats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<list1.size();i++){
                    list1.get(i).getText().setTextColor(ContextCompat.getColor(VendorDetailActivity.this,R.color.graphLine));
                    list1.get(i).getCard().setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));

                    binding.cardstats.setCardBackgroundColor(Color.parseColor("#FF8400"));
                    binding.stats.setTextColor(Color.argb(255, 255, 255, 255));
                }
            }
        });

        binding.imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}