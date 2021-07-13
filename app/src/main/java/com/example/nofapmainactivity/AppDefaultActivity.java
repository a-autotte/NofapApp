package com.example.nofapmainactivity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public abstract class AppDefaultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentViewLayoutRes());
        setUpInitialFragment(savedInstanceState);
    }


    private void setUpInitialFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, createInitialFragment())
                    .commit();
        }
    }

    @LayoutRes
    protected abstract int contentViewLayoutRes();

    @NonNull
    protected abstract Fragment createInitialFragment();
}
