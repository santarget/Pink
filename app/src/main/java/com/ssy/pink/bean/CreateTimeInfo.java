package com.ssy.pink.bean;

import java.io.Serializable;

public class CreateTimeInfo implements Serializable{
    private static final long serialVersionUID = 4204920788264931713L;
    private long date;

    private long hours;

    private long seconds;

    private long month;

    private long timezoneOffset;

    private long year;

    private long minutes;

    private long time;

    private long day;

    public void setDate(long date){
        this.date = date;
    }
    public long getDate(){
        return this.date;
    }
    public void setHours(long hours){
        this.hours = hours;
    }
    public long getHours(){
        return this.hours;
    }
    public void setSeconds(long seconds){
        this.seconds = seconds;
    }
    public long getSeconds(){
        return this.seconds;
    }
    public void setMonth(long month){
        this.month = month;
    }
    public long getMonth(){
        return this.month;
    }
    public void setTimezoneOffset(long timezoneOffset){
        this.timezoneOffset = timezoneOffset;
    }
    public long getTimezoneOffset(){
        return this.timezoneOffset;
    }
    public void setYear(long year){
        this.year = year;
    }
    public long getYear(){
        return this.year;
    }
    public void setMinutes(long minutes){
        this.minutes = minutes;
    }
    public long getMinutes(){
        return this.minutes;
    }
    public void setTime(long time){
        this.time = time;
    }
    public long getTime(){
        return this.time;
    }
    public void setDay(long day){
        this.day = day;
    }
    public long getDay(){
        return this.day;
    }

    @Override
    public String toString() {
        return "CreateTimeInfo{" +
                "date=" + date +
                ", hours=" + hours +
                ", seconds=" + seconds +
                ", month=" + month +
                ", timezoneOffset=" + timezoneOffset +
                ", year=" + year +
                ", minutes=" + minutes +
                ", time=" + time +
                ", day=" + day +
                '}';
    }
}
