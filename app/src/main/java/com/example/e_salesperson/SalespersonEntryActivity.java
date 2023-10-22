package com.example.e_salesperson;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

public class SalespersonEntryActivity extends AppCompatActivity{
    Spinner spinner;
    private ArrayAdapter<String> adapter;
    Button button1;
    Button button6;
    ImageView image;
    int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesperson_entry);
        spinner = findViewById(R.id.autoCompleteTextView);
        button1 = findViewById(R.id.button);
        button6 = findViewById(R.id.button6);
        image = findViewById(R.id.imageView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        // Add some initial values
        adapter.add("Coastal Region");
        adapter.add("North Region");
        adapter.add("South Region");
        adapter.add("East Region");
        adapter.add("Lebanon Region");

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                launchSomeActivity.launch(i);
            }
        });


        // Set click listeners for the buttons to navigate to other layouts.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.
                Intent intent = new Intent(SalespersonEntryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }
    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        image.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });
}
