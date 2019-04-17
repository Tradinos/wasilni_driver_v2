package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.view.InterviewContract;
import com.wasilni.wasilnidriverv2.network.Response;

import retrofit2.Call;

public class InterviewPresenterImp implements InterviewContract.InterviewPresenter {
    @Override
    public void sendToServer(String request) {

    }

    @Override
    public void onResponse(Call<Response<Object>> call, retrofit2.Response<Response<Object>> response) {

    }

    @Override
    public void onFailure(Call<Response<Object>> call, Throwable t) {

    }
}
