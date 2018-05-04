package com.example.acorona.alarmapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Alarma> alarmaList;
    private OnAlarmClickListener onAlarmClickListener;


    public void setOnAlarmClickListener(OnAlarmClickListener onAlarmClickListener){
        this.onAlarmClickListener = onAlarmClickListener;
    }

    public AlarmAdapter(Context context, ArrayList<Alarma> alarmaList){
        this.context = context;
        this.alarmaList = alarmaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,  int position) {
        final Alarma[] alarma = {alarmaList.get(position)};
        final int p = position;
        holder.daysTextView.setText(alarma[0].getDaysString());
        holder.timeTextView.setText(alarma[0].getTimeString());
        holder.nameTextView.setText(alarma[0].getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAlarmClickListener.onAlarmClick(alarma[0]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alarmaList.size();
    }

    public interface OnAlarmClickListener{
        void onAlarmClick(Alarma alarma);
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView timeTextView;
        public TextView daysTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.list_item_name);
            timeTextView = itemView.findViewById(R.id.list_item_time);
            daysTextView = itemView.findViewById(R.id.list_item_days);
        }
    }
}
