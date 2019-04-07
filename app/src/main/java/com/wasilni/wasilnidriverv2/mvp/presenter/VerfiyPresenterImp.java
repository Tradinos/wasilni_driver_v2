package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.VerifyContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;

import retrofit2.Call;
import retrofit2.Response;

public class VerfiyPresenterImp implements VerifyContract.VerfiyPresenter {
    @Override
    public void sendToServer(User request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<User>> call =
                service.VerifyCode( request.getPhone_number(),request.getActivation_code() , "captains");

        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response<User>> call, Response<com.wasilni.wasilnidriverv2.network.Response<User>> response) {
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
    public void onFailure(Call call, Throwable t) {
        Log.e("onFailure",t.getMessage());
    }
}
