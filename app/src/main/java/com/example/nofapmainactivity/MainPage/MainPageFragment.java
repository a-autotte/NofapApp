package com.example.nofapmainactivity.MainPage;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class MainPageFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_main_page;
    }

    public static MainPageFragment newInstance() {return new MainPageFragment();}
}
