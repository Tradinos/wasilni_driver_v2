package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.RegisterCaptain;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.CompleteDataContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.RegistrationActivity;

import retrofit2.Call;

public class CompleteDataPresenterImp implements CompleteDataContract.CompleteDataPresenter {
    RegistrationActivity registrationActivity ;
    public CompleteDataPresenterImp(RegistrationActivity registrationActivity) {
        this.registrationActivity = registrationActivity ;
    }

    @Override
    public void sendToServer(RegisterCaptain request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Log.d("CompleteData", "sendToServer: " + request);
        Call<com.wasilni.wasilnidriverv2.network.Response<User>> call =
                service.CompleteInfo( request);

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
        Log.e("onResponse register",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                registrationActivity.responseCode200(null);
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
    public void onFailure(Call<Response<User>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
    }
}
