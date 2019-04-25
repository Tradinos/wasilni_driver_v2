package com.wasilni.wasilnidriverv2.mvp.view;


import android.app.Activity;
import android.widget.ProgressBar;

import com.wasilni.wasilnidriverv2.network.Response;

public interface MainContract {


    ProgressBar initProgressBar();
    void onFailure(Throwable t);
    void responseCode200(Boolean response);
    void responseCode422(String message);
    void responseCode500();
    void responseCode400();
    void responseCode401();
}
