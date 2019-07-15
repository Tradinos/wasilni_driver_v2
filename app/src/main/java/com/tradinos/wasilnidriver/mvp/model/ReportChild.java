package com.tradinos.wasilnidriver.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportChild {
    @SerializedName("title")
    @Expose
    String title ;
    @SerializedName("type")
    @Expose
    String type ;
    @SerializedName("details")
    @Expose
    String details ;
    @SerializedName("id")
    @Expose
    Integer id ;
    @SerializedName("extra")
    @Expose
    List<String> extra ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getExtra() {
        return extra;
    }

    public void setExtra(List<String> extra) {
        this.extra = extra;
    }
}
