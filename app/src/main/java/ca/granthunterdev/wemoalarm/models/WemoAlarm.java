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

    @Column("daysOfWeek")
    private long mDaysOfWeek;

    @Column("hour")
    private long mHour;

    @Column("Minutes")
    private long mMinutes;

    @Column("alarmSound")
    private String mAlarmSound;

    @Column("enabled")
    private boolean mEnabled;


    public WemoAlarm() {

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

    public long getDaysOfWeek() {
        return mDaysOfWeek;
    }

    public void setDaysOfWeek(int daysOfWeek) {
        this.mDaysOfWeek = daysOfWeek;
    }

    public long getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        this.mHour = hour;
    }

    public long getMinutes() {
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
            time = this.getHour() % 12 + ":" + this.getMinutes();
        }
        return time;
    }
    public String displayAmPm() {
            return this.getHour() >= 12 ? "PM" : "AM";
    }
}
