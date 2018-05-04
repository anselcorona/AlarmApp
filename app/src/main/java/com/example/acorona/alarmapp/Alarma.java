package com.example.acorona.alarmapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Alarma implements Parcelable {

    private String name;
    private Date time;
    private ArrayList<Boolean> days;



    public Alarma(String name, Date time, ArrayList<Boolean> days) {
        this.name = name;
        this.time = time;
        this.days = days;
    }

    public Date getTime() {
        return time;
    }

    public ArrayList<Boolean> getDays() {
        return days;
    }

    public String getName() {
        return name;
    }


    public String getTimeString() {
        String time_string = Date2String(this.time, "hh:mm aa");
        return time_string;
    }

    public String Date2String(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }


    public String getDaysString() {
        String days = "";
        if (everyDay(this.days)) {
            days = "Everyday";
        } else {
            days = perDay(this.days);
        }
        return days;
    }


    private Boolean everyDay(ArrayList<Boolean> days) {
        for (Boolean d : days) {
            if (!d) {
                return false;
            }
        }
        return true;
    }

    private String perDay(ArrayList<Boolean> days) {
        String result = "";
        if (!everyDay(this.days)) {
            if (days.get(0)) {
                result = result.concat("Lun");
            }
            if (days.get(1)) {
                if (result.isEmpty()) {
                    result = result.concat("Mar");
                } else {
                    result = result.concat(", Mar");
                }
            }
            if (days.get(2)) {
                if (result.isEmpty()) {
                    result = result.concat("Mie");
                } else {
                    result = result.concat(", Mie");
                }
            }
            if (days.get(3)) {
                if (result.isEmpty()) {
                    result = result.concat("Jue");
                } else {
                    result = result.concat(", Jue");
                }
            }
            if (days.get(4)) {
                if (result.isEmpty()) {
                    result = result.concat("Vie");
                } else {
                    result = result.concat(", Vie");
                }
            }
            if (days.get(5)) {
                if (result.isEmpty()) {
                    result = result.concat("Sáb");
                } else {
                    result = result.concat(", Sáb");
                }
            }
            if (days.get(6)) {
                if (result.isEmpty()) {
                    result = result.concat("Dom");
                } else {
                    result = result.concat(", Dom.");
                }
            }
        }
        return result;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setDays(ArrayList<Boolean> days) {
        this.days = days;
    }


    protected Alarma(Parcel in) {
        name = in.readString();
        long tmpTime = in.readLong();
        time = tmpTime != -1 ? new Date(tmpTime) : null;
        if (in.readByte() == 0x01) {
            days = new ArrayList<Boolean>();
            in.readList(days, Boolean.class.getClassLoader());
        } else {
            days = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(time != null ? time.getTime() : -1L);
        if (days == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(days);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Alarma> CREATOR = new Parcelable.Creator<Alarma>() {
        @Override
        public Alarma createFromParcel(Parcel in) {
            return new Alarma(in);
        }

        @Override
        public Alarma[] newArray(int size) {
            return new Alarma[size];
        }
    };
}