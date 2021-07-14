package com.example.nofapmainactivity.Trophy;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class TrophyFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_trophy;
    }

    public static TrophyFragment newInstance() {return new TrophyFragment();}
}
