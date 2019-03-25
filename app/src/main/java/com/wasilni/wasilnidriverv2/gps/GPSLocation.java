package com.wasilni.wasilnidriverv2.gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import static com.wasilni.wasilnidriverv2.util.Constants.LOCATION;

public class GPSLocation {
    private final static Location[] myLocation = new Location[1];
    private static LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            Log.i(LOCATION, "lat " + location.getLatitude());
            Log.i(LOCATION, "lng " + location.getLongitude());
            if (((int) location.getLatitude()) != 0 && ((int) location.getLongitude()) != 0) {
                myLocation[0] = location;
            }
        }


        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
    public static LocationManager locationManager;

    public static Location getMyLocation() {
        return myLocation[0];
    }
    public static void startUpdateLocaiton(){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);
    }
    public static void stopUpdateLocation(){
        locationManager.removeUpdates(locationListener);
    }
}
