package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandModel {
    @SerializedName("id")
    @Expose
    private int id ;

    @SerializedName("name")
    @Expose
    private String name ;

    @SerializedName("number_of_seats")
    @Expose
    private int number_of_seats ;

    public BrandModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandModel(int id, String name, int number_of_seats) {
        this.id = id;
        this.name = name;
        this.number_of_seats = number_of_seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }
}
