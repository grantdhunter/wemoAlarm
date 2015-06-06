package ca.granthunterdev.wemoalarm;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;

import com.belkin.wemo.localsdk.WeMoDevice;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grant on 2/19/2015.
 */
@EBean
public class WemoDeviceListAdapter  extends BaseAdapter {
    List<WeMoDevice> mWemoDevices;

    @RootContext
    Context context;

    public WemoDeviceListAdapter() {

    }
    public void populateList(ArrayList arrayList){
        mWemoDevices = arrayList;
        this.notifyDataSetChanged();
    }

    @AfterInject
    void initAdapter(){
        mWemoDevices = new ArrayList();
    }

    @Override
    public int getCount() {
        return mWemoDevices.size();
    }

    @Override
    public WeMoDevice getItem(int position) {
        return mWemoDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WemoDeviceItemView wemoDeviceItemView;

        if(convertView == null){
            wemoDeviceItemView = WemoDeviceItemView_.build(context);
        } else {
            wemoDeviceItemView = (WemoDeviceItemView) convertView;
        }

        wemoDeviceItemView.bind(getItem(position));
        return wemoDeviceItemView;
    }


    @Override
    public boolean isEmpty() {
        return mWemoDevices.isEmpty();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }
}
