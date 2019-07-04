package com.tradinos.wasilnidriver.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.Ride;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.network.ServerPresenter;
import com.tradinos.wasilnidriver.ui.Activities.RegistrationActivity;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UserUtil;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.UtilFunction.hideProgressBar;
import static com.tradinos.wasilnidriver.util.UtilFunction.showProgressBar;
import static com.tradinos.wasilnidriver.util.UtilFunction.showToast;

public class DailyReportPresenter implements ServerPresenter<String , PaginationAPI<Ride> > {
    Activity activity ;
    OnResponseInterface anInterface ;
    public DailyReportPresenter(Activity activity,OnResponseInterface anInterface) {
        this.activity = activity;
        this.anInterface = anInterface ;
    }

    @Override
    public void sendToServer(String request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        showProgressBar(activity);

        Call<Response<PaginationAPI<Ride>>> call =
                service.dailyReport(UserUtil.getUserInstance().getAccessToken(), 100 ,request);

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
