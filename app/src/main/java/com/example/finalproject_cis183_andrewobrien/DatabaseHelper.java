package com.example.finalproject_cis183_andrewobrien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "AppData.db";
    private static final String food_table_name = "Food";
    private static final String macros_table_name = "Macros";
    private static final String meals_table_name = "Meals";
    private static final String users_table_name = "Users";
    private static final String budget_table_name = "Budget";

    public DatabaseHelper(Context c)
    {
        super(c,database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //food table
        db.execSQL("CREATE TABLE " + food_table_name + " (" + "foodName varchar(50) primary key not null, " + "cost float, " + "macrosID varchar(50));");

        //macros table
        db.execSQL("CREATE TABLE " + macros_table_name + " (" + "macrosID integer primary key autoincrement not null, " + "protein float, " + "carbs float, " + "fat float);");

        //meals table
        db.execSQL("CREATE TABLE " + meals_table_name + " (" + "mealID integer primary key autoincrement not null, " + "mealName varchar(50), " + "user varchar(50), " + "foodItem varchar(50));");

        //users table
        db.execSQL("CREATE TABLE " + users_table_name + " (" + "username varchar(50) primary key not null, " + "password varchar(50));");

        //budget table
        db.execSQL("CREATE TABLE " + budget_table_name + " (" + "budgetID integer primary key autoincrement not null, " + "user varchar(50), " + "funds float);");

        //DUMMY DATA
        //Macros
        db.execSQL("INSERT INTO Macros (protein, carbs, fat) VALUES (25, 30, 10);");
        db.execSQL("INSERT INTO Macros (protein, carbs, fat) VALUES (15, 50, 5);");
        db.execSQL("INSERT INTO Macros (protein, carbs, fat) VALUES (10, 20, 15);");

        //Food
        db.execSQL("INSERT INTO Food (foodName, cost, macrosID) VALUES ('Chicken Breast', 5.99, 1);");
        db.execSQL("INSERT INTO Food (foodName, cost, macrosID) VALUES ('Brown Rice', 2.49, 2);");
        db.execSQL("INSERT INTO Food (foodName, cost, macrosID) VALUES ('Avocado', 1.99, 3);");

        //Users
        db.execSQL("INSERT INTO Users (username, password) VALUES ('user1', 'password123');");
        db.execSQL("INSERT INTO Users (username, password) VALUES ('user2', 'mypassword');");

        //Meals
        db.execSQL("INSERT INTO Meals (mealName, user, foodItem) VALUES ('Lunch', 'user1', 'Chicken Breast');");
        db.execSQL("INSERT INTO Meals (mealName, user, foodItem) VALUES ('Dinner', 'user1', 'Brown Rice');");
        db.execSQL("INSERT INTO Meals (mealName, user, foodItem) VALUES ('Snack', 'user2', 'Avocado');");

        //Budget
        db.execSQL("INSERT INTO Budget (user, funds) VALUES ('user1', 100.00);");
        db.execSQL("INSERT INTO Budget (user, funds) VALUES ('user2', 50.00);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public void logMacrosData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Macros", null);

        if (cursor.moveToFirst()) {
            do {
                int macrosID = cursor.getInt(cursor.getColumnIndexOrThrow("macrosID"));
                float protein = cursor.getFloat(cursor.getColumnIndexOrThrow("protein"));
                float carbs = cursor.getFloat(cursor.getColumnIndexOrThrow("carbs"));
                float fat = cursor.getFloat(cursor.getColumnIndexOrThrow("fat"));

                Log.d("DatabaseHelper", "Macros Data: macrosID=" + macrosID + ", Protein=" + protein + ", Carbs=" + carbs + ", Fat=" + fat);
            } while (cursor.moveToNext());
        } else {
            Log.d("DatabaseHelper", "No data found in Macros table.");
        }
    }

    public boolean checkUserCredentials(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + password + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            //check if there is result
            cursor.close();
            return true;
        } else
        {
            cursor.close();
            return false;
        }
    }
}
