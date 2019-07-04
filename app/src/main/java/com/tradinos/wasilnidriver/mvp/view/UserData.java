package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface UserData {
    public interface GetUserData extends ServerPresenter<Object , User> {

    }
}
