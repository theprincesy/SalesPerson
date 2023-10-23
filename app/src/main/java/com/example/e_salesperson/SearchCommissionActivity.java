package com.example.e_salesperson;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

public class SearchCommissionActivity extends AppCompatActivity{
    Button button8;
    RecyclerView recyclerView;
    private ArrayAdapter<SalespersonInfo> adapter;
    private List<SalespersonInfo> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_commissions);
        recyclerView = findViewById(R.id.recyclerView);







//        recyclerView.setAdapter(adapter);


        Button button1 = findViewById(R.id.searchButton);
        button8 = findViewById(R.id.button8);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        button8.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.

//                notifyDataSetChanged(); // Refresh the R
                List<String> data = new ArrayList<String>();
                data.add("Salesperson Name:");data.add("Saeed Ahmad");
                data.add("Month");data.add("9");
                data.add("Year:");data.add("2023");
                data.add("Southern region: ");data.add("0");
                data.add("Northern Region:");data.add("1500000");
                data.add("Coastal region: ");data.add("0");

                data.add("Eastern Region:");data.add("0");
                data.add("Lebanon: ");data.add("0");
                data.add("Monthly commission:");data.add("85000");
                recyclerView.setLayoutManager(new GridLayoutManager(SearchCommissionActivity.this, 2));
//                Adapter adapter = (Adapter) recyclerView.getAdapter();

                Adapter adapter = new Adapter(data);
                recyclerView.setAdapter(adapter);
            }
        });

        // Set click listeners for the buttons to navigate to other layouts.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.
                Intent intent = new Intent(SearchCommissionActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}

