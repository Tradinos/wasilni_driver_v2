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

    public Cause(int id, int rating_value, String cause) {
        this.id = id;
        this.rating_value = rating_value;
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating_value() {
        return rating_value;
    }

    public void setRating_value(int rating_value) {
        this.rating_value = rating_value;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
