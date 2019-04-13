package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class ChangeBookingStatePresenterImp implements ChangeRideContract.ChangeBookingPresenter {

    Booking mBooking;
    Activity activity ;
    TripPassengersActionsFragment tripPassengersActionsFragment;
    RideSummaryFragment rideSummaryFragment ;
    public ChangeBookingStatePresenterImp(TripPassengersActionsFragment tripPassengersActionsFragment) {
        this.tripPassengersActionsFragment = tripPassengersActionsFragment ;
    }

    @Override
    public void sendToServer(Booking request) {
        request.setStatus(RideStatus.nextState(request.getStatus()));

        mBooking = request ;
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<Booking>> call =
                service.ChangeBookingState(Token ,  request.getId()   );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Booking>> call, retrofit2.Response<Response<Booking>> response) {
        Log.e("onResponse do action",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                if(response.body().getData().getStatus().equals("DONE")){
                    rideSummaryFragment = RideSummaryFragment.newInstance();
                    rideSummaryFragment.responseCode200(response.body().getData());
                    tripPassengersActionsFragment.deleteBooking(response.body().getData());
                }
                else {
                    mBooking.setStatus(response.body().getData().getStatus());
                    tripPassengersActionsFragment.updateListList();
                }
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
    public void onFailure(Call<Response<Booking>> call, Throwable t) {
        Log.e("onFailure do action",t.getMessage());
    }
}
