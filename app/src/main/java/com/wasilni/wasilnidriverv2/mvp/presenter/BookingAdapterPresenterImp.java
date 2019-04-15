package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.view.View;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.ui.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;

public class BookingAdapterPresenterImp implements AdapterContract.AdapterPresenter<Booking, BookingAdapter.BookingItemViewHolder> {

    BookingAdapter mAdapter;
    ChangeRideContract.ChangeBookingPresenter presenter ;
    TripPassengersActionsFragment tripPassengersActionsFragment;

    public BookingAdapterPresenterImp(TripPassengersActionsFragment tripPassengersActionsFragment) {
        this.tripPassengersActionsFragment = tripPassengersActionsFragment ;
        presenter = new ChangeBookingStatePresenterImp(tripPassengersActionsFragment);
    }


    @Override
    public void ObjectToHolder(final Booking object, BookingAdapter.BookingItemViewHolder holder) {


        if(holder.timeTextView != null) {
            holder.timeTextView.setText(object.getDatetime());
        }
        if(holder.seatCountTextView != null ) {
            holder.seatCountTextView.setText(""+object.getSeats());
        }


        holder.ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(object.getStatus().equals("DONE")){
                        // show ride summary

                    RideSummaryFragment rideSummaryFragment = RideSummaryFragment.newInstance(tripPassengersActionsFragment.activity);
                    rideSummaryFragment.responseCode200(object,tripPassengersActionsFragment);
                }
                else{
                    presenter.sendToServer(object);
                }
            }
        });
        if(holder.callImageView != null ) {
            holder.callImageView.setOnClickListener(this);
            holder.whatsappImageView.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.whatsapp_passenger :{
                break;
            }
            case R.id.call_passenger : {
                break;
            }
        }
    }
}
