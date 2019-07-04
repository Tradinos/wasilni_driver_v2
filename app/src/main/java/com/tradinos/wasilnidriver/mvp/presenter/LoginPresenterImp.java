package com.tradinos.wasilnidriver.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.mvp.view.LoginContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.ui.Activities.HomeActivity;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UserUtil;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import retrofit2.Call;

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
        UtilFunction.hideProgressBar();
        switch (response.code())
        {
            case 200 :
                UserUtil.getUserInstance().setAccessToken("Bearer "+ response.body().getData().getAccessToken());
                UtilFunction.sendDeviceData(activity);

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
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    String userMessage = jsonObject.getString("message");
                    UtilFunction.showToast(activity , userMessage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 500 :
                break;
            case 400:
                break;
            case 401:
                JSONObject jsonObject1 = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject1 = new JSONObject(res);
                    String userMessage = jsonObject1.getString("message");
                    UtilFunction.showToast(activity , userMessage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<User>> call, Throwable t) {
        try {
            t.printStackTrace();
//            Log.e("onFailure",t.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }

        UtilFunction.hideProgressBar();
    }
}
