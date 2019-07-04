package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.Payment;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface PayContract {
    public interface PayPresenter extends ServerPresenter<Payment,Payment> {

    }
}
