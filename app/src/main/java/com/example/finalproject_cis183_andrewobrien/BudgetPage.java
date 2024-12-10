package com.example.finalproject_cis183_andrewobrien;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BudgetPage extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText et_j_budget_budget;
    EditText et_j_budget_protein;
    EditText et_j_budget_carbs;
    EditText et_j_budget_fats;
    ListView lv_j_budget_results;
    Button btn_v_budget_back;
    Button btn_v_budget_search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        dbHelper = new DatabaseHelper(this);

        et_j_budget_budget = findViewById(R.id.et_v_budget_budget);
        et_j_budget_protein = findViewById(R.id.et_v_budget_protein);
        et_j_budget_carbs = findViewById(R.id.et_v_budget_carbs);
        et_j_budget_fats = findViewById(R.id.et_v_budget_fats);
        lv_j_budget_results = findViewById(R.id.lv_v_budget_results);
        btn_v_budget_back = findViewById(R.id.btn_v_budget_back2);
        btn_v_budget_search = findViewById(R.id.btn_v_budget_search);

        setOnClickListeners();
    }

    private void setOnClickListeners()
    {
        btn_v_budget_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                searchFoods();
            }
        });

        btn_v_budget_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(BudgetPage.this, HomePage.class));
            }
        });
    }

    private void searchFoods()
    {
        String budgetInput = et_j_budget_budget.getText().toString();
        String proteinInput = et_j_budget_protein.getText().toString();
        String carbsInput = et_j_budget_carbs.getText().toString();
        String fatsInput = et_j_budget_fats.getText().toString();

        ArrayList<String> foodList = findFoodsGivenCriteria(budgetInput, proteinInput, carbsInput, fatsInput);

        //log food list for debugging
        Log.d("FoodList", "Food List: " + foodList.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodList);
        lv_j_budget_results.setAdapter(adapter);
    }

    public ArrayList<String> findFoodsGivenCriteria(String budgetInput, String proteinInput, String carbsInput, String fatsInput) {
        ArrayList<String> foodList = new ArrayList<>();
        String foodQuery = "SELECT * FROM Food";
        String macrosQuery = "SELECT * FROM Macros";

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor foodCursor = db.rawQuery(foodQuery, null);
        ArrayList<Integer> matchingMacrosIDs = new ArrayList<>();

        if (!budgetInput.isEmpty())
        {
            double budget = Double.parseDouble(budgetInput);
            if (foodCursor.moveToFirst())
            {
                while (!foodCursor.isAfterLast())
                {
                    int macrosID = foodCursor.getInt(foodCursor.getColumnIndexOrThrow("macrosID"));
                    double cost = foodCursor.getDouble(foodCursor.getColumnIndexOrThrow("cost"));
                    if (cost <= budget)
                    {
                        matchingMacrosIDs.add(macrosID);
                    }
                    foodCursor.moveToNext();
                }
            }
        }
        else
        {
            if (foodCursor.moveToFirst())
            {
                while (!foodCursor.isAfterLast())
                {
                    int macrosID = foodCursor.getInt(foodCursor.getColumnIndexOrThrow("macrosID"));
                    matchingMacrosIDs.add(macrosID);
                    foodCursor.moveToNext();
                }
            }
        }

        Cursor macrosCursor = db.rawQuery(macrosQuery, null);
        ArrayList<Integer> finalMatchingIDs = new ArrayList<>();

        if (macrosCursor.moveToFirst())
        {
            while (!macrosCursor.isAfterLast())
            {
                int macrosID = macrosCursor.getInt(macrosCursor.getColumnIndexOrThrow("macrosID"));
                float protein = macrosCursor.getFloat(macrosCursor.getColumnIndexOrThrow("protein"));
                float carbs = macrosCursor.getFloat(macrosCursor.getColumnIndexOrThrow("carbs"));
                float fat = macrosCursor.getFloat(macrosCursor.getColumnIndexOrThrow("fat"));

                boolean matchesProtein = proteinInput.isEmpty() || protein >= Float.parseFloat(proteinInput);
                boolean matchesCarbs = carbsInput.isEmpty() || carbs >= Float.parseFloat(carbsInput);
                boolean matchesFats = fatsInput.isEmpty() || fat >= Float.parseFloat(fatsInput);

                if (matchesProtein && matchesCarbs && matchesFats && matchingMacrosIDs.contains(macrosID)) {
                    finalMatchingIDs.add(macrosID);
                }
                macrosCursor.moveToNext();
            }
        }
        macrosCursor.close();

        foodCursor = db.rawQuery(foodQuery, null);
        if (foodCursor.moveToFirst())
        {
            while (!foodCursor.isAfterLast())
            {
                int macrosID = foodCursor.getInt(foodCursor.getColumnIndexOrThrow("macrosID"));
                if (finalMatchingIDs.contains(macrosID))
                {
                    String foodName = foodCursor.getString(foodCursor.getColumnIndexOrThrow("foodName"));
                    foodList.add(foodName);
                }
                foodCursor.moveToNext();
            }
        }

        return foodList;
    }
}