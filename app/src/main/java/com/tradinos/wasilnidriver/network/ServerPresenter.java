package com.tradinos.wasilnidriver.network;

import retrofit2.Callback;

public interface ServerPresenter<T,F> extends Callback<Response<F>> {
    void sendToServer(T request);
}
