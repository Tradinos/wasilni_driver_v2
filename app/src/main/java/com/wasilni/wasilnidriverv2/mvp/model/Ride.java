package com.wasilni.wasilnidriverv2.mvp.model;


import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

public class Ride  {
    String status ;
    String start_datetime ;
    String passenger_name;
    String pick_up_location_name;
    int bookings_count , id;
    List<Booking> bookings ;
    public Ride(String status ) {
        this.status = status ;
    }

    public Ride(int id) {
        this.id = id;
    }

    public Ride(String passengaer_name, String pick_up_location_name, int bookings_count) {
        this.passenger_name = passengaer_name;
        this.pick_up_location_name = pick_up_location_name;
        this.bookings_count = bookings_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return passenger_name;
    }

    public void setPassengaer_name(String passengaer_name) {
        this.passenger_name = passengaer_name;
    }

    public int getBookings_count() {
        return bookings_count;
    }

    public void setBookings_count(int bookings_count) {
        this.bookings_count = bookings_count;
    }


    public String getPick_up_location_name() {
        return pick_up_location_name;
    }

    public void setPick_up_location_name(String pick_up_location_name) {
        this.pick_up_location_name = pick_up_location_name;
    }

    @Override
    public String toString() {
        return ""+id+" "+bookings_count+" "+passenger_name ;
    }
}
