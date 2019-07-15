package com.tradinos.wasilnidriver.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.Report;
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

public class ReportProblemPresenter implements ServerPresenter<UploadReport, Report> {
    Listener listener ;
    Activity activity ;

    public ReportProblemPresenter(Listener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    public void sendToServer(UploadReport request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        showProgressBar(activity);
        Log.e( "ReportProblem ", ""+ request.getLastQuestionId() +" "+  request.getTicketTypeId() );
        Call<Response<Report>> call =
                service.getReport( UserUtil.getUserInstance().getAccessToken(), request.getTicketTypeId() , request.getLastQuestionId());

        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<Response<Report>> call, retrofit2.Response<Response<Report>> response) {
        Log.e("ReportProblemPresenter",response.message()+" code :"+response.code());
        hideProgressBar();
        switch (response.code())
        {
            case 200 :
                Log.e( "onResponse: ","11111" );
                listener.showReportDialog(response.body().getData());
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
    public void onFailure(Call<Response<Report>> call, Throwable t) {
        t.printStackTrace();
        hideProgressBar();
        showToast(activity ,R.string.failure_message);
    }

    public interface Listener {
        public void showReportDialog(Report data);
    }
}
