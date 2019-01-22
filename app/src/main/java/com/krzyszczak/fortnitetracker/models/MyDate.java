package com.krzyszczak.fortnitetracker.models;

import java.util.Calendar;

import lombok.Getter;

@Getter
public class MyDate {

    int year;
    int month;
    int dayOfMonth;

    public MyDate(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public MyDate(int year, int month, int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }
}
