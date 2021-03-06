package ca.granthunterdev.wemoalarm;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import ca.granthunterdev.wemoalarm.models.WemoAlarm;

/**
 * Created by Grant on 2/13/2015.
 */
@EViewGroup(R.layout.wemo_alarm_item)
public class WemoAlarmItemView extends LinearLayout {

    private Context mContext;
    @ViewById
    TextView nameTextView;

    @ViewById
    TextView timeTextView;

    @ViewById
    TextView ampmTextView;
    public WemoAlarmItemView(Context context) {
        super(context);
        this.mContext = context;
    }

    public void bind(WemoAlarm wemoAlarm) {

        nameTextView.setText(wemoAlarm.getName());
        timeTextView.setText(wemoAlarm.displayTime(mContext));
        if (!DateFormat.is24HourFormat(mContext)) {
            ampmTextView.setText(wemoAlarm.displayAmPm());
        }
    }
}
