package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingSummary {
    @SerializedName("km_count")
    @Expose
    String km_count ;
    @SerializedName("booking_time")
    @Expose
    String booking_time ;
    @SerializedName("waiting_time_count")
    @Expose
    String waiting_time_count ;

    public String getKm_count() {
        return km_count;
    }

    public void setKm_count(String km_count) {
        this.km_count = km_count;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getWaiting_time_count() {
        return waiting_time_count;
    }

    public void setWaiting_time_count(String waiting_time_count) {
        this.waiting_time_count = waiting_time_count;
    }
}
