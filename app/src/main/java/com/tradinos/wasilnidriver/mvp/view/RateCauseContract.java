package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.BookingCause;
import com.tradinos.wasilnidriver.mvp.model.Cause;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

import java.util.List;

public interface RateCauseContract {
    public interface CausePresenter extends ServerPresenter<Cause,PaginationAPI<List<Cause>>> {

    }
    public interface RatePresenter extends ServerPresenter<BookingCause,BookingCause> {

    }
}
