package com.example.nofapmainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.google.android.material.navigation.NavigationView;

public class SplashActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent i = new Intent(SplashActivity.this, MainPageActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);










        dl = findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle( this, dl, R.string.Open, R.string.Close);
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
                    case R.id.IconProfileId:
                        OpenActivity(ProfileActivity.class);
                        break;

                    case R.id.IconSettingId:
                        OpenActivity(SettingsActivity.class);
                        break;

                    case R.id.IconTimerId:
                        OpenActivity(TimerActivity.class);
                        break;
                    case R.id.IconToDoListId:
                        OpenActivity(ToDoListActivity.class);
                        break;
                    case R.id.IconCommunityId:
                        OpenActivity(CommunityActivity.class);
                        break;

                }


                return true;
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