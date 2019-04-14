package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface ColorContract {
    public interface ColorsPresenter extends ServerPresenter<Color, PaginationAPI<Color>>{

    }
}
