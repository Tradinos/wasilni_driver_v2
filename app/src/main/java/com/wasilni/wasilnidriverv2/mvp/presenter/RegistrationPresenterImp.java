package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.RegistrationContract;
import com.wasilni.wasilnidriverv2.network.Response;

import retrofit2.Call;

public class RegistrationPresenterImp implements RegistrationContract.RegistrationPresenter {
    @Override
    public void sendToServer(User request) {

    }

    @Override
    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {

    }

    @Override
    public void onFailure(Call<Response<User>> call, Throwable t) {

    }
}
