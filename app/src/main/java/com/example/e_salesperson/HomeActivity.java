package com.example.e_salesperson;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button4 = findViewById(R.id.button4);

        // Set click listeners for the buttons to navigate to other layouts.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the first button's functionality.
                Intent intent = new Intent(HomeActivity.this, SearchCommissionActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the second button's functionality.
                Intent intent = new Intent(HomeActivity.this, SalespersonEntryActivity.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an activity for the second button's functionality.
                Intent intent = new Intent(HomeActivity.this, SalespersonDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
