package ca.granthunterdev.wemoalarm;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.belkin.wemo.localsdk.WeMoDevice;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Grant on 2/19/2015.
 */
@EViewGroup(R.layout.wemo_device_item)
public class WemoDeviceItemView extends LinearLayout {
    @ViewById
    TextView deviceName;

    public WemoDeviceItemView(Context context) {
            super(context);
    }

    public void bind(WeMoDevice weMoDevice) {
        deviceName.setText(weMoDevice.getFriendlyName());
    }
}
