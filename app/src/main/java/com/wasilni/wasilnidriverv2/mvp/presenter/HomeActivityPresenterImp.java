package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;

public class HomeActivityPresenterImp implements HomeContract.HomeActivityPresenter {
    HomeActivity activity ;
    public HomeActivityPresenterImp(HomeActivity activity ) {
        this.activity = activity ;
    }



}
