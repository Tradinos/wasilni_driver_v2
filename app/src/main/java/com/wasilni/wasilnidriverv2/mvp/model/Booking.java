package com.wasilni.wasilnidriverv2.mvp.model;


import android.app.Activity;
import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class Booking {

    @SerializedName("dates")
    @Expose
    List<String> dates ;
    @SerializedName("passenger_whatsapp_number")
    @Expose
    String whatsapp ;
    @SerializedName("passenger_username")
    @Expose
    String  phoneNumber ;
    @SerializedName("date")
    @Expose
    String date ;
    @SerializedName("is_pooling")
    @Expose
    int is_pooling ;
    @SerializedName("price")
    @Expose
    Integer price ;
    @SerializedName("to_pay")
    @Expose
    Integer to_pay ;
    @SerializedName("meter")
    @Expose
    BookingSummary summary ;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("passenger_name")
    @Expose
    private String name;
    @SerializedName("datetime")
    @Expose
    public String datetime;
    @SerializedName("pick_up_location")
    @Expose
    private GeoJson pickUpLocation;
    @SerializedName("pull_down_location")
    @Expose
    private GeoJson pullDownLocation;
    @SerializedName("pick_up_location_name")
    @Expose
    private String  pickUpName;
    @SerializedName("pick_up_location_details")
    @Expose
    private String pickUpLocationDetails;
    @SerializedName("pull_down_location_details")
    @Expose
    private String pullDownLocationDetails;
    @SerializedName("pull_down_location_name")
    @Expose
    private String pullDownName;
    @SerializedName("paid")
    @Expose
    private String paid;
    @SerializedName("status")
    @Expose
    private String status;
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
    @SerializedName("number")
    @Expose
    private Integer number ;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Booking(String dates, String datetime) {
        this.dates = new ArrayList<>();
        this.dates.add(dates);
        this.datetime = datetime;
    }
    public Booking() {

    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public int getIs_pooling() {
        return is_pooling;
    }

    public void setIs_pooling(int is_pooling) {
        this.is_pooling = is_pooling;
    }

    public Booking(String status) {
        this.status = status;
    }

    public Booking(String status, int id) {
        this.status = status ;
        this.id = id ;
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

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTo_pay() {
        return to_pay;
    }

    public void setTo_pay(Integer to_pay) {
        this.to_pay = to_pay;
    }

    public BookingSummary getSummary() {
        return summary;
    }

    public void setSummary(BookingSummary summary) {
        this.summary = summary;
    }

    public String getPickUpName() {
        return pickUpName;
    }

    public void setPickUpName(String pickUpName) {
        this.pickUpName = pickUpName;
    }

    public String getPullDownName() {
        return pullDownName;
    }

    public void setPullDownName(String pullDownName) {
        this.pullDownName = pullDownName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPickUpLocationDetails() {
        return pickUpLocationDetails;
    }

    public void setPickUpLocationDetails(String pickUpLocationDetails) {
        this.pickUpLocationDetails = pickUpLocationDetails;
    }

    public String getPullDownLocationDetails() {
        return pullDownLocationDetails;
    }

    public void setPullDownLocationDetails(String pullDownLocationDetails) {
        this.pullDownLocationDetails = pullDownLocationDetails;
    }

    @Override
    public String toString() {
        return ""+id +" "+ passengerId + " " + name;
    }
}
