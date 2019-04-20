package com.wasilni.wasilnidriverv2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.wasilni.wasilnidriverv2.gps.GPSLocation;
import com.wasilni.wasilnidriverv2.socket.SocketSingelton;
import com.wasilni.wasilnidriverv2.socket.TrackingService;
import com.wasilni.wasilnidriverv2.util.UtilUser;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
                Log.e(TAG, "run startServiceTracking " );
                if(UtilUser.getUserInstance().isLogingIn() && !SocketSingelton.isTracking){
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
