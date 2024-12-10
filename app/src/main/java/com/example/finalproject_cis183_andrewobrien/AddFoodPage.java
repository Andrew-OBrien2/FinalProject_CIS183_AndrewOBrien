package com.example.finalproject_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddFoodPage extends AppCompatActivity {

    Button btn_j_addFood_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_food_page);

        btn_j_addFood_back = findViewById(R.id.btn_v_addFood_back);

        btn_j_addFood_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddFoodPage.this, HomePage.class));
            }
        });
    }
}