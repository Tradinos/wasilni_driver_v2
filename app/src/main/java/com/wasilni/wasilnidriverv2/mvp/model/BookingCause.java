package com.wasilni.wasilnidriverv2.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingCause {
    @SerializedName("id")
    @Expose
    int book_id ;
    @SerializedName("cause")
    @Expose
    Cause cause ;

    public BookingCause(int book_id, Cause cause) {
        this.book_id = book_id;
        this.cause = cause;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }
}
