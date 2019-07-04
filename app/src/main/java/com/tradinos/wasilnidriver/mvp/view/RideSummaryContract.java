package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.Booking;
import com.tradinos.wasilnidriver.ui.Dialogs.TripPassengersActionsFragment;

public interface RideSummaryContract {
    public interface RideSummaryView{
        public void initView();
        public void onFailure(Throwable t);

        void responseCode200(Booking response , TripPassengersActionsFragment tripPassengersActionsFragment);
    }
}
