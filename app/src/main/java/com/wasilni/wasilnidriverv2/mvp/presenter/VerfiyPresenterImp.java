package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.VerifyContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneVerificationFragment;
import com.wasilni.wasilnidriverv2.util.UserUtil;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;

public class VerfiyPresenterImp implements VerifyContract.VerfiyPresenter {
    PhoneVerificationFragment phoneVerificationFragment ;
    public VerfiyPresenterImp(PhoneVerificationFragment phoneVerificationFragment) {
        this.phoneVerificationFragment = phoneVerificationFragment ;
    }

    @Override
    public void sendToServer(User request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<com.wasilni.wasilnidriverv2.network.Response<User>> call =
                service.VerifyCode( request.getPhone_number(),request.getActivation_code() , "captains");
        UtilFunction.showProgressBar(phoneVerificationFragment.getActivity());

        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response<User>> call, Response<com.wasilni.wasilnidriverv2.network.Response<User>> response) {
        Log.e("onResponse",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();

        switch (response.code())
        {
            case 200 :
                UserUtil.getUserInstance().setAccessToken("Bearer "+ response.body().getData().getAccessToken());
                UtilFunction.sendDeviceData(phoneVerificationFragment.getActivity());
                phoneVerificationFragment.responseCode200(response.body().getData());
                break;
            case 422 :
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    String userMessage = jsonObject.getString("message");
                    UtilFunction.showToast(phoneVerificationFragment.getActivity() , userMessage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        t.printStackTrace();
        Log.e("onFailure",t.getMessage());
    }
}
