package com.example.acorona.alarmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    String amorpm;
    String time_string;
    Boolean AMoPM;
    Intent returnIntent;


    Alarma result;


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
        amorpm = a.Date2String(time, "aa");

        setcolorday(monday, 0);
        setcolorday(tuesday, 1);
        setcolorday(wednesday, 2);
        setcolorday(thursday, 3);
        setcolorday(friday, 4);
        setcolorday(saturday, 5);
        setcolorday(sunday, 6);

        AM.setTextColor(amorpm.charAt(0)=='a'? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
        PM.setTextColor(amorpm.charAt(0)=='p'? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.detail_activity_AM:
                AMoPM=false;
                AM.setTextColor(amorpm.charAt(0)=='a'? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
                break;
            case R.id.detail_activity_PM:
                AMoPM=true;
                PM.setTextColor(amorpm.charAt(0)=='p'? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
                break;
            case R.id.detail_activity_monday:
                setday(0);
                setcolorday(monday, 0);
                break;
            case R.id.detail_activity_tuesday:
                setday(1);
                setcolorday(tuesday, 1);
                break;
            case R.id.detail_activity_wednesday:
                setday(2);
                setcolorday(wednesday, 2);
                break;
            case R.id.detail_activity_thursday:
                setday(3);
                setcolorday(thursday, 3);
                break;
            case R.id.detail_activity_friday:
                setday(4);
                setcolorday(friday, 4);
                break;
            case R.id.detail_activity_saturday:
                setday(5);
                setcolorday(saturday, 5);
                break;
            case R.id.detail_activity_sunday:
                setday(6);
                setcolorday(sunday, 6);
                break;
            case R.id.detail_activity_cancel:
                returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                break;
            case R.id.detail_activity_save:
                String name = Name.getText().toString();
                int h = Integer.parseInt(Hour.getText().toString());
                int m = Integer.parseInt(Minute.getText().toString());
                time_string = getTime_string(h, m, AMoPM?"am":"pm");
                try {
                    Date date = new SimpleDateFormat("hh:ss aa").parse(time_string);
                    result = new Alarma(name, date, days, true);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",result);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                } catch (ParseException e) {
                    Toast.makeText(this,"Error en conversion de fecha", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private String getTime_string(int hour, int minute, String amorpm){
        String resultingstring = String.valueOf(hour) + ":" + String.valueOf(minute) + " " + amorpm;
        return resultingstring;
    }

    private void setday(int day){
        if(days.get(day)){
            days.set(day,false);
        }else{
            days.set(day,true);
        }
    }
    private void setcolorday(TextView t, int d){
        t.setTextColor(days.get(d)? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
    }
}
