package com.wasilni.wasilnidriverv2.mvp.model;

public class Car {
    private int color_id, brand_id, brand_model_id;
    private String number, insurance_expired_date, car_manufacture_year;

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
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

    public int getBrand_model_id() {
        return brand_model_id;
    }

    public void setBrand_model_id(int brand_model_id) {
        this.brand_model_id = brand_model_id;
    }
}
