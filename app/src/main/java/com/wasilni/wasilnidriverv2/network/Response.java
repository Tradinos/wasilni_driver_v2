package com.wasilni.wasilnidriverv2.network;

public class Response<T> {
    protected String message ;
    protected T data ;
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
