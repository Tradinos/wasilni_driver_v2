package com.wasilni.wasilnidriverv2.mvp.view;

import android.view.View;

/*
 * Created by Mahmoud Mattar on 3/17/2019
 */
public interface MainView extends View.OnClickListener {
    public void initView();
    public void onSuccess();
    public void onFailuer();
}
