package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonObject;
import com.wasilni.wasilnidriverv2.mvp.model.RegisterCaptain;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.CompleteDataContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.RegistrationActivity;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UtilFunction;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import retrofit2.Call;

public class CompleteDataPresenterImp implements CompleteDataContract.CompleteDataPresenter {
    RegistrationActivity registrationActivity ;
    public CompleteDataPresenterImp(RegistrationActivity registrationActivity) {
        this.registrationActivity = registrationActivity ;
    }

    @Override
    public void sendToServer(RegisterCaptain request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        UtilFunction.showProgressBar(registrationActivity);
        /** Call the method with parameter in the interface to get the notice data*/
        Call<com.wasilni.wasilnidriverv2.network.Response<User>> call =
                service.CompleteInfo( request);

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {

        Log.e("onResponse register",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();
        switch (response.code())
        {
            case 200 :
                SharedPreferenceUtils.getEditorInstance(registrationActivity.getApplicationContext())
                        .putString("auth_token", "Bearer "+response.body().getData().getAccessToken());
                SharedPreferenceUtils.getEditorInstance(registrationActivity.getApplicationContext()).putInt("user_id",1);
                SharedPreferenceUtils.getEditorInstance(registrationActivity.getApplicationContext()).commit();
                UtilFunction.CheckFCMToken(registrationActivity.getApplicationContext());
                registrationActivity.responseCode200(null);
                break;
            case 422 :
                registrationActivity.responseCode422(response.body().getMessage());
                break;
            case 500 :
                registrationActivity.responseCode500();
                break;
            case 400:
                registrationActivity.responseCode400();
                break;
            case 401:
                registrationActivity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<User>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
        UtilFunction.hideProgressBar();

    }
}
