package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface RegistrationContract {
    public interface RegistrationPresenter extends ServerPresenter<User,User> {

    }

}
