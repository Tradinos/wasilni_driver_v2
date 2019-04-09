package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class RideBookingsPresenterImp implements RideContruct.RideBookingsPresenter {

    @Override
    public void sendToServer(PaginationAPI<Ride> request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<PaginationAPI<Ride>>> call =
                service.GetRideBookings( Token ,request.getData().getId(), 20 );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Ride>>> call, retrofit2.Response<Response<PaginationAPI<Ride>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                Log.e("booking list :",response.body().getData().getData().getBookings().toString()) ;
                break;
            case 422 :
                break;
            case 500 :
                break;
            case 400 :
                break;
            case 401 :
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<Ride>>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
    }
}
