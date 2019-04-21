package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;

public interface BookingsFragmentContract {
    public interface BookingFragmentView{
        public void initView();
        public void initMarker(Ride ride);
        public void setBookings(Ride ride);
        public void onFailuer();
        public void updateListList(Booking mBooking);
        public void deleteBooking(Booking data);
    }
}
