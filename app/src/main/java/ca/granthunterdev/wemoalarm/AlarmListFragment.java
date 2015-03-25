package ca.granthunterdev.wemoalarm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.joda.time.DateTime;

import ca.granthunterdev.wemoalarm.events.AddEvent;
import ca.granthunterdev.wemoalarm.events.EditEvent;
import ca.granthunterdev.wemoalarm.models.WemoAlarm;
import de.greenrobot.event.EventBus;
import se.emilsjolander.sprinkles.Model;


@EFragment(R.layout.fragment_alarm_list)
public class AlarmListFragment extends Fragment implements RadialTimePickerDialog.OnTimeSetListener {
    private static final String FRAG_TIME_PICKER = "timePickerDialogFragment";

    @ViewById
    TextView emptyTextView;

    @ViewById
    ListView alarmListView;

    @Bean
    WemoAlarmListAdapter mWemoAlarmListAdapter;

    FragmentManager fragmentManager;
    private EventBus mEventBus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();


        return null;
    }

    @AfterViews
    void bindAdapter() {
        alarmListView.setAdapter(mWemoAlarmListAdapter);
        alarmListView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.INVISIBLE);
    }

    @Click(R.id.newAlarm)
    void addAlarm() {
        DateTime now = DateTime.now();
        RadialTimePickerDialog radialTimePickerDialog;
        radialTimePickerDialog = RadialTimePickerDialog
                .newInstance(this, now.getHourOfDay(), now.getMinuteOfHour(),
                        DateFormat.is24HourFormat(getActivity().getBaseContext()));
        radialTimePickerDialog.show(fragmentManager, FRAG_TIME_PICKER);

    }

    @ItemClick(R.id.alarmListView)
    public void alarmListViewClicked(WemoAlarm wemoAlarm) {
        Log.i("log", "on list item click");
        mEventBus.post(new EditEvent(wemoAlarm));
    }


    @Override
    public void onTimeSet(RadialTimePickerDialog radialTimePickerDialog, int hourOfDay, int minutes) {
        Log.i("onTimeSet", String.valueOf(minutes));
        final WemoAlarm wemoAlarm = new WemoAlarm(hourOfDay, minutes);

        wemoAlarm.saveAsync(new Model.OnSavedCallback() {
            @Override
            public void onSaved() {
                long id = wemoAlarm.getId();
                mEventBus.post(new AddEvent(id));
            }
        });
    }

    public void onEventMainThread(AddEvent event) {
        mWemoAlarmListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mEventBus.unregister(this);
    }

}
