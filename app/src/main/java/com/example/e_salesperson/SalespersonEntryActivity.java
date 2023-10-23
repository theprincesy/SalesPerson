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
import android.widget.Toast;

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
    EditText name;
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
        name = findViewById(R.id.editTextText2);
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
                System.out.println(name.getText().toString());
                System.out.println(spinner.getSelectedItem().toString());
                System.out.println(image.toString());


                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("name", name.getText().toString());
                    jsonBody.put("region", spinner.getSelectedItem().toString());
                    jsonBody.put("img", image.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                OkHttpClient client = new OkHttpClient();
                String url = "https://abdalhamid.pythonanywhere.com/api/sales_person/"; // Replace with your API endpoint URL

                // Create a JSON request body (you can use other types of bodies, e.g., FormBody)
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                RequestBody requestBody = RequestBody.create(jsonBody.toString(),JSON);
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                new Thread(() -> {
                    try {
                        Response response = client.newCall(request).execute();
                        System.out.println(response.code());
                        if (response.isSuccessful()) {
                            String responseData = response.body().string();
//                            Intent intent = new Intent(SalespersonEntryActivity.this, HomeActivity.class);
//                            startActivity(intent);
                        } else {
                            // Handle the API call failure (e.g., network error or non-2xx HTTP response)
                            System.err.println("long");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SalespersonEntryActivity.this, "The SalesPerson Saved", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
//                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        // Handle the exception
                    }
                }).start();

                Intent intent = new Intent(SalespersonEntryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }

}
