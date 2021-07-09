package com.example.nofapmainactivity.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nofapmainactivity.Database.OpenHelper;
import com.example.nofapmainactivity.Modals.ToDoModel;
import com.example.nofapmainactivity.R;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    AlertDialog.Builder builder;
    Activity activity;
    Context context;
    public ArrayList<ToDoModel> dataSet;
    OpenHelper openHelper;

    public ToDoListAdapter(ArrayList<ToDoModel> arrayList) {
        this.dataSet = arrayList;
        this.activity = null;
        this.context = null;
        this.openHelper = null;
        this.builder = null;
    }

    public ToDoListAdapter(ArrayList<ToDoModel> arrayList, Context context2, Activity activity2) {
        this.dataSet = arrayList;
        this.activity = activity2;
        this.context = context2;
        this.openHelper = new OpenHelper(context2);
        this.builder = new AlertDialog.Builder(context2);
    }

    private ToDoModel getToDoModel(int position) {
        return this.dataSet.get(position);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof VHItem) {
            final ToDoModel toDoTask = getToDoModel(position);
            VHItem vhItem = (VHItem)holder;
            vhItem.txt_task_text.setText(toDoTask.getTask());
            vhItem.btn_task_save.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    DeleteDialog(position, toDoTask.getTask());
                    ToDoListAdapter.this.notifyDataSetChanged();
                    return true;
                }
            });
        }
    }

    public int getItemCount() {
        return this.dataSet.size();
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView txt_task_text;
        Button btn_task_save;
        public VHItem(View view) {
            super(view);
            this.txt_task_text = (TextView)view.findViewById(R.id.newTaskText);
            this.btn_task_save = (Button)view.findViewById(R.id.newTaskButton);
        }
    }

    public void DeleteDialog(final int i, String str2) {
        this.builder.setTitle("Delete");
        this.builder.setMessage("Do you want to delete " + str2 + " from the database ").setCancelable(false).setPositiveButton(Html.fromHtml("<font color='#000000'>Yes</font>"), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ToDoListAdapter.this.openHelper.deleteTask(i);
            }
        }).setNegativeButton((CharSequence) Html.fromHtml("<font color='#000000'>No</font>"), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = this.builder.create();
        alertDialog.setTitle("Delete");
        alertDialog.show();
    }
}
