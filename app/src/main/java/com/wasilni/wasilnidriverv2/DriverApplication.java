package com.wasilni.wasilnidriverv2;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import com.wasilni.wasilnidriverv2.socket.SocketSingelton;
import com.wasilni.wasilnidriverv2.socket.TrackingService;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import static com.wasilni.wasilnidriverv2.gps.GPSLocation.locationManager;

public class DriverApplication extends android.app.Application {
    public DriverApplication() {

    }
    private String TAG = "DriverApplication";

    public   Context mContext = this ;
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault() ;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startServiceTracking();
    }

    private void startServiceTracking(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.e("startServiceTracking ","123" +UserUtil.getUserInstance().isChecked() +" "+ !SocketSingelton.isTracking);
                if(UserUtil.getUserInstance().isChecked() && !SocketSingelton.isTracking){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(new Intent(mContext, TrackingService.class));
                    }
                    else{
                        startService(new Intent(mContext, TrackingService.class));

                    }
                }

            }
        }, 0, 10000);
    }


}
