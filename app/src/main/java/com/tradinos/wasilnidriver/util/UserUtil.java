package com.tradinos.wasilnidriver.util;

import com.tradinos.wasilnidriver.mvp.model.User;

public class UserUtil {
    private static User user ;

    public static User getUserInstance(){
        if(user == null){
            user = new User();
            //user.setLogingIn(true);
        }
        return user ;
    }
    public static void UpdateUserInstance(){

    }

    public static void setUser(User user) {
        UserUtil.user = user;
    }
}
