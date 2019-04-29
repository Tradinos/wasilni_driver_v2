package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Car {
    @SerializedName("color_id")
    @Expose
    private Integer color_id ;
    @SerializedName("brand_id")
    @Expose
    private Integer brand_id ;
    @SerializedName("brand_model_id")
    @Expose
    private Integer brand_model_id;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("insurance_expired_date")
    @Expose
    private String insurance_expired_date;
    @SerializedName("car_manufacture_year")
    @Expose
    private String car_manufacture_year;

    public Integer getColor_id() {
        return color_id;
    }

    public void setColor_id(Integer color_id) {
        this.color_id = color_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getBrand_model_id() {
        return brand_model_id;
    }

    public void setBrand_model_id(Integer brand_model_id) {
        this.brand_model_id = brand_model_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInsurance_expired_date() {
        return insurance_expired_date;
    }

    public void setInsurance_expired_date(String insurance_expired_date) {
        this.insurance_expired_date = insurance_expired_date;
    }

    public String getCar_manufacture_year() {
        return car_manufacture_year;
    }

    public void setCar_manufacture_year(String car_manufacture_year) {
        this.car_manufacture_year = car_manufacture_year;
    }
}
