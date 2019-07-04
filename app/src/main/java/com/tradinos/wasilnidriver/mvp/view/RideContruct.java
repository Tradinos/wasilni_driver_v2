package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Ride;
import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface RideContruct {
    public interface MyRidesPresenter extends ServerPresenter<User,PaginationAPI<Ride>> {

    }
    public interface RideBookingsPresenter extends ServerPresenter<Ride,Ride>{

    }
}
