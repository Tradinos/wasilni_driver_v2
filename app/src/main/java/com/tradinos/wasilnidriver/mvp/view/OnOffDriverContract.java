package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface OnOffDriverContract {
    public interface OnOffDriverPresenter extends ServerPresenter<Boolean,Boolean> {

    }
}
