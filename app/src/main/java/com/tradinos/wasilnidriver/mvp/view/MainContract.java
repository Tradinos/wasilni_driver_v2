package com.tradinos.wasilnidriver.mvp.view;


import android.widget.ProgressBar;

public interface MainContract {


    ProgressBar initProgressBar();
    void onFailure(Throwable t);
    void responseCode200(Boolean response);
    void responseCode422(String message);
    void responseCode500();
    void responseCode400();
    void responseCode401();
}
