package com.wasilni.wasilnidriverv2.util;

public enum RideStatus {
    // this enum descripe what i should todo now


    GET_START_LOCATION(0) ,
    GET_END_LOCATION (1) ,
    FINISH_FROM_GET_LOCATION(2),
    GET_DATA(3) ,
//    SELECT_TYPE (5),
//    SELECT_SERVICE(4),
//    SEND_REQUEST(5)
    ;
    int state ;
     RideStatus(){
        state = 0 ;
    }
    RideStatus(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public void nextStatus(){
         state++;
    }
}
