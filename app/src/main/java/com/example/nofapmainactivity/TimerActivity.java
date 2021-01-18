package com.example.nofapmainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nofapmainactivity.models.TimerModel;

public class TimerActivity extends AppCompatActivity {

    TextView mSeconds, mMinutes, mHours, mDays, mMonths, mYears;
    TimerModel timer;
    String timerSecond, timerMinutes, timerHours, timerDays, timerMonths, timerYears;
    Button mResetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mSeconds = findViewById(R.id.TimerSecondsId);
        mMinutes = findViewById(R.id.TimerMinutesId);
        mHours = findViewById(R.id.TimerHoursId);
        mDays = findViewById(R.id.TimerDaysId);
        mMonths = findViewById(R.id.TimerMonthsId);
        mYears = findViewById(R.id.TimerYearsId);

        //initTimer();
        int index = 0;
        while (index < 100)
        {
            timer.AddTime();
            index++;
        }

        mResetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ResetTimer();
            }
        });
    }

    private void ResetTimer()
    {
        timer = new TimerModel();
        timer.Reset();
        timerSecond = Integer.toString(timer.Seconds);
        timerMinutes = Integer.toString(timer.Minutes);
        timerHours = Integer.toString(timer.Hours);
        timerDays = Integer.toString(timer.Days);
        timerMonths = Integer.toString(timer.Months);
        timerYears = Integer.toString(timer.Years);
        mSeconds.setText("Seconds : \t\t" + timerSecond);
        mMinutes.setText("Minutes : \t\t" + timerMinutes);
        mHours.setText("Hours : \t\t" + timerHours);
        mDays.setText("Days : \t\t" + timerDays);
        mMonths.setText("Months : \t\t" + timerMonths);
        mYears.setText("Years : \t\t" + timerYears);
    }

    /*private void initTimer()
    {
        timer = new TimerModel();
        timer.Reset();
        timerSecond = Integer.toString(timer.Seconds);
        timerMinutes = Integer.toString(timer.Minutes);
        timerHours = Integer.toString(timer.Hours);
        timerDays = Integer.toString(timer.Days);
        timerMonths = Integer.toString(timer.Months);
        timerYears = Integer.toString(timer.Years);
        mSeconds.setText("Seconds : \t\t" + timerSecond);
        mMinutes.setText("Minutes : \t\t" + timerMinutes);
        mHours.setText("Hours : \t\t" + timerHours);
        mDays.setText("Days : \t\t" + timerDays);
        mMonths.setText("Months : \t\t" + timerMonths);
        mYears.setText("Years : \t\t" + timerYears);

    }*/
}