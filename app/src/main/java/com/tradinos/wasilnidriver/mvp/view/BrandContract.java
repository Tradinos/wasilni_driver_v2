package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Brand;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface BrandContract {
    public interface BrandsPresenter extends ServerPresenter<Brand, PaginationAPI<Brand>>{

    }
}
