package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.network.ServerPresenter;

public interface HomeContract {
    public interface HomeView{
        public void initView();
        public void driverStatusOnclick();
        public void notificationButotnOnclick();
        public void regesterNotification();
        public void regesterRecivers();
        public void unregesterNotification();
        public void unregesterRecivers();
        public void checkDriverStatus();

    }
    public interface HomeActivityPresenter {

    }
}
