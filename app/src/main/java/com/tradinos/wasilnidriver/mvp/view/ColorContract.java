package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Color;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface ColorContract {
    public interface ColorsPresenter extends ServerPresenter<Color, PaginationAPI<Color>>{

    }
}
