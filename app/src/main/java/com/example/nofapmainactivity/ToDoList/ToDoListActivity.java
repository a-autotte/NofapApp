package com.example.nofapmainactivity.ToDoList;

import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.nofapmainactivity.AppDefaultActivity;
import com.example.nofapmainactivity.Utility.Modals.ToDoModel;
import com.example.nofapmainactivity.R;

import java.util.Locale;

public class ToDoListActivity extends AppDefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_to_do_list;
    }

    @Override
    protected Fragment createInitialFragment() {
        return ToDoListFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("My_Lang", MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String lang = prefs.getString("My_Lang", "");
        setLocale(lang);
    }
}