package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cause {
    @SerializedName("id")
    @Expose
    int id ;
    @SerializedName("rating_value")
    @Expose
    int rating_value ;
    @SerializedName("cause")
    @Expose
    String cause ;

}
