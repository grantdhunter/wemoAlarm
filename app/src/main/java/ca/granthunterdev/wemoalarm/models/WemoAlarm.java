package ca.granthunterdev.wemoalarm.models;

import android.content.Context;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import android.text.format.DateFormat;


import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;
import se.emilsjolander.sprinkles.annotations.Column;

/**
 * Created by Grant on 2/13/2015.
 */
@Table("Alarms")
public class WemoAlarm extends Model implements Serializable {
    @Key
    @AutoIncrement
    @Column("id")
    private long mId;

    @Column("deviceUdn")
    private String mDeviceUdn;

    @Column("name")
    private String mName;
    /*
     * sunday       00000001
     * monday       00000010
     * tuesday      00000100
     * wednesday    00001000
     * thursday     00010000
     * friday       00100000
     * saturday     01000000
     */
    @Column("daysOfWeek")
    private int mDaysOfWeek;

    @Column("hour")
    private int mHour;

    @Column("minutes")
    private int mMinutes;

    @Column("alarmSound")
    private String mAlarmSound;

    @Column("enabled")
    private boolean mEnabled;


    public WemoAlarm() {

    }

    public WemoAlarm(int hour, int minutes) {
        this.mHour = hour;
        this.mMinutes = minutes;
    }

    public long getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getDeviceUdn() {
        return mDeviceUdn;
    }

    public void setDeviceUdn(String deviceUdn) {
        this.mDeviceUdn = deviceUdn;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getDaysOfWeek() {
        return mDaysOfWeek;
    }

    public void setDaysOfWeek(int daysOfWeek) {
        this.mDaysOfWeek = daysOfWeek;
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        this.mHour = hour;
    }

    public int getMinutes() {
        return mMinutes;
    }

    public void setMinutes(int minutes) {
        this.mMinutes = minutes;
    }

    public String getAlarmSound() {
        return mAlarmSound;
    }

    public void setAlarmSound(String AlarmSound) {
        this.mAlarmSound = AlarmSound;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }

    public String displayTime(Context context) {
        String time;

        if (DateFormat.is24HourFormat(context)) {
            time = this.getHour() + ":" + this.getMinutes();
        } else {
            int hour = this.getHour() % 12;
            int hour12 = hour == 0 ? 12 : hour;

            time = hour12 + ":" + this.getMinutes();
        }
        return time;
    }

    public String displayAmPm() {
        return this.getHour() >= 12 ? "PM" : "AM";
    }

    public int setDayOfWeek(int day, boolean enabled){
        this.mDaysOfWeek
    }
}
