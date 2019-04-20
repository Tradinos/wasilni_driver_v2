package com.wasilni.wasilnidriverv2.socket;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;


import com.wasilni.wasilnidriverv2.gps.GPSLocation;
import com.wasilni.wasilnidriverv2.util.UtilUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.socket.client.IO;
import io.socket.client.Socket;

import static com.wasilni.wasilnidriverv2.gps.GPSLocation.myLocation;
import static com.wasilni.wasilnidriverv2.util.Constants.ETAG;
import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static java.lang.Thread.sleep;

public class SocketSingelton {
    private static final String SOCKET_URL = "http://192.168.9.175:3000/captains";
    private static Socket socket = null;
    private static IO.Options mOptions;
    private static String TAG = "socket" ;
    private static Realm realm;
    private static RealmQuery<SocketItem> query ;
    public static boolean isTracking = false;
    private static int runingThreads=0;





    private static void createInstance() {
        try {
            mOptions = new IO.Options();
            mOptions.query = "authorization=" + Token;
            socket = IO.socket(SOCKET_URL, mOptions);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Socket getInstance() {
        if (socket == null) {
            createInstance();
        }
        return socket;
    }

    public static void reConnect() {
        if (!socket.connected()) {
            disconnect();
            createInstance();
            connect();
        }
    }

    public static void connect() {
        getInstance();
        socket.connect();
    }

    public static void disconnect() {
        Log.e(TAG, "disconnect" );
        isTracking = false ;
        socket.close();
        socket.disconnect();

    }

    public static void startTracking(final Context mContext , Location location) {
        Log.e(TAG, "startTracking2" );
        socket = getInstance();


        GPSLocation.startUpdateLocaiton(mContext);
        location = myLocation[0];
        final Location finalLocation = location;

        try {
            while(true){
                isTracking = true ;
                GPSLocation.startUpdateLocaiton(mContext);

                if(realm == null){
                    // i put it here because we can access it from this thread
                    initRealm(mContext);
                }

                if(socket.connected()){
                    Log.e(TAG, "connected true" );
                }
                else{
                    if(UtilUser.getUserInstance().isChecked()) {
                        reConnect();
                    }
                    Log.e(TAG, "connected false" );
                }

                if (myLocation[0] != null) {
                    Log.e(TAG, "get location + "+ myLocation[0].getLatitude() + " " + myLocation[0].getLongitude()  );
                    if(finalLocation != null) {
                        finalLocation.set(myLocation[0]);
                    }
                    addLocationToRealm(myLocation[0]);
                }
                if (socket.connected()) {
                    sendAllDB();
                    clearRealM();
                }

                sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "startTracking: finish" );
    }

    public static void stopTracking() {
        GPSLocation.stopUpdateLocation();
    }

    private static void addLocationToRealm(Location location) {
        realm.beginTransaction();
        SocketItem socketItem = realm.createObject(SocketItem.class);
        socketItem.setAttr(location.getLatitude(), location.getLongitude()
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                        format(Calendar.getInstance().getTime()));
        realm.commitTransaction();
        UtilUser.getUserInstance().setLocation(location);
    }

    private static void clearRealM() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private static void sendAllDB() {
        List<SocketItem> socketItemList = query.findAll();
        for (SocketItem socketItem : socketItemList) {
            JSONObject obj = new JSONObject();
            SocketItem item = socketItem;

            try {
                obj.put("lat", item.getLat());
                obj.put("lng", item.getLng());
                obj.put("date", item.getDate());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(ETAG, e.getMessage());
            }

            socket.emit("update_location", obj);
        }
    }

    static private void initRealm(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("wasilni.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


        realm  = Realm.getDefaultInstance();
        query = realm.where(SocketItem.class);
    }
}
