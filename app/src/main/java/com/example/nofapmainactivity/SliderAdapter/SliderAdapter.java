package com.example.nofapmainactivity.SliderAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.nofapmainactivity.R;


public class SliderAdapter extends PagerAdapter {

    Context Appcontext;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.Appcontext = context;
    }

    public int[] slide_image = {
            R.drawable.meditation_icon,
            R.drawable.success_steps_icon
    };


    public String[] slide_description = {
            "The goal of this app is to help you build habits that would lead you to success",
            "Meditation is a good exercice to resist to urge"
    };

    public String[] slide_heading = {
            "Meditation",
            "Habits"
    };

    @Override
    public int getCount() {
        return slide_image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)Appcontext.getSystemService(Appcontext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_description);

        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_heading[position]);
        slideDescription.setText(slide_description[position]);

        container.addView(view);
        return view;


    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
