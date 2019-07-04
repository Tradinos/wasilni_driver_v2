package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Brand;
import com.tradinos.wasilnidriver.mvp.model.BrandModel;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface BrandModelContract {
    public interface BrandModelsPresenter extends ServerPresenter<Brand, PaginationAPI<BrandModel>>{

    }
}
