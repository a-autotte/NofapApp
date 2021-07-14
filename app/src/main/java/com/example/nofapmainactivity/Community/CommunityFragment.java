package com.example.nofapmainactivity.Community;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class CommunityFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_community;
    }

    public static CommunityFragment newInstance() {return new CommunityFragment();}
}
