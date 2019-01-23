package com.krzyszczak.fortnitetracker.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.models.MyDate;
import com.krzyszczak.fortnitetracker.models.MyTime;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.provider.CalendarContract.Events.*;

public class InviteFragment extends Fragment {

    public static String TAG = "InviteFragmentTag";

    private TextView startDateTextView;
    private TextView startTimeTextView;
    private TextView endDateTextView;
    private TextView endTimeTextView;
    private MyDate startDate;
    private MyDate endDate;
    private MyTime startTime;
    private MyTime endTime;
    private Button createEventButton;
    private EditText inviteEditText;

    public static InviteFragment newInstance() {
        return new InviteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startDateTextView = view.findViewById(R.id.picked_start_date_textview);
        startTimeTextView = view.findViewById(R.id.picked_start_time_textview);
        endDateTextView = view.findViewById(R.id.picked_end_date_textview);
        endTimeTextView = view.findViewById(R.id.picked_end_time_textview);
        createEventButton = view.findViewById(R.id.create_event_button);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        startDate = new MyDate(calendar);
        endDate = new MyDate(calendar);
        startTime = new MyTime(calendar);
        endTime = new MyTime(calendar);

        changeDateOnTextView(startDate, startDateTextView);
        changeDateOnTextView(endDate, endDateTextView);
        changeTimeOnTextView(startTime, startTimeTextView);
        changeTimeOnTextView(endTime, endTimeTextView);

        startDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDate = new MyDate(year, month, dayOfMonth);
                        changeDateOnTextView(startDate, startDateTextView);
                    }
                }, startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth()).show();
            }
        });

        endDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDate = new MyDate(year, month, dayOfMonth);
                        changeDateOnTextView(endDate, endDateTextView);
                    }
                }, endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth()).show();
            }
        });

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTime = new MyTime(hourOfDay, minute);
                        changeTimeOnTextView(startTime, startTimeTextView);
                    }
                }, startTime.getHours(), startTime.getMinutes(), true).show();
            }
        });

        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTime = new MyTime(hourOfDay, minute);
                        changeTimeOnTextView(endTime, endTimeTextView);
                    }
                }, endTime.getHours(), endTime.getMinutes(), true).show();
            }
        });

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEventInCalendar();
            }
        });
    }

    private void createEventInCalendar() {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startTime.getHours(), startTime.getMinutes());
        Calendar finishTime = Calendar.getInstance();
        finishTime.set(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), endTime.getHours(), endTime.getMinutes());
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, finishTime.getTimeInMillis())
                .putExtra(TITLE, "Fortnite")
                .putExtra(DESCRIPTION, "Gramy gosciu, meeting zrobiony z aplikacji  hoho")
                .putExtra(EVENT_LOCATION, "Tilted towers")
                .putExtra(AVAILABILITY, AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "miwo.krzyszczak@gmail.com");
        startActivity(intent);
    }

    private void changeDateOnTextView(MyDate date, TextView textView) {
        textView.setText(date.getDayOfMonth() + "-" + date.getMonth()+1 + "-" + date.getYear());
    }

    private void changeTimeOnTextView(MyTime time, TextView textView) {
        StringBuilder minutes =new StringBuilder();
        if(time.getMinutes()<10){
            minutes.append(0);
        }
        minutes.append(time.getMinutes());
        textView.setText(time.getHours() + ":" + minutes.toString());
    }
}
