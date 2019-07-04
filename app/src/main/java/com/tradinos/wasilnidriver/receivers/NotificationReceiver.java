package com.tradinos.wasilnidriver.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tradinos.wasilnidriver.mvp.presenter.GetMyRidesPresenterImp;
import com.tradinos.wasilnidriver.util.UserUtil;
import com.tradinos.wasilnidriver.util.UtilFunction;

import static com.tradinos.wasilnidriver.ui.Activities.HomeActivity.homeActivity;
import static com.tradinos.wasilnidriver.util.UtilFunction.p;


public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final int code = getResultCode();
        Bundle extras = intent.getExtras();
        String event = extras.getString("event");
        if(event == null){
            return;
        }
        if (code == Activity.RESULT_OK) {
            switch (event) {
                case "new_ride": {
                    UtilFunction.showToast(context , "تم قبول طلب جديد");
                    Log.e("onReceive: ","1111" );
                    if(UserUtil.getUserInstance().getChecked()) {
                        Log.e("onReceive: ","2222" );
                        GetMyRidesPresenterImp presenter = new GetMyRidesPresenterImp(homeActivity);
                        presenter.sendToServer(null);
                    }

                    break;
                }
                case "update_ride" :{

                }
            }
        }
    }
}
