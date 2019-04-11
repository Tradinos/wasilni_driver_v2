package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.BrandModel;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface BrandModelContract {
    public interface BrandModelsPresenter extends ServerPresenter<Brand, List<BrandModel>>{

    }
}
