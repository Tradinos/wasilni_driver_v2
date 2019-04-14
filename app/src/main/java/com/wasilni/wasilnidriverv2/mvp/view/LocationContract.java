package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.model.Location;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface LocationContract {
    public interface LocationsPresenter extends ServerPresenter<Location, PaginationAPI<Location>>{

    }
}
