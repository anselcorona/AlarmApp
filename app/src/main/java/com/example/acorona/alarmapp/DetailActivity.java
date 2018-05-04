package com.example.acorona.alarmapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        assert extras != null;
        Alarma a = extras.getParcelable(ALARM_KEY);

        assert a != null;
        days = a.getDays();
        time = a.getTime();
        name = a.getName();


        Name.setText(name);
        Hour.setText(a.Date2String(time,"hh"));
        Minute.setText(a.Date2String(time, "mm"));
        amorpm = a.Date2String(time, "aa");
        AMoPM = !amorpm.equalsIgnoreCase("am");

        setcolorday(monday, 0);
        setcolorday(tuesday, 1);
        setcolorday(wednesday, 2);
        setcolorday(thursday, 3);
        setcolorday(friday, 4);
        setcolorday(saturday, 5);
        setcolorday(sunday, 6);

        AM.setTextColor(!AMoPM? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
        PM.setTextColor(AMoPM? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
    }


    @Override
    public void onClick(View view){
        int id = view.getId();

        switch (id) {
            case R.id.detail_activity_AM:
                AMoPM=false;
                AM.setTextColor(getResources().getColor(R.color.BLACK));
                PM.setTextColor(AMoPM? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
                break;
            case R.id.detail_activity_PM:
                AMoPM=true;
                AM.setTextColor(getResources().getColor(R.color.GRAY));
                PM.setTextColor(AMoPM? getResources().getColor(R.color.BLACK):getResources().getColor(R.color.GRAY));
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
                if((!Hour.getText().toString().isEmpty()&&!Minute.getText().toString().isEmpty())
                        && (Integer.parseInt(Hour.getText().toString()) >0 && Integer.parseInt(Hour.getText().toString())<13)
                        && (Integer.parseInt(Minute.getText().toString()) >=0 && Integer.parseInt(Minute.getText().toString())<60)){
                    String name = Name.getText().toString();
                    if(name.isEmpty()){
                        name = "untitled";
                    }
                    int h = Integer.parseInt(Hour.getText().toString());
                    int m = Integer.parseInt(Minute.getText().toString());
                    Date date = getDate(h, m, AMoPM?"pm":"am");
                    result = new Alarma(name, date, days);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(ALARM_KEY,result);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
                else{
                    if(Hour.getText().toString().isEmpty()){
                        Hour.setError("No puede estar vacio");
                    }
                    if(!(Integer.parseInt(Hour.getText().toString()) >0 && Integer.parseInt(Hour.getText().toString())<13)){
                        Hour.setError("Valor inválido");
                    }
                    if(Minute.getText().toString().isEmpty()){
                        Minute.setError("No puede estar vacio");
                    }
                    if(!(Integer.parseInt(Minute.getText().toString()) >=0 && Integer.parseInt(Minute.getText().toString())<60)){
                        Minute.setError("Valor inválido");
                    }
                }

                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private Date getDate(int hour, int minute, String amorpm){
        String resultingstring = String.valueOf(hour) + ":" + String.valueOf(minute) + " " + amorpm;
        Date date = null;
        try {
            date = new SimpleDateFormat("hh:mm aa").parse(resultingstring);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
