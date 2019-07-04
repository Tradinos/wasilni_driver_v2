package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.mvp.view.RequestActivitionCodeContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.ui.Fragments.PhoneRegistrationFragment;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import java.util.Calendar;

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

        Call<com.tradinos.wasilnidriver.network.Response<User>> call =
                service.RequestActivationCode( request.getPhone_number(), "captains");
        UtilFunction.showProgressBar(phoneRegistrationFragment.getActivity());
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<com.tradinos.wasilnidriver.network.Response<User>> call, Response<com.tradinos.wasilnidriver.network.Response<User>> response) {
        Log.e("onResponse",response.message()+" RequestActivitionCodePresentreImp code :"+response.code());
        UtilFunction.hideProgressBar();

        switch (response.code())
        {
            case 200 :
                SharedPreferenceUtils.getEditorInstance(phoneRegistrationFragment.getActivity()).remove("activition_code_time").commit();
                SharedPreferenceUtils.getEditorInstance(phoneRegistrationFragment.getActivity()).putLong("activition_code_time", Calendar.getInstance().getTime().getTime()).commit();

                phoneRegistrationFragment.responseCode200(response.body().getData());
                break;
            case 422 :
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    String userMessage = jsonObject.getString("message");
                    UtilFunction.showToast( phoneRegistrationFragment.getActivity() , userMessage);

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
//        Log.e("onFailure",t.getMessage());
        t.printStackTrace();

    }
}
