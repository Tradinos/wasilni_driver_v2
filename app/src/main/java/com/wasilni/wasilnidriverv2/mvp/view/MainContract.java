package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.network.Response;

public interface MainContract {


    public interface ProgressBar{
        android.widget.ProgressBar initProgressBar();
        void showProgressBar();
        void hideProgressBar();
    }

    interface APIHandling {

        interface OnFinishedListener {
            void onSuccess(Response response);
            void onFailure(Throwable t);
        }
    }

}
