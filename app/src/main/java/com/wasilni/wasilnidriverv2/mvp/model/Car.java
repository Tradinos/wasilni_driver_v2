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


}
