package com.wasilni.wasilnidriverv2.mvp.model;


import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

public class Ride  {
    String status ;
    List<Booking> bookings ;
    public Ride(String status ) {
        this.status = status ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
