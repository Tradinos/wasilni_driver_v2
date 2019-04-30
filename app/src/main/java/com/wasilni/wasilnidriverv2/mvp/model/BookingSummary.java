package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingSummary {
    @SerializedName("km_count")
    @Expose
    int km_count ;
    @SerializedName("elapsed_time")
    @Expose
    int booking_time ;
    @SerializedName("waiting_time")
    @Expose
    int waiting_time_count ;

    public int getKm_count() {
        return km_count;
    }

    public void setKm_count(int km_count) {
        this.km_count = km_count;
    }

    public int getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(int booking_time) {
        this.booking_time = booking_time;
    }

    public int getWaiting_time_count() {
        return waiting_time_count;
    }

    public void setWaiting_time_count(int waiting_time_count) {
        this.waiting_time_count = waiting_time_count;
    }
}
