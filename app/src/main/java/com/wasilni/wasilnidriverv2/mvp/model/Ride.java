package com.wasilni.wasilnidriverv2.mvp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

public class Ride  {
    @SerializedName("status")
    @Expose
    private String status ;
    @SerializedName("start_datetime")
    @Expose
    private String start_datetime ;
    @SerializedName("passenger_name")
    @Expose
    private String passenger_name;
    @SerializedName("pick_up_location_name")
    @Expose
    private String pick_up_location_name;
    @SerializedName("bookings_count")
    @Expose
    private Integer bookings_count ;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("meter_price")
    @Expose
    private Double meterPrice;
    @SerializedName("cash_received")
    @Expose
    private int cashReceived;
    @SerializedName("bookings")
    @Expose
    private List<Booking> bookings ;
    @SerializedName("number")
    @Expose
    private Integer number ;
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
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getPick_up_location_name() {
        return pick_up_location_name;
    }

    public void setPick_up_location_name(String pick_up_location_name) {
        this.pick_up_location_name = pick_up_location_name;
    }

    public Integer getBookings_count() {
        return bookings_count;
    }

    public void setBookings_count(Integer bookings_count) {
        this.bookings_count = bookings_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Double getMeterPrice() {
        return meterPrice;
    }

    public void setMeterPrice(Double meterPrice) {
        this.meterPrice = meterPrice;
    }

    public int getCashReceived() {
        return cashReceived;
    }

    public void setCashReceived(int cashReceived) {
        this.cashReceived = cashReceived;
    }

    @Override
    public String toString() {
        return ""+id+" "+bookings_count+" "+passenger_name ;
    }
}
