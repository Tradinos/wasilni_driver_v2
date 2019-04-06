package com.wasilni.wasilnidriverv2.mvp.model;


import com.wasilni.wasilnidriverv2.util.RideStatus;

public class Ride  {
    String status ;

    public Ride(String status ) {
        this.status = status ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
