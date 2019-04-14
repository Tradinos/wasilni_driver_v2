package com.wasilni.wasilnidriverv2.mvp.view;


import com.wasilni.wasilnidriverv2.mvp.model.Bank;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;

import java.util.List;

public interface BankContract {
    public interface BanksPresenter extends ServerPresenter<Bank, PaginationAPI<Bank>>{

    }
}
