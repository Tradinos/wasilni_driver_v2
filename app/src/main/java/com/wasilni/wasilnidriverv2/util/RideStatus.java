package com.wasilni.wasilnidriverv2.util;

public enum RideStatus {
    // this enum descripe what i should todo now


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
    public void nextState(){
        switch (state){
            case "STARTED" :
                state = "ARRIVED";
                break;
            case "ARRIVED" :
                state = "PICKED_UP";
                break;
            case "PICKED_UP" :
                state = "DONE";
                break;
        }
    }
}
