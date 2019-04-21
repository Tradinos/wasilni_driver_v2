package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.view.InterviewContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.ui.Activities.InterviewActivity;
import com.wasilni.wasilnidriverv2.ui.Fragments.InterviewRegistrationFragment;
import com.wasilni.wasilnidriverv2.util.UserUtil;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class InterviewPresenterImp implements InterviewContract.InterviewPresenter {
    InterviewRegistrationFragment interviewRegistrationFragment;
    public InterviewPresenterImp(InterviewRegistrationFragment interviewRegistrationFragment) {
        this.interviewRegistrationFragment = interviewRegistrationFragment ;
    }
    InterviewActivity activity ;
    public InterviewPresenterImp(InterviewActivity activity) {
        this.activity = activity ;
    }

    @Override
    public void sendToServer(String request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<Object>> call =
                service.Interview(UserUtil.getUserInstance().getAccessToken(), request);

        call.enqueue(this);
        UtilFunction.showProgressBar(activity);
    }

    @Override
    public void onResponse(Call<Response<Object>> call, retrofit2.Response<Response<Object>> response) {
        Log.e("onResponse Interview",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();
        switch (response.code())
        {
            case 200 :
                activity.responseCode200();
                break;
            case 422 :
                activity.responseCode422();
                break;
            case 500 :
                activity.responseCode500();
                break;
            case 400:
                activity.responseCode401();
                break;
            case 401:
                activity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<Object>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
        UtilFunction.hideProgressBar();
    }
}
