package com.wasilni.wasilnidriverv2.network;

import retrofit2.Callback;

public interface ServerPresenter<T,F> extends Callback<Response<F>> {
    void sendToServer(T request);
}
