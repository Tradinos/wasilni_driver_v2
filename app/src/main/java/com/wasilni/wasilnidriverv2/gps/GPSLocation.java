package com.wasilni.wasilnidriverv2.gps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import java.util.List;

import static com.wasilni.wasilnidriverv2.util.Constants.LOCATION;

public class GPSLocation {
    public final static Location[] myLocation = new Location[1];
    private static LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            Log.i(LOCATION, "lat " + location.getLatitude());
            Log.i(LOCATION, "lng " + location.getLongitude());
            if (((int) location.getLatitude()) != 0 && ((int) location.getLongitude()) != 0) {
                myLocation[0] = location;
                UserUtil.getUserInstance().setLocation(location);
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

    public static void startUpdateLocaiton(final Context mContext) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("permission ", "error " );
            return;
        }
        Log.e("permission ", "allow " );
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void stopUpdateLocation(){
        locationManager.removeUpdates(locationListener);
    }
}
