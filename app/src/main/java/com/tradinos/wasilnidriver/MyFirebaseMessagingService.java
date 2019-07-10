package com.tradinos.wasilnidriver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tradinos.wasilnidriver.receivers.NotificationReceiver;
import com.tradinos.wasilnidriver.ui.Activities.HomeActivity;

import java.util.Map;

import static com.tradinos.wasilnidriver.util.UtilFunction.p;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String ADMIN_CHANNEL_ID = "dragon-channel-id";
    private NotificationCompat.Builder notificationBuilder ;
    private NotificationManager notificationManager ;
    private NotificationChannel adminChannel;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        setupNotification(remoteMessage);

        super.onMessageReceived(remoteMessage);
    }

    @SuppressLint("WrongConstant")
    private void setupNotification(RemoteMessage remoteMessage){
        Uri defaultSoundUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + android.R.raw.short_pyscho);
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                Intent.FLAG_ACTIVITY_NEW_TASK);

        notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setSmallIcon(R.mipmap.driver_logo_text)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        try {
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), defaultSoundUri);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence adminChannelName = "dragon-channel";
            String adminChannelDescription = "notification";
            AudioAttributes att = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();


            adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW);
            adminChannel.setDescription(adminChannelDescription);
            adminChannel.enableLights(true);
            adminChannel.setLightColor(Color.RED);
            adminChannel.enableVibration(true);
            adminChannel.setSound(defaultSoundUri,att);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(adminChannel);
            }
        }
        notificationAction(remoteMessage.getData());
    }

    public void notificationAction(Map<String ,String> data){
        String  messageCode =  data.get("code");
//        Log.e( "notificationAction: ", messageCode);
        Intent intent = new Intent("booking_notification");
        intent.putExtra("event",messageCode);
        intent.setAction("com.wasilni.wasilnidriverv2.receivers");
        sendOrderedBroadcast(intent,
                null,
                new NotificationReceiver(),
                null,
                Activity.RESULT_OK,
                null,
                null);

    }

}
