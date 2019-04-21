package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface HomeContract {
    public interface HomeView{
        public void addDriverLocationToMap();
        public void setRides(List<Ride> data);
        public void initView();
        public void driverStatusOnclick();
        public void regesterNotification();
        public void regesterRecivers();
        public void unregesterNotification();
        public void unregesterRecivers();
        public void checkDriverStatus();
        public void setDriverStatus(User user);

    }
    public interface HomeActivityPresenter {

    }
}
