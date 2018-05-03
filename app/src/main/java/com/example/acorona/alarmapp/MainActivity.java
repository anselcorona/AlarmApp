package com.example.acorona.alarmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String ALARM_KEY = "ALARMA";
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

        alarmaList.add(new Alarma("Metformina 500mg", new Date(), days, false));
        alarmaList.add(new Alarma("DOS", new Date(), days, true));
        alarmaList.add(new Alarma("TRES", new Date(), days, false));
        alarmaList.add(new Alarma("CUATRO", new Date(), days, true));
        alarmaList.add(new Alarma("CINCO", new Date(), days, false));
        alarmaList.add(new Alarma("SEIS", new Date(), days, true));
        alarmaList.add(new Alarma("SIETE", new Date(), days, false));
        alarmaList.add(new Alarma("OCHO", new Date(), days, true));
        alarmaList.add(new Alarma("Metformina 500mg", new Date(), days, false));
        alarmaList.add(new Alarma("DOS", new Date(), days, true));
        alarmaList.add(new Alarma("TRES", new Date(), days, false));
        alarmaList.add(new Alarma("CUATRO", new Date(), days, true));
        alarmaList.add(new Alarma("CINCO", new Date(), days, false));
        alarmaList.add(new Alarma("SEIS", new Date(), days, true));
        alarmaList.add(new Alarma("SIETE", new Date(), days, false));
        alarmaList.add(new Alarma("OCHO", new Date(), days, true));

        AlarmAdapter alarmAdapter = new AlarmAdapter(this, alarmaList);
        alarmRecycler.setAdapter(alarmAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(MainActivity.this, DetailActivity.class);
                details.putExtra(ALARM_KEY, sampleAlarm);
                startActivity(details);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
