package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface RideContruct {
    public interface MyRidesPresenter extends ServerPresenter<List<Ride>> {

    }
}
