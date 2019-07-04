package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.RegisterCaptain;
import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface CompleteDataContract {
    public interface CompleteDataPresenter extends ServerPresenter<RegisterCaptain,User> {

    }
}
