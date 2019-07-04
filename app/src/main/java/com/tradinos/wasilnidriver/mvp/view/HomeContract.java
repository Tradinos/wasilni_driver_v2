package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.Ride;
import com.tradinos.wasilnidriver.mvp.model.User;

import java.util.List;

public interface HomeContract {
    public interface HomeView{
        public void addDriverLocationToMap();
        public void getTokenFromLocalData();
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
