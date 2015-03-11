package ca.granthunterdev.wemoalarm;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import ca.granthunterdev.wemoalarm.models.WemoAlarm;
import se.emilsjolander.sprinkles.Query;

/**
 * Created by Grant on 2/13/2015.
 */
@EBean
public class WemoAlarmListAdapter extends BaseAdapter {

    List<WemoAlarm> mWemoAlarms;

    @RootContext
    Context context;

    public WemoAlarmListAdapter(){

    }

    @AfterInject
    void initAdapter(){
        mWemoAlarms = Query.all(WemoAlarm.class).get().asList();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mWemoAlarms = Query.all(WemoAlarm.class).get().asList();
    }

    @Override
    public int getCount() {
        return mWemoAlarms.size();
    }

    @Override
    public WemoAlarm getItem(int position) {
        return mWemoAlarms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mWemoAlarms.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WemoAlarmItemView wemoAlarmItemView;
        if(convertView == null){
            wemoAlarmItemView = WemoAlarmItemView_.build(context);
        } else {
            wemoAlarmItemView = (WemoAlarmItemView) convertView;
        }

        wemoAlarmItemView.bind(getItem(position));
        return wemoAlarmItemView;
    }
}
