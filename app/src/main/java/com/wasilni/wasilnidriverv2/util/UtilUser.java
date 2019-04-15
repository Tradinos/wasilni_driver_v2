package com.wasilni.wasilnidriverv2.util;

import com.wasilni.wasilnidriverv2.mvp.model.User;

public class UtilUser {
    private static User user ;

    public static User getUserInstance(){
        if(user == null){
            user = new User();
            user.setLogingIn(true);
        }
        return user ;
    }
    public static void UpdateUserInstance(){

    }

    public static void setUser(User user) {
        UtilUser.user = user;
    }
}
