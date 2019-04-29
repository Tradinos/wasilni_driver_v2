package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.LatLng;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.util.RideStatus;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.getLatLng;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.hideProgressBar;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.showProgressBar;

public class ChangeBookingStatePresenterImp implements ChangeRideContract.ChangeBookingPresenter {

    Booking mBooking;
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
        showProgressBar(tripPassengersActionsFragment.activity);
        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<Booking>> call =
                service.ChangeBookingState(Token ,  request.getId()   );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Booking>> call, retrofit2.Response<Response<Booking>> response) {
        Log.e("onResponse do action",response.message()+" code :"+response.code());
        hideProgressBar();

        switch (response.code())
        {
            case 200 :
                Log.e("data", ""+response.body().getData().getStatus() );
                if(response.body().getData().getStatus().equals("DONE")){
                    mBooking.setStatus(response.body().getData().getStatus());
                    mBooking.setSummary(response.body().getData().getSummary());
                    mBooking.setIs_pooling(response.body().getData().getIs_pooling());
                    mBooking.setPrice(response.body().getData().getPrice());
                    mBooking.setTo_pay(response.body().getData().getTo_pay());
                    rideSummaryFragment = RideSummaryFragment.newInstance(tripPassengersActionsFragment.activity);
                    rideSummaryFragment.responseCode200(mBooking,tripPassengersActionsFragment);
                    try {
                        tripPassengersActionsFragment.activity.moveCamera(new LatLng(UserUtil.getUserInstance().getLocation().getLatitude(), UserUtil.getUserInstance().getLocation().getLongitude()));
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                else {
                    mBooking.setStatus(response.body().getData().getStatus());
                    if(mBooking.getStatus().equals("PICKED_UP")){
                        tripPassengersActionsFragment.activity.moveCamera(getLatLng(mBooking.getPullDownLocation().getCoordinates()));
                    }
                    else{
                        tripPassengersActionsFragment.activity.moveCamera(getLatLng(mBooking.getPickUpLocation().getCoordinates()));
                    }

                    tripPassengersActionsFragment.updateListList(mBooking);

                }
                break;
            case 422 :
                tripPassengersActionsFragment.activity.responseCode422(response.body().getMessage());
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
    public void onFailure(Call<Response<Booking>> call, Throwable t) {
        Log.e("onFailure do action",t.getMessage());
        hideProgressBar();
    }
}
