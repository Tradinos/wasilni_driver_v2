package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.view.OnOffDriverContract;
import com.wasilni.wasilnidriverv2.network.Response;

import retrofit2.Call;

public class OnOffDriverPresenterImp implements OnOffDriverContract.OnOffDriverPresenter {
    @Override
    public void sendToServer(Boolean request) {

    }

    @Override
    public void onResponse(Call<Response<Boolean>> call, retrofit2.Response<Response<Boolean>> response) {

    }

    @Override
    public void onFailure(Call<Response<Boolean>> call, Throwable t) {

    }
}
