package com.example.nofapmainactivity.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.nofapmainactivity.AppDefaultActivity;
import com.example.nofapmainactivity.Community.CommunityActivity;
import com.example.nofapmainactivity.R;
import com.example.nofapmainactivity.Settings.SettingsActivity;
import com.example.nofapmainactivity.Timer.TimerActivity;
import com.example.nofapmainactivity.ToDoList.ToDoListActivity;
import com.example.nofapmainactivity.Trophy.TrophyActivity;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppDefaultActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dl = findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id)
                {
                    case R.id.IconProfileId:
                        dl.closeDrawers();
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
        return R.layout.activity_profile;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return ProfileFragment.newInstance();
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

}