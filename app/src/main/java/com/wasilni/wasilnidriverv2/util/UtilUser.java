package com.wasilni.wasilnidriverv2.util;

import com.wasilni.wasilnipassengerv2.mvp.model.User;

public class UtilUser {
    private static User user ;
    public static RideStatus rideStatus = RideStatus.GET_START_LOCATION;

    public static User getUserInstance(){
        if(user == null){
            user = new User();
        }
        return user ;
    }
    public static void UpdateUserInstance(){

    }
}
