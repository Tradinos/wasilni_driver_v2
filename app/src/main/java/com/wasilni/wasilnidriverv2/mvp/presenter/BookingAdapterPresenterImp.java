package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

public class BookingAdapterPresenterImp implements AdapterContract.AdapterPresenter<Booking, BookingAdapter.BookingItemViewHolder> {

    BookingAdapter mAdapter;
    ChangeRideContract.ChangeBookingPresenter presenter ;
    public BookingAdapterPresenterImp(BookingAdapter bookingAdapter ,Activity activity) {
        this.mAdapter = bookingAdapter;
        presenter = new ChangeBookingStatePresenterImp(mAdapter,activity);
    }


    @Override
    public void ObjectToHolder(final Booking object, BookingAdapter.BookingItemViewHolder holder, Activity activity) {

        holder.timeTextView.setText(object.getDatetime());
//        holder.dateTextView.setText(object.getDates().get(0));
        //holder.DetailsTextView.setText();
        if(!object.getStatus().equals("ARRIVED")) {
            holder.seatCountTextView.setText(""+object.getSeats());
        }


        holder.ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.sendToServer(object);
            }
        });
        if(!object.getStatus().equals("PICKED_UP")) {
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
