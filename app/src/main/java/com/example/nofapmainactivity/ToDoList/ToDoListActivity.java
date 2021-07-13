package com.example.nofapmainactivity.ToDoList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nofapmainactivity.Adapter.ToDoListAdapter;
import com.example.nofapmainactivity.AppDefaultActivity;
import com.example.nofapmainactivity.Database.OpenHelper;
import com.example.nofapmainactivity.Modals.ToDoModel;
import com.example.nofapmainactivity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ToDoListActivity extends AppDefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadLocale();
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

    /*private void setLocale(String lang) {
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
    }*/
}