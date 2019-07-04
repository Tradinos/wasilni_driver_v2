package com.tradinos.wasilnidriver.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response<T> {
    @SerializedName("message")
    @Expose
    private String message ;
    @SerializedName("data")
    @Expose
    private T data ;
    public Response(String message) {
        this.message = message;
    }
    public Response() {
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
