package com.wasilni.wasilnidriverv2.socket;

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
import io.realm.RealmQuery;
import io.socket.client.IO;
import io.socket.client.Socket;

import static com.wasilni.wasilnidriverv2.util.Constants.ETAG;

public class SocketSingelton {
    private static final String SOCKET_URL = "http://192.168.9.148:3000/passengers";
    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjgwZGFkNTE4Zjk3ZjYxMmIyMmY1MzgzM2ZlM2ZiMTQ5ZDdlOTcyOWI2ZjI3ZTgwZGJhODMxZTA4YjY5ZTNiZjM5ZWMyOGZlOWU5YjA5NWZlIn0.eyJhdWQiOiIxIiwianRpIjoiODBkYWQ1MThmOTdmNjEyYjIyZjUzODMzZmUzZmIxNDlkN2U5NzI5YjZmMjdlODBkYmE4MzFlMDhiNjllM2JmMzllYzI4ZmU5ZTliMDk1ZmUiLCJpYXQiOjE1NTI5MTA2OTMsIm5iZiI6MTU1MjkxMDY5MywiZXhwIjoxNTg0NTMzMDkzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.HsG0UD6rg4bbchf5jbp5J93_DAAYRO8Kgi1qKPKaU0oHHL35Pq9spWBnifb_tU6saVZfGDkxAjtr25TrwgLVhVdyl_FXONsF5r-bm4omxXHtC4ddzXFoT7RdFN8_vv6m54tjZLD-kykcyDvJFBHblrRmIUaSkd-AIpKPZkX-IjnGs9zVWHNOGa46BBJ6pEwWuDiwIZ36Z2rXFUP8RotWmpvynhV81JhcGe0u6MYr9Kqmn-RW0y3oxGV-ASMPcd1UnAEcSFm7GbkSDutAyZtsBo5Zs7OU5oCPaTKPlIGYSS9rJQmkv_Aobd94ouAtmz57DMxaqtMcNFaUCZ_fVwTJ-JCvG4ppmAF4Zm3191yABJCwmcS0BrO-kIxuMdkCy9s4udwjvX_C8EHqqT5_FBNyMxi1TjlXTh1clC-r2wSHf6qEaA6j0MNHC2u1tHY30xRRbhMo8fm9IvyJ6ptFu88e6yiEjU1PHstkAGFB_2DS3Jb5zqB54b3nXSbQRn8lMUP_bx1XvFOAkJZiLC6Ul0knlC7MxrDZI1Dw_5qkaBkPajFseDc6dtdh8hg-P09cjptX7PGF4bgG7yf4xiywOpkARurPKZffO-dlEaLnfiNSh-EFsE4xPEDLXPiQ1HmvFMIdbhF4F9NOriux1yAs0pSupa-Pe9hj1MY1kbWl_IjIbwQ";
    private static Socket socket = null;
    private static IO.Options mOptions;
    private static Realm realm = Realm.getDefaultInstance();
    private static RealmQuery<SocketItem> query = realm.where(SocketItem.class);

    private static void createInstance() {
        try {
            mOptions = new IO.Options();
           // mOptions.query = "accessToken=" + TOKEN;
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
        socket.close();
        socket.disconnect();

    }

    public static void startTracking(final Location location) {
        socket = getInstance();
        Timer timer = new Timer();

        GPSLocation.startUpdateLocaiton();
        final Location myLocation = GPSLocation.getMyLocation();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reConnect();

                if (myLocation != null) {
                    location.set(myLocation) ;
                    addLocationToRealm(myLocation);
                }
                if (socket.connected()) {
                    sendAllDB();
                    clearRealM();
                }
            }
        }, 0, 10000);

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

            socket.emit("update_driver_location", obj);
        }
    }
}
