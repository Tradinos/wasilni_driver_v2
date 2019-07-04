package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Nationality;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface NationalityContract {
    public interface NationalitiesPresenter extends ServerPresenter<Nationality, PaginationAPI<Nationality>>{

    }
}
