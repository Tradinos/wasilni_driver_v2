package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

public interface ChangeRideContract {
    public interface ChangeRidePresenter extends ServerPresenter<Response<Ride> > {

    }
}
