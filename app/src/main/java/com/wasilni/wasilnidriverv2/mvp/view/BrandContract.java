package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface BrandContract {
    public interface BrandsPresenter extends ServerPresenter<Brand, List<Brand>>{

    }
}
