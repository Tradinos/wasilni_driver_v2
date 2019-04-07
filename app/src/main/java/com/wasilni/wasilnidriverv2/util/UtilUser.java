package com.wasilni.wasilnidriverv2.util;

import com.wasilni.wasilnidriverv2.mvp.model.User;

public class UtilUser {
    private static User user ;

    public static User getUserInstance(){
        if(user == null){
            user = new User();
        }
        return user ;
    }
    public static void UpdateUserInstance(){

    }
}
