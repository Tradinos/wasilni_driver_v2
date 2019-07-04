package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.RegisterCaptain;
import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.mvp.view.CompleteDataContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.ui.Activities.RegistrationActivity;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.UtilFunction.p;

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
        Call<com.tradinos.wasilnidriver.network.Response<User>> call =
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
                UtilFunction.showToast(registrationActivity , "111");
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    p(response.errorBody().toString());
                    String userMessage = jsonObject.getString("message");
                    UtilFunction.showToast(registrationActivity , userMessage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
//        Log.e("onFailure",t.getMessage());
        UtilFunction.hideProgressBar();
        t.printStackTrace();

    }
}
