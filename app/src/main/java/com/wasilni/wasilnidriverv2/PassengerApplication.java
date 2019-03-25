package com.wasilni.wasilnidriverv2;

import android.content.Context;
import android.location.LocationManager;

import org.greenrobot.eventbus.EventBus;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.wasilni.wasilnipassengerv2.gps.GPSLocation.locationManager;

public class PassengerApplication extends android.app.Application {
    public PassengerApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault() ;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        initRealm();
    }
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("wasilni.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
