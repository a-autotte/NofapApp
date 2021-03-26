package com.example.nofapmainactivity.SliderAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.nofapmainactivity.ProfileActivity;
import com.example.nofapmainactivity.R;

public class OnBoardingFragment1 extends Fragment {



    public static class OnBoardingFragmentActivity1 extends AppCompatActivity {
        Button skipButton;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            skipButton = findViewById(R.id.skipButton);

            skipButton.setOnClickListener(View.OnClickListener() {
                
            });
        }

        private void OpenActivity(Class<?> Activity) {
            Intent intent = new Intent(getApplicationContext(), Activity);
            startActivity(intent);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.on_boarding_fragment1, container, false);
        return root;
    }

}





