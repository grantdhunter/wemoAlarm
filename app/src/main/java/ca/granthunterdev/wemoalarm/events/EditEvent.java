package ca.granthunterdev.wemoalarm.events;

import ca.granthunterdev.wemoalarm.models.WemoAlarm;

/**
 * Created by Grant on 2/19/2015.
 */
public class EditEvent {
    WemoAlarm mWemoAlarm;
    public EditEvent(WemoAlarm wemoAlarm){
        this.mWemoAlarm = wemoAlarm;
    }

    public WemoAlarm getmWemoAlarm() {
        return mWemoAlarm;
    }

    public void setmWemoAlarm(WemoAlarm mWemoAlarm) {
        this.mWemoAlarm = mWemoAlarm;
    }
}
