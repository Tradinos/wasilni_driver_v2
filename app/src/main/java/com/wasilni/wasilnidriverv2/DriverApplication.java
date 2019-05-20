package com.wasilni.wasilnidriverv2;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.wasilni.wasilnidriverv2.socket.SocketSingelton;
import com.wasilni.wasilnidriverv2.socket.TrackingService;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import io.fabric.sdk.android.Fabric;
import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import static com.wasilni.wasilnidriverv2.gps.GPSLocation.locationManager;

public class DriverApplication extends android.app.Application {
    public DriverApplication() {

    }
    public static Intent trackingService ;
    private String TAG = "DriverApplication";

    public   Context mContext = this ;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        EventBus.getDefault() ;
        trackingService =new Intent(this, TrackingService.class) ;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startServiceTracking();
    }

    private void startServiceTracking(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Log.e("startServiceTracking ", "123" + UserUtil.getUserInstance().getChecked() + " " + !SocketSingelton.isTracking);
                    if (UserUtil.getUserInstance().getChecked() && !SocketSingelton.isTracking) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(trackingService);
                        } else {
                            startService(trackingService);

                        }
                    } else {
                        if (!UserUtil.getUserInstance().getChecked()) {
                            SocketSingelton.stopTracking();
                            stopService(trackingService);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, 0, 10000);
    }


}
