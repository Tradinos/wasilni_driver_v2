package com.wasilni.wasilnidriverv2.mvp.view;


import android.widget.ProgressBar;

import com.wasilni.wasilnidriverv2.network.Response;

public interface MainContract {

    void showProgressBar();
    void hideProgressBar();
    ProgressBar initProgressBar();
    void onFailure(Throwable t);
    void responseCode200(Boolean response);
    void responseCode422();
    void responseCode500();
    void responseCode400();
    void responseCode401();
}
