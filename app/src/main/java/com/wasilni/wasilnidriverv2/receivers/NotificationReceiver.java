package com.wasilni.wasilnidriverv2.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wasilni.wasilnidriverv2.mvp.presenter.GetMyRidesPresenterImp;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.ui.Activities.NotificationActivity;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import static com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity.homeActivity;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.p;


public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final int code = getResultCode();
        Bundle extras = intent.getExtras();
        String event = extras.getString("event");
        p(event);
        if (code == Activity.RESULT_OK) {
            switch (event) {
                case "new_order": {
                    UtilFunction.showToast(context , "new order approvid");
                    GetMyRidesPresenterImp presenter = new GetMyRidesPresenterImp(homeActivity);
                    presenter.sendToServer(null);

                    break;
                }
                case "update_ride" :{

                }
            }
        }
    }
}
