package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;

import com.github.florent37.viewanimator.ViewAnimator;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.databinding.ActivityHomeBinding;
import com.wasilni.wasilnidriverv2.databinding.ContentToolbarBinding;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.receivers.NotificationReceiver;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.UtilUser;

public class HomeActivityPresenterImp implements HomeContract.HomeActivityPresenter {
    HomeActivity activity ;

    NotificationReceiver  notificationReceiver ;
    public HomeActivityPresenterImp(final HomeActivity activity ) {
        this.activity = activity;
        notificationReceiver = new NotificationReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                activity.recreate();
            }

            ;
        };

    }


    @Override
    public void driverStatusOnclick() {
        if(UtilUser.getUserInstance().isChecked()){
            UtilUser.getUserInstance().setChecked(false);
            activity.driverStatus.setImageResource(R.mipmap.power_off);
            activity.driverStatusTextView.setText("You're offline");
            ViewAnimator
                    .animate(activity.bottomLinearLayout)
                    .translationY(1000 , 0)
                    .duration(1000)
                    .alpha(0,1).start();
        }
        else
        {
            UtilUser.getUserInstance().setChecked(true);
            activity.driverStatus.setImageResource(R.mipmap.power_on);
            activity.driverStatusTextView.setText("You're online");
            ViewAnimator
                    .animate(activity.bottomLinearLayout)
                    .translationY(0 , 1000)
                    .duration(1000)
                    .alpha(1,0).start();
        }
    }

    @Override
    public void regesterNotification() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wasilni.wasilnidriverv2.receivers");
        activity.registerReceiver(notificationReceiver , intentFilter) ;
    }

    @Override
    public void regesterRecivers() {
        regesterNotification();
    }

    @Override
    public void unregesterNotification() {
        activity.unregisterReceiver(notificationReceiver); ;

    }

    @Override
    public void unregesterRecivers() {
        unregesterNotification();
    }
}
