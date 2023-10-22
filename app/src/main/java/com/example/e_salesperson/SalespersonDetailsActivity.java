package com.example.e_salesperson;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

public class SalespersonDetailsActivity extends AppCompatActivity {
    Spinner spinner;
    Button button1;
    EditText editText;
    Button button5;
    TextView textView5;
    EditText editText3;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    private ArrayAdapter<String> adapter;

    ArrayList<User> userList = new ArrayList<User>() {
        {
            add(new User(1, "UserA"));
            add(new User(2, "UserB"));
            add(new User(3, "UserC"));
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesperson_details);

        button1 = findViewById(R.id.button3);
        button5 = findViewById(R.id.button5);
        spinner = findViewById(R.id.autoCompleteTextView2);
        editText = findViewById(R.id.editTextText4);
        textView5 = findViewById(R.id.textView5);

        editText3 = findViewById(R.id.editTextText3);
        editText5 = findViewById(R.id.editTextText5);
        editText6 = findViewById(R.id.editTextText6);
        editText7 = findViewById(R.id.editTextText7);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        // Add some initial values
        adapter.add("Ahmad");
        adapter.add("Saeed Ahmad");
        adapter.add("mohammad");

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();

//         Set click listeners for the buttons to navigate to other layouts.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.
                Intent intent = new Intent(SalespersonDetailsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = spinner.getSelectedItem().toString();
                if (selectedValue.equals("Saeed Ahmad")) {
                    // Change the text input field's text color to blue
                    editText.setBackgroundColor(Color.BLUE);
                    System.out.println("Enter If");

                } else {
                    // Reset the text input field's text color to the default color
                    editText.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case where nothing is selected (optional)
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Start an activity for the first button's functionality.
                    int region = getRegionFun();
                    int regionVal = (region == 1) ? 1 : 0;
                    double coastelRegion = Integer.parseInt(editText3.getText().toString());
                    double northRegion = Integer.parseInt(editText5.getText().toString());
                    double southRegion = Integer.parseInt(editText6.getText().toString());
                    double eastRegion = Integer.parseInt(editText7.getText().toString());
                    double LebanonRegion = Integer.parseInt(editText.getText().toString());

                    double rateRegion = (region == regionVal) ? 0.05 : 0.03;
                    double rateAbove1Million = (region == regionVal) ? 0.07 : 0.04;

                    double commission1 = (coastelRegion <= 1000000) ? coastelRegion * rateRegion : (1000000 * rateRegion + (coastelRegion - 1000000) * rateAbove1Million);

                    double commission2 = (northRegion <= 1000000) ? northRegion * rateRegion : (1000000 * rateRegion + (northRegion - 1000000) * rateAbove1Million);
                    double commission3 = (southRegion <= 1000000) ? southRegion * rateRegion : (1000000 * rateRegion + (southRegion - 1000000) * rateAbove1Million);
                    double commission4 = (eastRegion <= 1000000) ? eastRegion * rateRegion : (1000000 * rateRegion + (eastRegion - 1000000) * rateAbove1Million);
                    double commission5 = (LebanonRegion <= 1000000) ? LebanonRegion * rateRegion : (1000000 * rateRegion + (LebanonRegion - 1000000) * rateAbove1Million);

                    double totalCommission = commission1 + commission2 + commission3 + commission4 + commission5;
                    textView5.setText(String.valueOf(totalCommission));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, userList);
//        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
//            User selectedUser = (User) parent.getItemAtPosition(position);
//            int userId = selectedUser.getId();
//            // Use the userId as needed
//        });

    }

    private int getRegionFun() {
        Random random = new Random();
        // Generate a random number that's either 0 or 1
        return random.nextInt(2);
    }
}
