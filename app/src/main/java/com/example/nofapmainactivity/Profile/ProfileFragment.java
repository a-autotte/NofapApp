package com.example.nofapmainactivity.Profile;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class ProfileFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_profile;
    }

    public static ProfileFragment newInstance() {return new ProfileFragment();}
}
