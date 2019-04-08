package com.wasilni.wasilnidriverv2.mvp.model;


import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

public class Ride  {
    String status ;
    String start_datetime ;
    String passengaer_name;
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

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getPassengaer_name() {
        return passengaer_name;
    }

    public void setPassengaer_name(String passengaer_name) {
        this.passengaer_name = passengaer_name;
    }
}
