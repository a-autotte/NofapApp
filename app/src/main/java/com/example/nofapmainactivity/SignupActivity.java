package com.example.nofapmainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SignupActivity extends AppCompatActivity {

    EditText userEdit, PasswordEdit, ConfirmPasswordEdit, EmailEdit;
    Button SignUpButton, LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_signup);

        userEdit = findViewById(R.id.userEditId);
        PasswordEdit = findViewById(R.id.passwordEditId);
        ConfirmPasswordEdit = findViewById(R.id.passwordConfirmId);
        EmailEdit = findViewById(R.id.emailEditId);
        SignUpButton = findViewById(R.id.signupButtonId);
        LoginButton = findViewById(R.id.loginButtonId);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity();
            }
        });
    }

    public void OpenActivity()
    {
        Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
        startActivity(intent);
    }

    private void setLocale(String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale()
    {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);

    }
}