package com.example.finalproject_cis183_andrewobrien;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "AppData.db";
    private static final String students_table_name = "Students";
    private static final String majors_table_name = "Majors";

    public DatabaseHelper(Context c)
    {
        super(c,database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
