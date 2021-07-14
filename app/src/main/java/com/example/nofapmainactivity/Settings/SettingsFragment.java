package com.example.nofapmainactivity.Settings;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class SettingsFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_settings;
    }

    public static SettingsFragment newInstance() {return new SettingsFragment();}
}
