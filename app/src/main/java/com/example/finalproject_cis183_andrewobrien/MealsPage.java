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

public class MealsPage extends AppCompatActivity
{

    Button btn_v_meals_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meals_page);

        btn_v_meals_back = findViewById(R.id.btn_v_meals_back);

        btn_v_meals_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MealsPage.this, HomePage.class));
            }
        });
    }
}