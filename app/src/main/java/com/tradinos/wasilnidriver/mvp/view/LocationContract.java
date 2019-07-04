package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Location;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface LocationContract {
    public interface LocationsPresenter extends ServerPresenter<Location, PaginationAPI<Location>>{

    }
}
