package com.wasilni.wasilnidriverv2.util;

public enum RideStatus {
    // this enum descripe what i should todo now


    APPROVED("ACCEPTED") ,
    ACCEPTED("ACCEPTED") ,
    STARTED("STARTED") ,
    ARRIVED ("ARRIVED") ,
    PICKED_UP("PICKED_UP"),
    DONE("DONE") ,
    ;
    String state ;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    RideStatus(String state) {
        this.state = state;
    }
    public static String nextState(String state){
        switch (state){
            case "APPROVED" :
                return "ACCEPTED" ;
            case "ACCEPTED" :
                return "STARTED" ;
            case "STARTED" :
                return "ARRIVED";
            case "ARRIVED" :
                return  "PICKED_UP";
            case "PICKED_UP" :
                return  "DONE";
            case "DONE" :
                return  "PAID";
        }
        return  null;
    }
}
