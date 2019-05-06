package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;
import android.view.View;

import com.wasilni.wasilnidriverv2.mvp.model.Payment;
import com.wasilni.wasilnidriverv2.mvp.view.PayContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.util.RideStatus;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class PayPresenterImp implements PayContract.PayPresenter {
    RideSummaryFragment fragment ;
    public PayPresenterImp(RideSummaryFragment rideSummaryFragment) {
        fragment = rideSummaryFragment;
    }

    @Override
    public void sendToServer(Payment request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        UtilFunction.showProgressBar(fragment.activity);
        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<Payment>> call =
                service.Pay(Token , request.getBooking().getId() , request.getPassenger_paid_amount() );

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response<Payment>> call, Response<com.wasilni.wasilnidriverv2.network.Response<Payment>> response) {
        Log.e("onResponse pay",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();
        switch (response.code())
        {
            case 200 :
                UtilFunction.showToast(fragment.getActivity(),"Done");
                fragment.dismiss();
                fragment.moneyCost.setText("");
                fragment.sendedBooking.setStatus(RideStatus.nextState(fragment.sendedBooking.getStatus()));
                Log.e("submit", "onClick: "+fragment.sendedBooking.getId());
                fragment.tripPassengersActionsFragment.deleteBooking(fragment.sendedBooking);
                break;
            case 422:
                try {
                    fragment.activity.responseCode422(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 500:
                fragment.activity.responseCode500();
                break;
            case 400:
                fragment.activity.responseCode400();
                break;
            case 401:
                fragment.activity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<com.wasilni.wasilnidriverv2.network.Response<Payment>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
    }
}
