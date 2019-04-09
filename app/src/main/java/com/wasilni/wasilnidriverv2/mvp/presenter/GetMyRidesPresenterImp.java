package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.network.Response;

import java.util.List;

import retrofit2.Call;

public class GetMyRidesPresenterImp implements RideContruct.MyRidesPresenter {
    @Override
    public void sendToServer(List<Ride> request) {

    }

    @Override
    public void onResponse(Call<Response<List<Ride>>> call, retrofit2.Response<Response<List<Ride>>> response) {

    }

    @Override
    public void onFailure(Call<Response<List<Ride>>> call, Throwable t) {

    }
}
