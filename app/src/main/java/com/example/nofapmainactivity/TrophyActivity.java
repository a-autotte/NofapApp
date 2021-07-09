package com.example.nofapmainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class TrophyActivity extends AppCompatActivity {
    private ActionBarDrawerToggle abdt;
    private DrawerLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);

        dl = findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle( this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                switch(id) {
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

                    case R.id.IconTrophyId:
                        dl.closeDrawers();
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void OpenActivity(Class<?> activity)
    {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);
    }
}