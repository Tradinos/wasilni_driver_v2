package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface LoginContract {
    public interface LoginPresenter extends ServerPresenter<User,User> {

    }
}
