package com.wasilni.wasilnidriverv2.socket;

import io.realm.RealmObject;

/**
 * Created by Mahmoud Mattar on 11/29/2018.
 */

public class SocketItem extends RealmObject {
    double lat, lng;
    String date;

    public SocketItem(){

    }
    public SocketItem(double lat, double lng, String date) {
        this.lat = lat;
        this.lng = lng;
        this.date = date;
    }
    public void setAttr(double lat , double lng , String date){
        this.lat = lat ;
        this.lng = lng ;
        this.date = date ;
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
