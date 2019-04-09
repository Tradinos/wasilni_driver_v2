package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {
    @SerializedName("booking")
    @Expose
    Booking booking ;
    @SerializedName("passenger_paid_amount")
    @Expose
    String passenger_paid_amount ;

    public Payment(Booking booking, String passenger_paid_amount) {
        this.booking = booking;
        this.passenger_paid_amount = passenger_paid_amount;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getPassenger_paid_amount() {
        return passenger_paid_amount;
    }

    public void setPassenger_paid_amount(String passenger_paid_amount) {
        this.passenger_paid_amount = passenger_paid_amount;
    }
}
