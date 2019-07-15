package com.tradinos.wasilnidriver.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Report {
    @SerializedName("type")
    @Expose
    String type ;
    @SerializedName("list")
    @Expose
    List<ReportChild> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ReportChild> getList() {
        return list;
    }

    public void setList(List<ReportChild> list) {
        this.list = list;
    }
}
