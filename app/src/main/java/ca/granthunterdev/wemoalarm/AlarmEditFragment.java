package ca.granthunterdev.wemoalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.belkin.wemo.localsdk.WeMoDevice;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import ca.granthunterdev.wemoalarm.events.AddEvent;
import ca.granthunterdev.wemoalarm.events.DeviceListEvent;
import ca.granthunterdev.wemoalarm.models.WemoAlarm;
import de.greenrobot.event.EventBus;
import se.emilsjolander.sprinkles.Model;


@EFragment(R.layout.fragment_edit_alarm)
public class AlarmEditFragment extends Fragment implements RadialTimePickerDialog.OnTimeSetListener {
    private static final String FRAG_TIME_PICKER = "timePickerDialogFragment";
    private static final int ALARM_SOUND_RESULT = 1;

    private EventBus mEventBus;
    private WemoHandler mWemoHandler;
    private WemoAlarm wemoAlarm;
    private Context mContext;
    private FragmentManager fragmentManager;


    @Bean
    WemoDeviceListAdapter mWemoDeviceListAdapter;

    @ViewById
    TextView timeTextView;

    @ViewById
    TextView ampmTextView;

    @ViewById
    Spinner wemoSpinner;

    @ViewById
    Button alarmSoundButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);

        wemoAlarm = (WemoAlarm) getArguments().getSerializable("wemoAlarm");

        mContext = this.getActivity().getBaseContext();

        fragmentManager = getActivity().getSupportFragmentManager();

    }

    @AfterViews
    public void afterViews() {
        mWemoHandler = new WemoHandler(getActivity().getBaseContext());

        alarmSoundButton.setText(wemoAlarm.getAlarmSound());
        updateView();
        setupAlarmSoundButton();
        wemoSpinner.setAdapter(mWemoDeviceListAdapter);
    }


    @Click(R.id.timeTextView)
    public void editTime() {
        RadialTimePickerDialog radialTimePickerDialog;
        radialTimePickerDialog = RadialTimePickerDialog
                .newInstance(this, wemoAlarm.getHour(), wemoAlarm.getMinutes(),
                        DateFormat.is24HourFormat(getActivity().getBaseContext()));
        radialTimePickerDialog.show(fragmentManager, FRAG_TIME_PICKER);
    }

    @Override
    public void onTimeSet(RadialTimePickerDialog radialTimePickerDialog, int hour, int minutes) {
        wemoAlarm.setHour(hour);
        wemoAlarm.setMinutes(minutes);
        wemoAlarm.saveAsync(new Model.OnSavedCallback() {
            @Override
            public void onSaved() {
                updateView();
            }
        });
    }

    @ItemSelect(R.id.wemoSpinner)
    public void deviceSelect(boolean selected, WeMoDevice selectedDevice){
        Log.i("deviceSelected", selectedDevice.getFriendlyName());
    }

    @Click(R.id.sundayToggle)
    public void sundayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.SUNDAY, view.isEnabled());
    }

    @Click(R.id.mondayToggle)
    public void mondayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.MONDAY, view.isEnabled());
    }

    @Click(R.id.tuesdayToggle)
    public void tuesdayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.TUESDAY, view.isEnabled());
    }

    @Click(R.id.wednesdayToggel)
    public void wednesdayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.WEDNESDAY, view.isEnabled());
    }

    @Click(R.id.thursdayToggle)
    public void thursdayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.THURSDAY, view.isEnabled());
    }

    @Click(R.id.fridayToggle)
    public void fridayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.FRIDAY, view.isEnabled());
    }

    @Click(R.id.saturdayToggle)
    public void saturdayToggle(View view) {
        wemoAlarm.setDayOfWeek(WemoAlarm.SATURDAY, view.isEnabled());
    }


    @Click(R.id.alarmSoundButton)
    public void pickAlarmSound(View view) {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Alarm");

        startActivityForResult(intent, ALARM_SOUND_RESULT);
    }

    @OnActivityResult(ALARM_SOUND_RESULT)
    public void alarmSoundResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            String sound = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
                    .toString();

            String title = RingtoneManager.getRingtone(getActivity(), Uri.parse(sound))
                    .getTitle(getActivity());
            // set button text to name of the sound
            alarmSoundButton.setText(title);
            wemoAlarm.setAlarmSound(sound);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
        mWemoHandler.stop();
    }

    public void onEventMainThread(DeviceListEvent event) {
        Log.i("event", "Device List Event");
        Log.i("event", String.valueOf(event.getDeviceList()));
        mWemoDeviceListAdapter.populateList(event.getDeviceList());
        wemoSpinner.setVisibility(View.VISIBLE);

    }

    private void updateView() {
        timeTextView.setText(wemoAlarm.displayTime(mContext));
        ampmTextView.setText(wemoAlarm.displayAmPm());
    }

    private void setupAlarmSoundButton(){
        String title;
        if(wemoAlarm.getAlarmSound() == null){
            Uri sound = RingtoneManager.getActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_ALARM);
            title = RingtoneManager.getRingtone(mContext, sound)
                    .getTitle(mContext);
        } else {
            title = RingtoneManager.getRingtone(mContext, Uri.parse(wemoAlarm.getAlarmSound()))
                    .getTitle(mContext);
        }
        alarmSoundButton.setText(title);
    }

}
