package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.hideProgressBar;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.showProgressBar;

public class GetMyRidesPresenterImp implements RideContruct.MyRidesPresenter {
    HomeActivity activity ;

    public GetMyRidesPresenterImp(HomeActivity activity) {
        this.activity = activity;
    }

    @Override
    public void sendToServer(User request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        showProgressBar(activity);

        Call<Response<PaginationAPI<Ride>>> call =
                service.GetRides( Token , 20,"NOT_PAID" );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Ride>>> call, retrofit2.Response<Response<PaginationAPI<Ride>>> response) {
        Log.e("onResponse GetRides",response.message()+" code :"+response.code());
        hideProgressBar();
        switch (response.code())
        {
            case 200 :
                activity.setRides(response.body().getData().getData());
                break;
            case 422 :
                try {
                    activity.responseCode422(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 500 :
                activity.responseCode500();
                break;
            case 400 :
                activity.responseCode400();
                break;
            case 401 :
                activity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<Ride>>> call, Throwable t) {
//        Log.e("onFailure",t.getMessage());
        hideProgressBar();
        t.printStackTrace();
    }

}
