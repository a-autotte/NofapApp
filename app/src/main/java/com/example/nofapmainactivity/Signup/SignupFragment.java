package com.example.nofapmainactivity.Signup;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.R;

public class SignupFragment extends AppDefaultFragment {
    @Override
    protected int layoutRes() {
        return R.layout.activity_signup;
    }

    public static SignupFragment newInstance() {return new SignupFragment();}
}
