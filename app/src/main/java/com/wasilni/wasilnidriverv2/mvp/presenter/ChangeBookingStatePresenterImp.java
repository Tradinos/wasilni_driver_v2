package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class ChangeBookingStatePresenterImp implements ChangeRideContract.ChangeBookingPresenter {

    @Override
    public void sendToServer(Booking request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<Booking>> call =
                service.ChangeBookingState(Token ,  request.getId() , request.getStatus()  );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Booking>> call, retrofit2.Response<Response<Booking>> response) {
        Log.e("onResponse",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                break;
            case 422 :
                break;
            case 500 :
                break;
            case 400:
                break;
            case 401:
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<Booking>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
    }
}
