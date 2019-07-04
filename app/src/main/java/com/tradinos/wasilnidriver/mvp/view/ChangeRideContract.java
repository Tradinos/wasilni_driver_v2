package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.Booking;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface ChangeRideContract {
    public interface ChangeBookingPresenter extends ServerPresenter<Booking,Booking> {

    }
}
