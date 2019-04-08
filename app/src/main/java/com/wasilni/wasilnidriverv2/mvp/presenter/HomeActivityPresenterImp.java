package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.databinding.ContentToolbarBinding;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.mvp.view.OnOffDriverContract;
import com.wasilni.wasilnidriverv2.receivers.NotificationReceiver;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.UtilUser;

import static com.wasilni.wasilnidriverv2.util.UtilFunction.p;

public class HomeActivityPresenterImp implements HomeContract.HomeActivityPresenter {
    HomeActivity activity ;
    OnOffDriverContract.OnOffDriverPresenter presenter = new OnOffDriverPresenterImp(this);
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
                    .animate(activity.bottomLayout)
                    .translationY(activity.onlineOfflineLayout.getHeight() , 0)
                    .duration(1000)
                    .start();

        }
        else
        {
            UtilUser.getUserInstance().setChecked(true);
            activity.driverStatus.setImageResource(R.mipmap.power_on);
            activity.driverStatusTextView.setText("You're online");
            ViewAnimator
                    .animate(activity.bottomLayout)
                    .translationY(0 ,activity.onlineOfflineLayout.getHeight() )
                    .duration(1000)
                    .start();
        }
        presenter.sendToServer(null);
    }

    @Override
    public void notificationButotnOnclick() {
        activity.newOrderLayout.setVisibility(View.GONE);
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
