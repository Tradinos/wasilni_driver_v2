package com.wasilni.wasilnidriverv2.mvp.model;


import android.app.Activity;
import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class Booking {

    Activity activity ;
    @SerializedName("dates")
    @Expose
    List<String> dates ;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("datetime")
    @Expose
    public String datetime;
    @SerializedName("pick_up_location")
    @Expose
    private GeoJson pickUpLocation;
    @SerializedName("pull_down_location")
    @Expose
    private GeoJson pullDownLocation;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("passenger_id")
    @Expose
    private Integer passengerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("ride_id")
    @Expose
    private Integer rideId;
    @SerializedName("voucher_id")
    @Expose
    private String voucherId;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("serivce_id")
    @Expose
    private int serivce_id ;
    @SerializedName("seats")
    @Expose
    private int seats ;

    public Booking(String dates, String datetime) {
        this.dates = new ArrayList<>();
        this.dates.add(dates);
        this.datetime = datetime;
    }
    public Booking() {

    }
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getSerivce_id() {
        return serivce_id;
    }

    public void setSerivce_id(int serivce_id) {
        this.serivce_id = serivce_id;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public GeoJson getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(GeoJson pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public GeoJson getPullDownLocation() {
        return pullDownLocation;
    }

    public void setPullDownLocation(GeoJson pullDownLocation) {
        this.pullDownLocation = pullDownLocation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }



}
