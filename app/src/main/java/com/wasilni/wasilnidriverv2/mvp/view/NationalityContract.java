package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface NationalityContract {
    public interface NationalitiesPresenter extends ServerPresenter<Nationality, List<Nationality>>{

    }
}
