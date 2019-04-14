package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;

public interface RideSummaryContract {
    public interface RideSummaryView{
        public void initView();
        public void onFailure(Throwable t);

        void responseCode200(Booking response , TripPassengersActionsFragment tripPassengersActionsFragment);
    }
}
