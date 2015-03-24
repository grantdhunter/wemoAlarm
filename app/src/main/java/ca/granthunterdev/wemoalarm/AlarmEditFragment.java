package ca.granthunterdev.wemoalarm;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ca.granthunterdev.wemoalarm.events.AddEvent;
import ca.granthunterdev.wemoalarm.events.DeviceListEvent;
import ca.granthunterdev.wemoalarm.models.WemoAlarm;
import de.greenrobot.event.EventBus;


@EFragment(R.layout.fragment_edit_alarm)
public class AlarmEditFragment extends Fragment {
    private EventBus mEventBus;
    private WemoHandler mWemoHandler;

    @Bean
    WemoDeviceListAdapter mWemoDeviceListAdapter;

    @ViewById
    TextView timeTextView;

    @ViewById
    Spinner wemoSpinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);

    }

    @AfterViews
    public void afterViews(){
        mWemoHandler = new WemoHandler(getActivity().getBaseContext());
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

}
