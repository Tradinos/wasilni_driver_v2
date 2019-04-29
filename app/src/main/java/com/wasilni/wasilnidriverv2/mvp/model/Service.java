package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service{
    @SerializedName("name")
    @Expose
    private String name ;
    @SerializedName("cost")
    @Expose
    private String cost ;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("imageID")
    @Expose
    private Integer imageID ;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("max_bookings")
    @Expose
    private Integer max_bookings;
    @SerializedName("priority")
    @Expose
    private Integer priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMax_bookings() {
        return max_bookings;
    }

    public void setMax_bookings(Integer max_bookings) {
        this.max_bookings = max_bookings;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
