package ca.granthunterdev.wemoalarm;

import android.content.Context;

import com.belkin.wemo.localsdk.WeMoDevice;
import com.belkin.wemo.localsdk.WeMoSDKContext;

import java.util.ArrayList;

import ca.granthunterdev.wemoalarm.events.DeviceListEvent;
import de.greenrobot.event.EventBus;

/**
 * Created by Grant on 2/13/2015.
 */
public class WemoHandler implements WeMoSDKContext.NotificationListener {
    private WeMoSDKContext mWeMoSDKContext = null;
    private EventBus mEventBus;


    public WemoHandler(Context context) {
        mWeMoSDKContext = new WeMoSDKContext(context);
        mWeMoSDKContext.addNotificationListener(this);
        mWeMoSDKContext.refreshListOfWeMoDevicesOnLAN();
        mEventBus = EventBus.getDefault();
    }


    @Override
    public void onNotify(String event, String udn) {
        switch (event) {
            case WeMoSDKContext.REFRESH_LIST:

                ArrayList<String> uuIdList = mWeMoSDKContext.getListOfWeMoDevicesOnLAN();
                mEventBus.post(new DeviceListEvent(getDeviceList(uuIdList)));

                break;
            case WeMoSDKContext.SET_STATE:
                break;
            case WeMoSDKContext.CHANGE_STATE:
                break;
            case WeMoSDKContext.ADD_DEVICE:
                break;
            case WeMoSDKContext.REMOVE_DEVICE:
                break;
        }
    }

    private ArrayList getDeviceList(ArrayList<String> uuIdList) {
        ArrayList<WeMoDevice> deviceList = new ArrayList<WeMoDevice>();
        for (String uuId : uuIdList) {
            WeMoDevice device = mWeMoSDKContext.getWeMoDeviceByUDN(uuId);
            deviceList.add(device);
        }
        return deviceList;
    }

    public void stop() {
        mWeMoSDKContext.removeNotificationListener(this);
        mWeMoSDKContext.stop();
    }
}
