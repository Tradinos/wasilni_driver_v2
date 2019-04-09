package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.ChangeRideContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripSummaryFragment;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class ChangeBookingStatePresenterImp implements ChangeRideContract.ChangeBookingPresenter {

    BookingAdapter mAdapter ;
    Booking mBooking;
    Activity activity ;
    TripSummaryFragment fragment = TripSummaryFragment.newInstance();
    public ChangeBookingStatePresenterImp(BookingAdapter mAdapter, Activity activity) {
        this.mAdapter = mAdapter ;
        this.activity = activity ;
    }

    @Override
    public void sendToServer(Booking request) {
        request.setStatus(RideStatus.nextState(request.getStatus()));

        mBooking = request ;

        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<Booking>> call =
                service.ChangeBookingState(Token ,  request.getId() , request.getStatus()  );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Booking>> call, retrofit2.Response<Response<Booking>> response) {
        Log.e("onResponse do action",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                if(mBooking.getStatus().equals("DONE")){
                    Booking object = response.body().getData();
                    List<Booking> list = mAdapter.getList();
                    list.remove(mBooking);
                    mAdapter.setList(list);
                    mAdapter.notifyDataSetChanged();
                    fragment.show(((FragmentActivity)activity).getSupportFragmentManager(),"123");
                }
                // refreshAdapter and recycler view
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
        Log.e("onFailure do action",mBooking.getStatus());
        switch (mBooking.getStatus()){
            case "STARTED" :
                mBooking.setStatus("APPROVED") ;
            case "ARRIVED" :
                mBooking.setStatus("STARTED") ;
            case "PICKED_UP" :
                mBooking.setStatus("ARRIVED") ;
            case "DONE" :
                mBooking.setStatus("PICKED_UP") ;
            case "FINISH" :
                mBooking.setStatus("DONE") ;

        }
        Log.e("onFailure do action",mBooking.getStatus());

    }
}
