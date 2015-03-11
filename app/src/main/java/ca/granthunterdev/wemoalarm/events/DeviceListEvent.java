package ca.granthunterdev.wemoalarm.events;

import com.belkin.wemo.localsdk.WeMoDevice;

import java.util.ArrayList;

/**
 * Created by Grant on 2/19/2015.
 */
public class DeviceListEvent {
    ArrayList<WeMoDevice> mDeviceList;
    public DeviceListEvent(ArrayList<WeMoDevice> deviceList) {
        this.mDeviceList = deviceList;
    }

    public ArrayList<WeMoDevice> getDeviceList() {
        return mDeviceList;
    }

    public void setDeviceList(ArrayList<WeMoDevice> mDeviceList) {
        this.mDeviceList = mDeviceList;
    }
}
