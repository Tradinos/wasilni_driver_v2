package com.wasilni.wasilnidriverv2.mvp.view;

import android.app.Activity;
import android.view.View;

public interface AdapterContract {

    public interface  AdapterPresenter<T,F> extends View.OnClickListener {
        public void ObjectToHolder(T object , F holder , Activity activity);
    }
}
