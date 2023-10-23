package com.example.e_salesperson;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.editTextText);
        passwordEditText = findViewById(R.id.editTextTextPassword3);
        button1 = findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                System.out.println(username);
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("username", username);
                    jsonBody.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                OkHttpClient client = new OkHttpClient();
                String url = "https://abdalhamid.pythonanywhere.com/api/login/"; // Replace with your API endpoint URL

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
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Handle the API call failure (e.g., network error or non-2xx HTTP response)
                            System.err.println("long");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
//                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        // Handle the exception
                    }
                }).start();



            }
        });
    }



}

