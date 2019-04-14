package com.wasilni.wasilnidriverv2.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.presenter.BookingAdapterPresenterImp;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingItemViewHolder> {

    private List<Booking> list ;
    private TripPassengersActionsFragment tripPassengersActionsFragment;
    private BookingAdapterPresenterImp presenter ;
    public BookingAdapter(List<Booking> list, TripPassengersActionsFragment tripPassengersActionsFragment) {
        this.list = list;
        this.tripPassengersActionsFragment = tripPassengersActionsFragment;
        presenter = new BookingAdapterPresenterImp(tripPassengersActionsFragment);
    }

    public List<Booking> getList() {
        return list;
    }

    public void setList(List<Booking> list) {
        this.list = list;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BookingItemViewHolder holder, int position) {
        Booking booking = list.get(position) ;
        presenter.ObjectToHolder(booking , holder);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        Log.d("SAED", "getItemViewType:  I am changing postition " + position);
//        Log.e("Booking View Type",list.get(position).getStatus()) ;
        if(position == 0 && this.tripPassengersActionsFragment instanceof BookingAdapter.OnAdapterInteractionListener)
        {
            ((BookingAdapter.OnAdapterInteractionListener)this.tripPassengersActionsFragment.getActivity()).itemChanged(list.get(position).getStatus());
        }

        switch (list.get(position).getStatus()){
            case "APPROVED" :{
                return -1;
            }
            case "ACCEPTED" :{
                return 0;
            }
            case "STARTED" :{
                return 1;
            }
            case "ARRIVED" :{
                return 2 ;
            }
            case "PICKED_UP" :{
                return 3 ;
            }
            case "DONE" :{
                return 4 ;
            }
        }
        return 0;
    }


    @Override
    public BookingAdapter.BookingItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        View v = null;
        if(viewType == -1)
        {
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_piniding_booking, parent, false);
        }
        else if(viewType == 0)
        {
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_getting_passenger, parent, false);
        }
        else if(viewType == 1){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_picking_up_passenger, parent, false);
        }
        else if(viewType == 2){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_picked_up_passenger, parent, false);
        }
        else if(viewType == 3){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_drop_off_passenger, parent, false);
        }else if(viewType == 4){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_paid_passenger, parent, false);
        }

        BookingItemViewHolder vh = new BookingItemViewHolder(v);
        return vh;
    }

    public static class BookingItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView startPlaceTextView , endPlaceTextView , DetailsTextView , passengerNameTextView , dateTextView , timeTextView ,seatCountTextView ;
        public Button ChangeButton ;
        public CardView allItem ;
        public ImageView whatsappImageView , callImageView ;
        public BookingItemViewHolder(View v) {
            super(v);
            allItem = v.findViewById(R.id.item);
            startPlaceTextView = v.findViewById(R.id.start_place);
            endPlaceTextView = v.findViewById(R.id.end_place);
            DetailsTextView = v.findViewById(R.id.details);
            passengerNameTextView = v.findViewById(R.id.passenger_name);
            dateTextView = v.findViewById(R.id.date);
            timeTextView = v.findViewById(R.id.time);
            seatCountTextView = v.findViewById(R.id.seat_count);
            ChangeButton = v.findViewById(R.id.change_btn);
            whatsappImageView = v.findViewById(R.id.whatsapp_passenger);
            callImageView = v.findViewById(R.id.call_passenger);
        }
    }

    public interface OnAdapterInteractionListener {
        void itemChanged(String status);
    }
}
