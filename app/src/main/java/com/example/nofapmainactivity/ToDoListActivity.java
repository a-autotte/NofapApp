package com.example.nofapmainactivity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.example.nofapmainactivity.Database.OpenHelper;
import com.example.nofapmainactivity.Modals.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ToDoListActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private RecyclerView tasksRecyclerView;
    ToDoListAdapter tasksAdapter;
    private ArrayList<ToDoModel> taskList;
    OpenHelper oh;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_to_do_list);
        getSupportActionBar().hide();
        /*taskList = new ArrayList<>();
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksAdapter = new ToDoListAdapter(taskList);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ToDoModel task = new ToDoModel();
        task.setTask("This is a Test Task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        tasksAdapter.dataSet.addAll(taskList);*/

        FloatingActionButton btn_addTask = (FloatingActionButton)findViewById(R.id.fab);

        btn_addTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                builder.setTitle("Add");
                builder.setMessage("Do you want to add a task").setCancelable(false).setPositiveButton(Html.fromHtml("<font color='#000000'>Yes</font>"), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = (EditText)findViewById(R.id.newTaskText);
                        editText.getText();
                    }
                }).setNegativeButton((CharSequence) Html.fromHtml("<font color='#000000'>No</font>"), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
            }
        });

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