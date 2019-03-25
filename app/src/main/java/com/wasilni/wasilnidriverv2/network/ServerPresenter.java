package com.wasilni.wasilnidriverv2.network;

import retrofit2.Callback;

public interface ServerPresenter<T> extends Callback<Response<T>> {
    void sendToServer(T request);
}
