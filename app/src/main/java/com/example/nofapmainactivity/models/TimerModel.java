package com.example.nofapmainactivity.models;

import java.util.Timer;

public class TimerModel {
    public boolean TimerStarted;

    public int Hours;
    public int Minutes;
    public int Seconds;
    public int Days;
    public int Years;
    public int Months;

    public TimerModel()
    {
        this.TimerStarted = true;
        AddTime();
    }

    public void AddTime()
    {
            Seconds++;

            if (Seconds > 60)
            {
                Seconds = 0;
                Minutes++;

                if (Minutes > 60)
                {
                    if (Hours > 24)
                    {
                        Days++;

                        if (Days > 30)
                        {
                            if (Months > 12)
                            {
                                Years++;
                                Months = 0;
                            }
                            Months++;
                            Days = 0;
                        }
                    }

                    Minutes = 0;
                    Hours++;
                }


            }


    }


    public void Reset()
    {
        this.TimerStarted = false;
        this.Seconds = 0;
        this.Minutes = 0;
        this.Hours = 0;
        this.Days = 0;
        this.Years = 0;
    }
}
