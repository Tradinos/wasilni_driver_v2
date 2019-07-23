package com.tradinos.wasilnidriver.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class UploadReport {
    @SerializedName("answers")
    @Expose
    Map<String,Object> answers ;
    @SerializedName("ticket_type_id")
    @Expose
    Integer ticketTypeId ;
    @SerializedName("last_question_id")
    @Expose
    Integer lastQuestionId ;
    @SerializedName("booking_id")
    @Expose
    Integer bookingId ;
    @SerializedName("ride_id")
    @Expose
    Integer rideId ;

    public void setAnswers(Map<String, Object> answers) {
        this.answers = answers;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Map<String, Object> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Object> answers) {
        this.answers = answers;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Integer getLastQuestionId() {
        return lastQuestionId;
    }

    public void setLastQuestionId(Integer lastQuestionId) {
        this.lastQuestionId = lastQuestionId;
    }
    public class Tecket{
        Integer intval ;

    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
