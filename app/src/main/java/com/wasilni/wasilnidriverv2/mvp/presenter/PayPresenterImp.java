package com.wasilni.wasilnidriverv2.mvp.presenter;

import com.wasilni.wasilnidriverv2.mvp.model.Cause;
import com.wasilni.wasilnidriverv2.mvp.model.Payment;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.PayContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class PayPresenterImp implements PayContract.PayPresenter {
    @Override
    public void sendToServer(Payment request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<Payment>> call =
                service.Pay(Token , request.getBooking().getId() , request.getPassenger_paid_amount() );

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response<Payment>> call, Response<com.wasilni.wasilnidriverv2.network.Response<Payment>> response) {

    }

    @Override
    public void onFailure(Call<com.wasilni.wasilnidriverv2.network.Response<Payment>> call, Throwable t) {

    }
}
