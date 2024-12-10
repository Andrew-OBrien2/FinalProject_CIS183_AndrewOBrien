package com.example.finalproject_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity
{
    Button btn_j_home_budget;
    Button btn_j_home_meals;
    Button btn_j_home_search;
    Button btn_j_home_addFood;
    TextView tv_j_home_logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        // Connecting GUI to Java
        btn_j_home_budget   = findViewById(R.id.btn_v_home_budget);
        btn_j_home_meals    = findViewById(R.id.btn_v_home_meals);
        btn_j_home_search   = findViewById(R.id.btn_v_home_search);
        btn_j_home_addFood  = findViewById(R.id.btn_v_home_addFood);
        tv_j_home_logOut    = findViewById(R.id.tv_v_home_logOut);

        setButtonListeners();
    }

    private void setButtonListeners()
    {
        btn_j_home_budget.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomePage.this, BudgetPage.class));
            }
        });

        btn_j_home_meals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomePage.this, MealsPage.class));
            }
        });

        btn_j_home_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomePage.this, SearchPage.class));
            }
        });

        btn_j_home_addFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomePage.this, AddFoodPage.class));
            }
        });

        tv_j_home_logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomePage.this, MainActivity.class));
            }
        });
    }
}