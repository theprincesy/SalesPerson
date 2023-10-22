package com.example.e_salesperson;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        SalespersonInfo saidAhmadInfo = new SalespersonInfo(
                "Said Ahmad",
                9, // Month
                2023, // Year
                new Date(2023 - 1900, 0, 10), // Registration Date (year - 1900, month, day)
                85000, // Southern Region Commission
                9000, // Coastal Region Commission
                0, // Northern Region Commission
                0, // Eastern Region Commission
                13500, // Lebanon Commission
                107500 // Monthly Commission
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();
        data.add(saidAhmadInfo);




//        recyclerView.setAdapter(adapter);


        Button button1 = findViewById(R.id.searchButton);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.

//                notifyDataSetChanged(); // Refresh the R
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

