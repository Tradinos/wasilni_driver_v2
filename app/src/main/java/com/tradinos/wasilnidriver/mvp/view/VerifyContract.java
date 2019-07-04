package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface VerifyContract {
    public interface VerfiyPresenter extends ServerPresenter<User,User> {

    }
}
