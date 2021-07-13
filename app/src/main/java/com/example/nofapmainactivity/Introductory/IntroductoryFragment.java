package com.example.nofapmainactivity.Introductory;

import androidx.fragment.app.Fragment;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class IntroductoryFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.introductory_activity;
    }

    public static IntroductoryFragment newInstance() {return new IntroductoryFragment();}
}
