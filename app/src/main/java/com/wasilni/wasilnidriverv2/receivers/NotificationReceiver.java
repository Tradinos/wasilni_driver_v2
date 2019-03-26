package com.wasilni.wasilnidriverv2.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;



public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final int code = getResultCode();
        Bundle extras = intent.getExtras();
        int state = extras.getInt("extra");
        boolean booleanState = extras.getBoolean("booleanextra");
        if (code == Activity.RESULT_OK) {

        }
    }
}
