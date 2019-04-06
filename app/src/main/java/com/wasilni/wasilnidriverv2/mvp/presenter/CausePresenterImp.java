package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.model.Cause;
import com.wasilni.wasilnidriverv2.mvp.view.CauseContract;
import com.wasilni.wasilnidriverv2.network.Response;

import retrofit2.Call;

public class CausePresenterImp implements CauseContract.CausePresenter {
    @Override
    public void sendToServer(Cause request) {

    }

    @Override
    public void onResponse(Call<Response<Cause>> call, retrofit2.Response<Response<Cause>> response) {

    }

    @Override
    public void onFailure(Call<Response<Cause>> call, Throwable t) {

    }
}
