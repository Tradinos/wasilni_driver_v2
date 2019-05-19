package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.graphics.Color;
import android.view.View;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.ui.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

            try {
                String time = object.getDatetime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(time);
                holder.dateTextView.setText((date.getYear()+1900) +"-"+(date.getMonth()+1)+"-"+date.getDate() );
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if(holder.timeTextView != null) {
            try {
                String time = object.getDatetime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(time);
                time = "";
                if(date.getHours() < 10){
                    time += "0"+date.getHours() ;
                }else{
                    time += date.getHours();
                }
                time +=":";
                if(date.getMinutes() < 10){
                    time += "0"+date.getMinutes() ;
                }else{
                    time += date.getMinutes();
                }
                holder.timeTextView.setText(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(holder.passengerNameTextView != null) {
            holder.passengerNameTextView.setText(object.getName());
        }
        if(holder.DetailsTextView != null){
            if(object.getStatus().equals("PICKED_UP") ||object.getStatus().equals("DONE") )
            {
                holder.DetailsTextView.setText(object.getPullDownLocationDetails());
            }else {
                holder.DetailsTextView.setText(object.getPickUpLocationDetails());
            }
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
        if(object.getNumber() == 0) {
            holder.ChangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.sendToServer(object);
                }
            });
        }else{
            holder.ChangeButton.setBackgroundColor(Color.GRAY);
        }
        if(holder.callImageView != null ) {
            holder.callImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilFunction.dialContactPhone(tripPassengersActionsFragment.activity,"0"+object.getPhoneNumber());
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
