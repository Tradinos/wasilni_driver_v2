package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.databinding.DataBindingUtil;

import com.github.florent37.viewanimator.ViewAnimator;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.databinding.ActivityHomeBinding;
import com.wasilni.wasilnidriverv2.databinding.ContentToolbarBinding;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.UtilUser;

public class HomeActivityPresenterImp implements HomeContract.HomeActivityPresenter {
    HomeActivity activity ;
    public HomeActivityPresenterImp(HomeActivity activity ) {
        this.activity = activity;



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
}
