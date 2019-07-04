package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.Booking;
import com.tradinos.wasilnidriver.mvp.model.Ride;

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
