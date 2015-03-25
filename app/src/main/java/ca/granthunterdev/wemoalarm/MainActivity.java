package ca.granthunterdev.wemoalarm;

        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentTransaction;

        import org.androidannotations.annotations.AfterViews;
        import org.androidannotations.annotations.EActivity;

        import ca.granthunterdev.wemoalarm.events.AddEvent;
        import ca.granthunterdev.wemoalarm.events.EditEvent;
        import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
    EventBus mEventBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @AfterViews
    void afterViews() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AlarmListFragment alarmListFragment = new AlarmListFragment_();

        ft.add(R.id.fragmentContainer, alarmListFragment)
                .commit();
    }


    public void onEventMainThread(EditEvent event){
       FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AlarmEditFragment alarmEditFragment = new AlarmEditFragment_();

        Bundle arg = new Bundle();
        arg.putSerializable("wemoAlarm", event.getmWemoAlarm());
        alarmEditFragment.setArguments(arg);
        ft.replace(R.id.fragmentContainer, alarmEditFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }
}
