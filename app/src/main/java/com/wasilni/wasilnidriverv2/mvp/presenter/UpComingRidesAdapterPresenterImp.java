package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.view.View;

import com.wasilni.wasilnidriverv2.adapters.UpcomingRidesAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;

public class UpComingRidesAdapterPresenterImp implements AdapterContract.AdapterPresenter<Ride, UpcomingRidesAdapter.RideViewHolder> {
    @Override
    public void ObjectToHolder(Ride object, UpcomingRidesAdapter.RideViewHolder holder, Activity activity) {

    }

    @Override
    public void onClick(View v) {

    }
}
