package com.tradinos.wasilnidriver.mvp.view;

import android.view.View;

public interface AdapterContract {

    public interface  AdapterPresenter<T,F> extends View.OnClickListener {
        public void ObjectToHolder(T object ,int position, F holder);
    }
}
