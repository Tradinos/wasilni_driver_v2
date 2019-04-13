package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand {
    @SerializedName("id")
    @Expose
    private int id ;

    @SerializedName("name")
    @Expose
    private String name ;


    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
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
}
