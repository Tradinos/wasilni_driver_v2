package com.wasilni.wasilnidriverv2.socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.UtilUser;

import java.net.Socket;

public class TrackingService extends Service {
    private String CHANEEL_ID = "772233";

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        String channelName = "wasilni Background Service";
        @SuppressLint("WrongConstant") NotificationChannel chan = new NotificationChannel(CHANEEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(chan);
        return CHANEEL_ID;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForegroundNew() {
        String channelId = createNotificationChannel();
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true).setContentText("وصلني يعمل الان...").setContentTitle("وصلني")
                .setSmallIcon(R.drawable.car_seat).setCategory(Notification.CATEGORY_SERVICE).build();
        startForeground(101, notification);
    }

    public TrackingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("tracking service", "onCreate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundNew();
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(UtilUser.getUserInstance().isChecked()) {
                    Log.e("tracking service", "run: start" );
                    startTracking();

                }
            }
        }, 1000);
    }

    private void startTracking() {
        SocketSingelton.connect();
        SocketSingelton.startTracking(getApplicationContext(),null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("tracking service", "onCreate");

        if(!SocketSingelton.isTracking){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(UtilUser.getUserInstance().isChecked()) {
                        Log.e("tracking service", "run: start" );
                        startTracking();

                    }
                }
            }, 1000);
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
