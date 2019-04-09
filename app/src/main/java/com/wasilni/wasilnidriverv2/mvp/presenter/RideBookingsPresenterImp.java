package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.network.Response;

import java.util.List;

import retrofit2.Call;

public class RideBookingsPresenterImp implements RideContruct.RideBookingsPresenter {

    @Override
    public void sendToServer(PaginationAPI<List<Booking>> request) {

    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<List<Booking>>>> call, retrofit2.Response<Response<PaginationAPI<List<Booking>>>> response) {

    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<List<Booking>>>> call, Throwable t) {

    }
}
