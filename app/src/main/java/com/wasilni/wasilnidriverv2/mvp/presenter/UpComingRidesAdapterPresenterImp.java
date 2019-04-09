package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.adapters.UpcomingRidesAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.ArrayList;
import java.util.List;

public class UpComingRidesAdapterPresenterImp implements AdapterContract.AdapterPresenter<Ride, UpcomingRidesAdapter.RideViewHolder> {
    private TripPassengersActionsFragment fragment;
    RideContruct.RideBookingsPresenter presenter ;

    public UpComingRidesAdapterPresenterImp(TripPassengersActionsFragment fragment) {
        this.fragment = fragment;
        presenter = new RideBookingsPresenterImp(fragment);
    }

    @Override
    public void ObjectToHolder(final Ride object, UpcomingRidesAdapter.RideViewHolder holder, Activity activity) {
        holder.allItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change bottom sheet adapter data ;
                presenter.sendToServer(object);

            }
        });
        holder.duration.setText(object.getStart_datetime());
        holder.startPlace.setText(object.getPick_up_location_name());
        if(object.getBookings_count() == 1) {
            holder.passengerName.setText(object.getPassengaer_name());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item :{
                // change bottom sheet adapter data ;

                break;
            }
        }
    }
}
