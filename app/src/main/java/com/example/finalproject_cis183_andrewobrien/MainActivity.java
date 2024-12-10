package com.example.finalproject_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    Button btn_j_main_login;
    Button btn_j_main_createUser;
    EditText et_j_main_username;
    EditText et_j_main_password;
    TextView tv_j_main_error;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //connecting GUI to java
        btn_j_main_login      = findViewById(R.id.btn_v_main_login);
        btn_j_main_createUser = findViewById(R.id.btn_v_main_createAccount);
        et_j_main_username    = findViewById(R.id.et_v_main_name);
        et_j_main_password    = findViewById(R.id.et_v_main_password);
        tv_j_main_error       = findViewById(R.id.tv_v_main_error);

        dbHelper = new DatabaseHelper(this);
        dbHelper.logMacrosData();

        setLoginButtonOnClickListener();
    }

    private void setLoginButtonOnClickListener()
    {
        btn_j_main_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String username = et_j_main_username.getText().toString();
                String password = et_j_main_password.getText().toString();

                if (dbHelper.checkUserCredentials(username, password))
                {
                    //go to the home page if login is successful
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                } else
                {
                    //show an error message
                    tv_j_main_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setCreateUserButtonOnClickListener()
    {
        btn_j_main_createUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, CreateNewUser.class));
            }
        });
    }
}