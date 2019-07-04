package com.tradinos.wasilnidriver.mvp.view;


import com.tradinos.wasilnidriver.mvp.model.Bank;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ServerPresenter;

public interface BankContract {
    public interface BanksPresenter extends ServerPresenter<Bank, PaginationAPI<Bank>>{

    }
}
