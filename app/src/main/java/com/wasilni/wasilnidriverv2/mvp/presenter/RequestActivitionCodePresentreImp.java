package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.RequestActivitionCodeContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneRegistrationFragment;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import retrofit2.Call;
import retrofit2.Response;

public class RequestActivitionCodePresentreImp implements RequestActivitionCodeContract.RequestActivitionCodePresentre {
    PhoneRegistrationFragment phoneRegistrationFragment;
    public RequestActivitionCodePresentreImp(PhoneRegistrationFragment phoneRegistrationFragment) {
        this.phoneRegistrationFragment = phoneRegistrationFragment ;
    }

    @Override
    public void sendToServer(User request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<User>> call =
                service.RequestActivationCode( request.getPhone_number(), "captains");
        UtilFunction.showProgressBar(phoneRegistrationFragment.getActivity());
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response<User>> call, Response<com.wasilni.wasilnidriverv2.network.Response<User>> response) {
        Log.e("onResponse",response.message()+" RequestActivitionCodePresentreImp code :"+response.code());
        UtilFunction.hideProgressBar();

        switch (response.code())
        {
            case 200 :
                phoneRegistrationFragment.responseCode200(response.body().getData());
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
        UtilFunction.hideProgressBar();
        Log.e("onFailure",t.getMessage());
    }
}
