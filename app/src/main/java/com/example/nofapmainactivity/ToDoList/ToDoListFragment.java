package com.example.nofapmainactivity.ToDoList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDoListFragment extends AppDefaultFragment {
    private FloatingActionButton mToDoFloatingActionButton;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToDoFloatingActionButton = (FloatingActionButton)view.findViewById(R.id.makeToDoActionButton);

        mToDoFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public static ToDoListFragment newInstance() {return new ToDoListFragment(); }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_to_do_list;
    }
}
