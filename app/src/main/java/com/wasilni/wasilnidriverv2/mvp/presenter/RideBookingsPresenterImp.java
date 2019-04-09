package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class RideBookingsPresenterImp implements RideContruct.RideBookingsPresenter {

    TripPassengersActionsFragment activity ;
    Activity context ;
    public RideBookingsPresenterImp(TripPassengersActionsFragment fragment , Activity activity) {
        this.activity = fragment ;
        context = activity ;
    }

    @Override
    public void sendToServer(Ride request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<Ride>> call =
                service.GetRideBookings( Token ,request.getId(), 20 );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Ride>> call, retrofit2.Response<Response<Ride>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                Log.e("booking list :",response.body().getData().getBookings().toString()) ;
                activity.show(((FragmentActivity)context ).getSupportFragmentManager() , R.id.bottomsheet);
                activity.mAdapter.setList(response.body().getData().getBookings());
                activity.mAdapter.notifyDataSetChanged();
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
    public void onFailure(Call<Response<Ride>> call, Throwable t) {
        Log.e("onFailure ride booking",t.getMessage());
    }
}
