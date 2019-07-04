package com.tradinos.wasilnidriver;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.tradinos.wasilnidriver.socket.SocketSingelton;
import com.tradinos.wasilnidriver.socket.TrackingService;
import com.tradinos.wasilnidriver.util.UserUtil;

import io.fabric.sdk.android.Fabric;
import org.greenrobot.eventbus.EventBus;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.tradinos.wasilnidriver.gps.GPSLocation.locationManager;

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
        updateResources(getApplicationContext() , "ar");
        trackingService =new Intent(this, TrackingService.class) ;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startServiceTracking();
    }
    public static boolean updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return true;
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
