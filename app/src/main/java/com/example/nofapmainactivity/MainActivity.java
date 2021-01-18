package com.example.nofapmainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText userEdit, PasswordEdit, ConfirmPasswordEdit, EmailEdit;
    Button SignUpButton, LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEdit = findViewById(R.id.userEditId);
        PasswordEdit = findViewById(R.id.passwordEditId);
        ConfirmPasswordEdit = findViewById(R.id.passwordConfirmId);
        EmailEdit = findViewById(R.id.emailEditId);
        SignUpButton = findViewById(R.id.signupButtonId);
        LoginButton = findViewById(R.id.loginButtonId);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void OpenActivity()
    {

    }
}