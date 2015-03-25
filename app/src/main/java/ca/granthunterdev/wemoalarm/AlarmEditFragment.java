package ca.granthunterdev.wemoalarm;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ca.granthunterdev.wemoalarm.events.AddEvent;
import ca.granthunterdev.wemoalarm.events.DeviceListEvent;
import ca.granthunterdev.wemoalarm.models.WemoAlarm;
import de.greenrobot.event.EventBus;
import se.emilsjolander.sprinkles.Model;


@EFragment(R.layout.fragment_edit_alarm)
public class AlarmEditFragment extends Fragment implements RadialTimePickerDialog.OnTimeSetListener {
    private static final String FRAG_TIME_PICKER = "timePickerDialogFragment";

    private EventBus mEventBus;
    private WemoHandler mWemoHandler;
    private WemoAlarm wemoAlarm;
    private Context mContext;
    @Bean
    WemoDeviceListAdapter mWemoDeviceListAdapter;

    @ViewById
    TextView timeTextView;

    @ViewById
    TextView ampmTextView;

    @ViewById
    Spinner wemoSpinner;
    private FragmentManager fragmentManager;


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

        updateView();
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

    @Click(R.id.sundayToggle)
    public void sundayToggle(){

    }
    @Click(R.id.mondayToggle)
    public void mondayToggle(){

    }
    @Click(R.id.tuesdayToggle)
    public void tuesdayToggle(){

    }
    @Click(R.id.wednesdayToggel)
    public void wednesdayToggle(){

    }
    @Click(R.id.thursdayToggle)
    public void thursdayToggle(){

    }
    @Click(R.id.fridayToggle)
    public void fridayToggle(){

    }
    @Click(R.id.saturdayToggle)
    public void saturdayToggle(){

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
        wemoSpinner.setAdapter(mWemoDeviceListAdapter);
    }

    private void updateView(){
        timeTextView.setText(wemoAlarm.displayTime(mContext));
        ampmTextView.setText(wemoAlarm.displayAmPm());
    }

}
