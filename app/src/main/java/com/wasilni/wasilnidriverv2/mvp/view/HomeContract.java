package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.network.ServerPresenter;

public interface HomeContract {
    public interface HomeView{
        public void initView();

    }
    public interface HomeActivityPresenter {
        public void driverStatusOnclick();
        public void notificationButotnOnclick();
        void regesterNotification();
        public void regesterRecivers();
         void unregesterNotification();
        public void unregesterRecivers();
    }
}
