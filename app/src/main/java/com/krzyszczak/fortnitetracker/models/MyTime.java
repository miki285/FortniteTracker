package com.krzyszczak.fortnitetracker.models;

import java.util.Calendar;

import lombok.Getter;

@Getter
public class MyTime {
    int hours;
    int minutes;

    public MyTime(Calendar calendar) {
        this.hours = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
    }

    public MyTime(int hourOfDay, int minute) {
        this.hours = hourOfDay;
        this.minutes = minute;
    }
}
