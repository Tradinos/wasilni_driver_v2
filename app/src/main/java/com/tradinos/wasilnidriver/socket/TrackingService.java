package com.tradinos.wasilnidriver.socket;

import android.annotation.SuppressLint;
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

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.gps.GPSLocation;
import com.tradinos.wasilnidriver.util.UserUtil;

import static com.tradinos.wasilnidriver.socket.SocketSingelton.isTracking;
import static java.lang.Thread.sleep;

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
                .setSmallIcon(R.mipmap.driver_logo_text).setCategory(Notification.CATEGORY_SERVICE).build();
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
    }

    private void startTracking() {
        GPSLocation.startUpdateLocaiton(getApplicationContext());
        SocketSingelton.connect();
        SocketSingelton.startTracking(getApplicationContext(), null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("tracking service", "onStartCommand");

        if (!isTracking) {
            isTracking = true;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (UserUtil.getUserInstance().getChecked() && isTracking) {
                        startTracking();
                        return;
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
