package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.mvp.model.Payment;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

public interface PayContract {
    public interface PayPresenter extends ServerPresenter<Payment> {

    }
}
