package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.view.View;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.ui.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

public class BookingAdapterPresenterImp implements AdapterContract.AdapterPresenter<Booking, BookingAdapter.BookingItemViewHolder> {

    BookingAdapter mAdapter;
    ChangeRideContract.ChangeBookingPresenter presenter ;
    TripPassengersActionsFragment tripPassengersActionsFragment;

    public BookingAdapterPresenterImp(TripPassengersActionsFragment tripPassengersActionsFragment) {
        this.tripPassengersActionsFragment = tripPassengersActionsFragment ;
        presenter = new ChangeBookingStatePresenterImp(tripPassengersActionsFragment);
    }


    @Override
    public void ObjectToHolder(final Booking object,int position , BookingAdapter.BookingItemViewHolder holder) {


        if(holder.timeTextView != null) {
            holder.timeTextView.setText(object.getDatetime());
        }
        if(holder.dateTextView != null) {
            holder.dateTextView.setText(object.getDate());
        }
        if(holder.timeTextView != null) {
            holder.timeTextView.setText(object.getDatetime());
        }
        if(holder.passengerNameTextView != null) {
            holder.passengerNameTextView.setText(object.getName());
        }
        if(holder.DetailsTextView != null){
            holder.DetailsTextView.setText(object.getPickUpLocationDetails());
        }
        if(holder.startPlaceTextView != null){
            holder.startPlaceTextView.setText(object.getPickUpName());
        }
        if(holder.endPlaceTextView != null){
            holder.endPlaceTextView.setText(object.getPullDownName());
        }
        if(holder.seatCountTextView != null ) {
            holder.seatCountTextView.setText(""+object.getSeats());
        }

        holder.ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendToServer(object);
            }
        });
        if(holder.callImageView != null ) {
            holder.callImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilFunction.dialContactPhone(tripPassengersActionsFragment.activity,object.getPhoneNumber());
                }
            });
            holder.whatsappImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilFunction.massegingWasilniWhatsAPP(tripPassengersActionsFragment.activity , object.getWhatsapp());
                }
            });
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
