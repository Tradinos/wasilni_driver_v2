package com.wasilni.wasilnidriverv2.mvp.model;

public class Service{
    private String name , cost , icon;
    private int imageID ,id,max_bookings,priority;

    public Service(String name, String cost, String icon, int imageID, int id, int max_bookings, int priority) {
        this.name = name;
        this.cost = cost;
        this.icon = icon;
        this.imageID = imageID;
        this.id = id;
        this.max_bookings = max_bookings;
        this.priority = priority;
    }

    public Service(String name, String cost, int imageID) {
        this.name = name;
        this.cost = cost;
        this.imageID = imageID;
    }

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

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax_bookings() {
        return max_bookings;
    }

    public void setMax_bookings(int max_bookings) {
        this.max_bookings = max_bookings;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
