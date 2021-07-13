package com.example.nofapmainactivity.Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.nofapmainactivity.AppDefaultActivity;
import com.example.nofapmainactivity.Profile.ProfileActivity;
import com.example.nofapmainactivity.R;
import com.example.nofapmainactivity.databinding.ActivitySignupBinding;

import com.example.nofapmainactivity.Modals.UserModal;

import java.util.Locale;

public class SignupActivity extends AppDefaultActivity {

    ActivitySignupBinding binding;

    EditText userEdit, PasswordEdit, ConfirmPasswordEdit, EmailEdit;
    Button SignUpButton, LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.loginButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity();
            }
        });

        binding.signupButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModal userModal = new UserModal();
                userModal.userName = binding.userEditId.getText().toString();
                userModal.userEmail = binding.emailEditId.getText().toString();
                userModal.userPassword = binding.passwordEditId.getText().toString();
                userModal.userConfirmPassword = binding.passwordConfirmId.getText().toString();
            }
        });
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_signup;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return SignupFragment.newInstance();
    }

    public void OpenActivity()
    {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
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