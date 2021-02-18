package com.example.nofapmainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ToDoListActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private Button buttonToDoList;
    private TextView tvToDoList;
    private EditText etToDoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        LinearLayout ll = new LinearLayout(getApplicationContext());
        dl = findViewById(R.id.dl);

        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id)
                {
                    case R.id.IconCommunityId:
                        OpenActivity(CommunityActivity.class);
                        break;

                    case R.id.IconSettingId:
                        OpenActivity(SettingsActivity.class);
                        break;

                    case R.id.IconProfileId:
                        OpenActivity(ProfileActivity.class);
                        break;

                    case R.id.IconTimerId:
                        OpenActivity(TimerActivity.class);
                        break;

                    case R.id.IconToDoListId:
                        dl.closeDrawers();
                        break;
                }

                return true;
            }
        });

        buttonToDoList = findViewById(R.id.ButtonToDoListId);
        etToDoList = findViewById(R.id.editTextToDoListId);
        tvToDoList = findViewById(R.id.toDoListTextId);

        buttonToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = new CheckBox(getApplicationContext());
                checkBox.setText("sdfsd");
                tvToDoList.setText(etToDoList.getText().toString());
                etToDoList.setText("");
                ll.addView(checkBox);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void OpenActivity(Class<?> Activity)
    {
        Intent intent = new Intent(getApplicationContext(), Activity);
        startActivity(intent);
    }
}