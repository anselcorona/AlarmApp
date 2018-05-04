package com.example.acorona.alarmapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String ALARM_KEY = "ALARMA";
    private static final int INSERT_ALARM_REQUEST_CODE = 1;
    Alarma sampleAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        RecyclerView alarmRecycler = findViewById(R.id.activity_main_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        alarmRecycler.setLayoutManager(linearLayoutManager);

        ArrayList<Alarma> alarmaList = new ArrayList<>();

        ArrayList<Boolean> days = new ArrayList<Boolean>();
        days.add(true);
        days.add(true);
        days.add(true);
        days.add(true);
        days.add(true);
        days.add(false);
        days.add(true);
        sampleAlarm = new Alarma("Metformina 500mg", new Date(), days, true);

        AlarmAdapter alarmAdapter = new AlarmAdapter(this, alarmaList);
        alarmRecycler.setAdapter(alarmAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(MainActivity.this, DetailActivity.class);
                details.putExtra(ALARM_KEY, sampleAlarm);
                startActivityForResult(details, INSERT_ALARM_REQUEST_CODE);
            }
        });

        alarmAdapter.setOnAlarmClickListener(new AlarmAdapter.OnAlarmClickListener() {
            @Override
            public void onAlarmClick(Alarma alarma) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(ALARM_KEY, alarma);
                startActivityForResult(intent, INSERT_ALARM_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Alarma a = data.getParcelableExtra(ALARM_KEY);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Nueva alarma cancelada", Toast.LENGTH_LONG).show();
            }
        }
    }
}
