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
public class WemoDeviceListAdapter  extends BaseAdapter implements SpinnerAdapter {
    List<WeMoDevice> mWemoDevices;

    @RootContext
    Context context;

    public WemoDeviceListAdapter() {

    }
    public void populateList(ArrayList arrayList){
        mWemoDevices = arrayList;
    }

    @AfterInject
    void initAdapter(){
        mWemoDevices = new ArrayList();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getCount() {
        return mWemoDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return mWemoDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WemoDeviceItemView wemoDeviceItemView;

        if(convertView == null){
            wemoDeviceItemView = new WemoDeviceItemView_(context);
        } else {
            wemoDeviceItemView = (WemoDeviceItemView) convertView;
        }
        return wemoDeviceItemView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
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
