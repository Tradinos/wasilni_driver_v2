package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.network.Response;

import retrofit2.Call;

public class ChangeRideStatePresenterImp implements ChangeRideContract.ChangeRidePresenter {
    @Override
    public void sendToServer(Response<Ride> request) {

    }

    @Override
    public void onResponse(Call<Response<Response<Ride>>> call, retrofit2.Response<Response<Response<Ride>>> response) {

    }

    @Override
    public void onFailure(Call<Response<Response<Ride>>> call, Throwable t) {

    }
}
