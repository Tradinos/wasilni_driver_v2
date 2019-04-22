package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Cause;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.LoginContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UserUtil;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class LoginPresenterImp implements LoginContract.LoginPresenter {
    Activity activity;
    public LoginPresenterImp(Activity activity){
        this.activity = activity;
    }

    @Override
    public void sendToServer(User request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        UtilFunction.showProgressBar(activity);
        /** Call the method with parameter in the interface to get the notice data*/
        Call<Response<User>> call =
                service.Login( request.getPhone_number(),request.getPassword() , "captains");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
        Log.e("onResponse login",response.message()+" code :"+response.code());
        UtilFunction.showProgressBar(activity);
        switch (response.code())
        {
            case 200 :
                SharedPreferenceUtils.getEditorInstance(activity.getApplicationContext())
                        .putString("auth_token","Bearer "+ response.body().getData().getAccessToken());
                SharedPreferenceUtils.getEditorInstance(activity.getApplicationContext()).putInt("user_id",1);
                SharedPreferenceUtils.getEditorInstance(activity.getApplicationContext()).commit();
                UtilFunction.CheckFCMToken(activity.getApplicationContext());
                Intent intent = new Intent(activity, HomeActivity.class);
                activity.startActivity(intent);
                ActivityCompat.finishAffinity(activity);
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
        UtilFunction.hideProgressBar();
    }
}
