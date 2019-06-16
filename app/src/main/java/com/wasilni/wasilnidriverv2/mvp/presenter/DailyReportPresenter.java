package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.network.ServerPresenter;
import com.wasilni.wasilnidriverv2.ui.Activities.RegistrationActivity;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UserUtil;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.hideProgressBar;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.showProgressBar;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.showToast;

public class DailyReportPresenter implements ServerPresenter<Object , PaginationAPI<Ride> > {
    Activity activity ;
    OnResponseInterface anInterface ;
    public DailyReportPresenter(Activity activity,OnResponseInterface anInterface) {
        this.activity = activity;
        this.anInterface = anInterface ;
    }

    @Override
    public void sendToServer(Object request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        showProgressBar(activity);

        Call<Response<PaginationAPI<Ride>>> call =
                service.dailyReport(UserUtil.getUserInstance().getAccessToken(), 100 ,"PAID");

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Ride>>> call, retrofit2.Response<Response<PaginationAPI<Ride>>> response) {
        Log.e("onResponse GetRides",response.message()+" code :"+response.code());
        hideProgressBar();
        switch (response.code())
        {
            case 200 :
                Log.e("onResponse: ",""+response.body().getData().getData().size() );
                anInterface.populateBooking(response.body().getData().getData());
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
                showToast(activity , R.string.error_500);
                break;
            case 400 :
                showToast(activity , R.string.error_400);
                break;
            case 401 :
                showToast(activity , R.string.error_401);
                SharedPreferenceUtils.getEditorInstance(activity.getApplicationContext()).remove("auth_token");
                SharedPreferenceUtils.getEditorInstance(activity.getApplicationContext()).commit();
                Intent intent = new Intent(activity, RegistrationActivity.class);
                activity.startActivity(intent);
                ActivityCompat.finishAffinity(activity);
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<Ride>>> call, Throwable t) {
//        Log.e("onFailure",t.getMessage());
        hideProgressBar();
        t.printStackTrace();
    }
    public interface OnResponseInterface{
        void populateBooking(List<Ride> rides);
    }
}
