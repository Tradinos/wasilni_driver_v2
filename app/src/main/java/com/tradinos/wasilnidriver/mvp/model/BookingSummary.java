package com.tradinos.wasilnidriver.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingSummary {
    @SerializedName("km_count")
    @Expose
    double km_count ;
    @SerializedName("elapsed_time")
    @Expose
    double booking_time ;
    @SerializedName("waiting_time")
    @Expose
    double waiting_time_count ;

    public double getKm_count() {
        return km_count;
    }

    public void setKm_count(double km_count) {
        this.km_count = km_count;
    }

    public double getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(double booking_time) {
        this.booking_time = booking_time;
    }

    public double getWaiting_time_count() {
        return waiting_time_count;
    }

    public void setWaiting_time_count(double waiting_time_count) {
        this.waiting_time_count = waiting_time_count;
    }
}
