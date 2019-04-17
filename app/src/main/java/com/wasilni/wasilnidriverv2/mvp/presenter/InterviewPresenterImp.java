package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.InterviewContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Fragments.InterviewRegistrationFragment;
import com.wasilni.wasilnidriverv2.util.UtilUser;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class InterviewPresenterImp implements InterviewContract.InterviewPresenter {
    InterviewRegistrationFragment interviewRegistrationFragment;
    public InterviewPresenterImp(InterviewRegistrationFragment interviewRegistrationFragment) {
        this.interviewRegistrationFragment = interviewRegistrationFragment ;
    }

    @Override
    public void sendToServer(String request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<Object>> call =
                service.Interview(Token, request);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Object>> call, retrofit2.Response<Response<Object>> response) {
        Log.e("onResponse Interview",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                interviewRegistrationFragment.responseCode200();
                break;
            case 422 :
                interviewRegistrationFragment.responseCode422();
                break;
            case 500 :
                interviewRegistrationFragment.responseCode500();
                break;
            case 400:
                interviewRegistrationFragment.responseCode401();
                break;
            case 401:
                interviewRegistrationFragment.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<Object>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());

    }
}
