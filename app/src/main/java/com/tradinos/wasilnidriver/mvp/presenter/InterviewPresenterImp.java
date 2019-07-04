package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;

import com.tradinos.wasilnidriver.mvp.view.InterviewContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.ui.Activities.InterviewActivity;
import com.tradinos.wasilnidriver.ui.Fragments.InterviewRegistrationFragment;
import com.tradinos.wasilnidriver.util.UserUtil;
import com.tradinos.wasilnidriver.util.UtilFunction;

import retrofit2.Call;

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
        Log.e("interveiw auth",UserUtil.getUserInstance().getAccessToken());
        Call<com.tradinos.wasilnidriver.network.Response<Object>> call =
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
//        Log.e("onFailure",t.getMessage());
        t.printStackTrace();
        UtilFunction.hideProgressBar();
    }
}
