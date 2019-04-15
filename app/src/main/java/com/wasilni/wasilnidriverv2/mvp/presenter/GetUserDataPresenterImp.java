package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.UserData;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class GetUserDataPresenterImp implements UserData.GetUserData {
    HomeActivity activity ;

    public GetUserDataPresenterImp(HomeActivity activity) {
        this.activity = activity;
    }

    @Override
    public void sendToServer(Object request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<User>> call =
                service.GetUserData( Token );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
        Log.e("onResponse GetUserData",response.message()+" code :"+response.code());

        switch(response.code())
        {
            case 200 :
                activity.setDriverStatus(response.body().getData());
                break;
            case 422 :
                activity.responseCode422();
                break;
            case 500 :
                activity.responseCode500();
                break;
            case 400:
                activity.responseCode400();
                break;
            case 401:
                activity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<User>> call, Throwable t) {
        Log.e("onFailure GetUserData",t.getMessage());

    }
}
