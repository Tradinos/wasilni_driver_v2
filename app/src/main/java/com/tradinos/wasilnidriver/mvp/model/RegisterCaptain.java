package com.tradinos.wasilnidriver.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterCaptain {
    @SerializedName("captain")
    @Expose
    private User captain;
    @SerializedName("car")
    @Expose
    private Car car;

    public User getCaptain() {
        return captain;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
