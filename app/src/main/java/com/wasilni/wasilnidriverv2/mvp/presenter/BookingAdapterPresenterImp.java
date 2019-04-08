package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.AdapterContract;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

public class BookingAdapterPresenterImp implements AdapterContract.AdapterPresenter<Booking, BookingAdapter.BookingItemViewHolder> {

    BookingAdapter mAdapter;

    public BookingAdapterPresenterImp(BookingAdapter bookingAdapter) {
        this.mAdapter = bookingAdapter;
    }

    @Override
    public void ObjectToHolder(final Booking object, BookingAdapter.BookingItemViewHolder holder, Activity activity) {
//        if(object.getStatus().equals("DONE")){
//
//            holder.ChangeButton.setVisibility(View.GONE);
//            return;
//        }
        holder.timeTextView.setText(object.getDatetime());
//        holder.dateTextView.setText(object.getDates().get(0));
        //holder.DetailsTextView.setText();
        Log.e("state", "ObjectToHolder: " + object.getStatus() + " " +RideStatus.ARRIVED.toString() );
        if(!object.getStatus().equals("ARRIVED")) {
            holder.seatCountTextView.setText(""+object.getSeats());
        }


        holder.ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("change1", "onClick: "+object.getStatus() );

                object.setStatus(RideStatus.nextState(object.getStatus()));
                if(object.getStatus().equals("DONE")){
                    List<Booking> list = mAdapter.getList();
                    list.remove(object);
                    mAdapter.setList(list);
                }
                // refreshAdapter and recycler view
                Log.e("change2", "onClick: "+object.getStatus() );
                mAdapter.notifyDataSetChanged();
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
