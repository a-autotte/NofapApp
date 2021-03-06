package com.example.nofapmainactivity.MainPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.nofapmainactivity.AppDefaultActivity;
import com.example.nofapmainactivity.Community.CommunityActivity;
import com.example.nofapmainactivity.Utility.Modals.ToDoModel;
import com.example.nofapmainactivity.Profile.ProfileActivity;
import com.example.nofapmainactivity.R;
import com.example.nofapmainactivity.Settings.SettingsActivity;
import com.example.nofapmainactivity.Timer.TimerActivity;
import com.example.nofapmainactivity.ToDoList.ToDoListActivity;
import com.example.nofapmainactivity.Trophy.TrophyActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Locale;

public class MainPageActivity extends AppDefaultActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private RecyclerView tasksRecyclerView;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadLocale();
        //setContentView(R.layout.activity_main_page);
        /*getSupportActionBar().hide();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksAdapter = new ToDoAdapter(this);
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

        tasksAdapter.setTasks(taskList);*/





        dl = findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle( this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                switch (id)
                {
                    case R.id.IconProfileId:
                        OpenActivity(ProfileActivity.class);
                        break;

                    case R.id.IconSettingId:
                        OpenActivity(SettingsActivity.class);
                        break;

                    case R.id.IconTimerId:
                        OpenActivity(TimerActivity.class);
                        break;

                    case R.id.IconCommunityId:
                        OpenActivity(CommunityActivity.class);
                        break;

                    case R.id.IconToDoListId:
                        OpenActivity(ToDoListActivity.class);
                        break;


                    case R.id.IconTrophyId:
                        OpenActivity(TrophyActivity.class);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_main_page;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return MainPageFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void OpenActivity(Class<?> ActivityToOpen)
    {
        Intent intent = new Intent(getApplicationContext(), ActivityToOpen);
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
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);

    }
}