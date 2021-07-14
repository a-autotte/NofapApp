package com.example.nofapmainactivity.Timer;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class TimerFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_timer;
    }

    public static TimerFragment newInstance() {return new TimerFragment();}
}
