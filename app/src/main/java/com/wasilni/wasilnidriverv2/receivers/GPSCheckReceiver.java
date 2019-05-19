package com.wasilni.wasilnidriverv2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

import com.wasilni.wasilnidriverv2.mvp.model.EventBusMessage;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Mahmoud Mattar on 12/12/2018.
 */

public class GPSCheckReceiver extends BroadcastReceiver {
    public static int gpsStatus = 1 ;
    @Override
    public void onReceive(Context context, Intent intent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);


        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            EventBus.getDefault().postSticky(new EventBusMessage<String>("GPS_PROVIDER", "1"));
        }
        else
        {
            EventBus.getDefault().postSticky(new EventBusMessage<String>("GPS_PROVIDER", "0"));

        }
    }
}
