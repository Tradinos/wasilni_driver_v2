package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.Booking;
import com.tradinos.wasilnidriver.mvp.model.Ride;
import com.tradinos.wasilnidriver.mvp.view.RideContruct;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.ui.Dialogs.TripPassengersActionsFragment;

import java.io.IOException;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.Constants.Token;
import static com.tradinos.wasilnidriver.util.UtilFunction.hideProgressBar;
import static com.tradinos.wasilnidriver.util.UtilFunction.showProgressBar;

public class RideBookingsPresenterImp implements RideContruct.RideBookingsPresenter {
    int number ;
    TripPassengersActionsFragment tripPassengersActionsFragment ;
    public RideBookingsPresenterImp(TripPassengersActionsFragment fragment) {
        this.tripPassengersActionsFragment = fragment ;
    }

    @Override
    public void sendToServer(Ride request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        showProgressBar(tripPassengersActionsFragment.activity);
        /** Call the method with parameter in the interface to get the notice data*/
        number = request.getNumber() ;
        Call<Response<Ride>> call =
                service.GetRideBookings( Token ,request.getId(), 20 );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Ride>> call, retrofit2.Response<Response<Ride>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());
        hideProgressBar();
        switch (response.code())
        {
            case 200 :
                Log.e("booking list :",response.body().getData().getBookings().toString()) ;
                for(Booking booking : response.body().getData().getBookings()){
                    booking.setNumber(number);
                }
                tripPassengersActionsFragment.setBookings(response.body().getData());
                break;
            case 422 :
                try {
                    tripPassengersActionsFragment.activity.responseCode422(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 500 :
                tripPassengersActionsFragment.activity.responseCode500();
                break;
            case 400 :
                tripPassengersActionsFragment.activity.responseCode400();
                break;
            case 401 :
                tripPassengersActionsFragment.activity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<Ride>> call, Throwable t) {
//        Log.e("onFailure ride booking",t.getMessage());
        t.printStackTrace();
        hideProgressBar();
    }
}
