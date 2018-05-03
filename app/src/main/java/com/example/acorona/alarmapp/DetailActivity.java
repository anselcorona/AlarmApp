package com.example.acorona.alarmapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import static com.example.acorona.alarmapp.MainActivity.ALARM_KEY;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView AM;
    TextView PM;

    EditText Hour;
    EditText Minute;
    EditText Name;

    TextView monday;
    TextView tuesday;
    TextView wednesday;
    TextView thursday;
    TextView friday;
    TextView saturday;
    TextView sunday;

    TextView cancel;
    TextView save;

    ArrayList<Boolean> days;
    Date time;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        AM = findViewById(R.id.detail_activity_AM);
        PM = findViewById(R.id.detail_activity_PM);
        Hour = findViewById(R.id.detail_activity_hour);
        Minute = findViewById(R.id.detail_activity_minute);
        Name = findViewById(R.id.detail_activity_name);
        monday = findViewById(R.id.detail_activity_monday);
        tuesday = findViewById(R.id.detail_activity_tuesday);
        wednesday = findViewById(R.id.detail_activity_wednesday);
        thursday = findViewById(R.id.detail_activity_thursday);
        friday = findViewById(R.id.detail_activity_friday);
        saturday = findViewById(R.id.detail_activity_saturday);
        sunday = findViewById(R.id.detail_activity_sunday);
        cancel = findViewById(R.id.detail_activity_cancel);
        save = findViewById(R.id.detail_activity_save);

        AM.setOnClickListener(this);
        PM.setOnClickListener(this);
        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
        saturday.setOnClickListener(this);
        sunday.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        Alarma a = extras.getParcelable(ALARM_KEY);

        days = a.getDays();
        time = a.getTime();
        name = a.getName();


        Name.setText(name);
        Hour.setText(a.Date2String(time,"HH"));
        Minute.setText(a.Date2String(time, "mm"));

        if(days.get(0)){
            monday.setTextColor(getResources().getColor(R.color.BLACK));
        }
        if(days.get(1)){
            tuesday.setTextColor(getResources().getColor(R.color.BLACK));
        }
        if(days.get(2)){
            wednesday.setTextColor(getResources().getColor(R.color.BLACK));
        }
        if(days.get(3)){
            thursday.setTextColor(getResources().getColor(R.color.BLACK));
        }
        if(days.get(4)){
            friday.setTextColor(getResources().getColor(R.color.BLACK));
        }
        if(days.get(5)){
            saturday.setTextColor(getResources().getColor(R.color.BLACK));
        }
        if(days.get(6)){
            sunday.setTextColor(getResources().getColor(R.color.BLACK));
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.detail_activity_AM:
                break;
            case R.id.detail_activity_PM:
                break;
            case R.id.detail_activity_monday:
                break;
            case R.id.detail_activity_tuesday:
                break;
            case R.id.detail_activity_wednesday:
                break;
            case R.id.detail_activity_thursday:
                break;
            case R.id.detail_activity_friday:
                break;
            case R.id.detail_activity_saturday:
                break;
            case R.id.detail_activity_sunday:
                break;
            case R.id.detail_activity_cancel:
                break;
            case R.id.detail_activity_save:
                break;
        }
    }
}
