package com.example.nofapmainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nofapmainactivity.Adapter.ToDoAdapter;
import com.example.nofapmainactivity.Utils.DatabaseHandler;
import com.example.nofapmainactivity.modals.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity implements DialogCloseListener {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private FloatingActionButton buttonToDoList;
    private TextView tvToDoList;
    private EditText etToDoList;
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        buttonToDoList = findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        buttonToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });



        ToDoModel task = new ToDoModel();
        task.setTask("Hello");
        task.setStatus(0);
        task.setId(1);

        db.insertTask(task);
        db.insertTask(task);
        db.insertTask(task);
        db.insertTask(task);
        db.insertTask(task);
        tasksAdapter.setTasks(db.getAllTasks());


        /*buttonToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoModel task = new ToDoModel();
                task.setTask();
                task.setStatus(0);
                task.setId(db.getAllTasks().size() + 1);
                taskList.add(task);
                tasksAdapter.setTasks(taskList);
                etToDoList.setText("");
            }
        });*/





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

                    case R.id.IconTrophyId:
                        OpenActivity(TrophyActivity.class);
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

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();

    }
}