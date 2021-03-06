package com.example.nofapmainactivity.Community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.nofapmainactivity.Adapter.NofapServer;
import com.example.nofapmainactivity.AppDefaultActivity;
import com.example.nofapmainactivity.Profile.ProfileActivity;
import com.example.nofapmainactivity.R;
import com.example.nofapmainactivity.RecyclerViewAdapter;
import com.example.nofapmainactivity.Settings.SettingsActivity;
import com.example.nofapmainactivity.Timer.TimerActivity;
import com.example.nofapmainactivity.ToDoList.ToDoListActivity;
import com.example.nofapmainactivity.Trophy.TrophyActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommunityActivity extends AppDefaultActivity {
    //ActivityCommunityBinding binding;
    private DrawerLayout dl;
    ActionBarDrawerToggle abdt;

    RecyclerView recyclerView;
    List<NofapServer> list=new ArrayList<>();
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_community);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String username[] = getResources().getStringArray(R.array.nofapservername);
        int profileImage[] = {R.drawable.nofap1, R.drawable.nofap2, R.drawable.nofap4, R.drawable.nofap5, R.drawable.nofap6, R.drawable.nofap7, R.drawable.nofap8, R.drawable.nofap9, R.drawable.nofap10};
        String descForAll = getResources().getString(R.string.nofapserverdescription);

        for (int i = 0; i < username.length; i++)
        {
            NofapServer users = new NofapServer(profileImage[i], username[i], descForAll);
            list.add(users);
        }

        adapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(adapter);

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
                        dl.closeDrawers();
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
        return R.layout.activity_community;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return CommunityFragment.newInstance();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText.toString());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void OpenActivity(Class<?> activity)
    {
        Intent intent = new Intent(getApplicationContext(), activity);
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