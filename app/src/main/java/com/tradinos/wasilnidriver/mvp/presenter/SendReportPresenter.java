package com.tradinos.wasilnidriver.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.UploadReport;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.network.ServerPresenter;
import com.tradinos.wasilnidriver.ui.Activities.RegistrationActivity;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UserUtil;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.UtilFunction.hideProgressBar;
import static com.tradinos.wasilnidriver.util.UtilFunction.showProgressBar;
import static com.tradinos.wasilnidriver.util.UtilFunction.showToast;


public class SendReportPresenter implements ServerPresenter<UploadReport, Object> {
    Activity activity ;
    Listener listener ;

    public SendReportPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(UploadReport request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        showProgressBar(activity);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<Response<Object>> call =
                service.PostReport( UserUtil.getUserInstance().getAccessToken(), request );
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Object>> call, retrofit2.Response<Response<Object>> response) {
        Log.e("GetFavoritePresenter",response.message()+" code :"+response.code());
        hideProgressBar();
        switch (response.code())
        {
            case 200 :
                listener.ReportSubmited();
                break;
            case 422 :
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    String userMessage = jsonObject.getString("message");
                    showToast(activity , userMessage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 500 :
                showToast(activity , R.string.error_500);
                break;
            case 400:
                showToast(activity , R.string.error_400);
                break;
            case 401:
                showToast(activity, R.string.error_401);
                SharedPreferenceUtils.getEditorInstance(activity).remove("auth_token");
                SharedPreferenceUtils.getEditorInstance(activity).commit();
                Intent intent = new Intent(activity, RegistrationActivity.class);
                activity.startActivity(intent);
                ActivityCompat.finishAffinity(activity);
                showToast(activity , R.string.error_401);
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<Object>> call, Throwable t) {
        t.printStackTrace();
        hideProgressBar();
        showToast(activity ,R.string.failure_message);
    }


    public interface Listener {
        public void ReportSubmited();
    }
}
