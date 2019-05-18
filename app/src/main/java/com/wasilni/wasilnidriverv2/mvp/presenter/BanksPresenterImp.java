package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Bank;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.BankContract;
import com.wasilni.wasilnidriverv2.mvp.view.NationalityContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.RegistrationActivity;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.p;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.showToast;

public class BanksPresenterImp implements BankContract.BanksPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public BanksPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Bank request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        UtilFunction.showProgressBar(context);
        Call<Response<PaginationAPI<Bank>>> call =
                service.Banks( Token );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Bank>>> call, retrofit2.Response<Response<PaginationAPI<Bank>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();

        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateBanks((List<Bank>) response.body().getData().getData());
                break;
            case 422 :
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    p(response.errorBody().toString());
                    String userMessage = jsonObject.getString("message");
                    UtilFunction.showToast(context , userMessage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 500 :
                showToast(context , R.string.error_500);
                break;
            case 400 :
                showToast(context , R.string.error_400);
                break;
            case 401 :
                UtilFunction.showToast(context, R.string.error_401);
                SharedPreferenceUtils.getEditorInstance(context).remove("auth_token");
                SharedPreferenceUtils.getEditorInstance(context).commit();
                Intent intent = new Intent(context, RegistrationActivity.class);
                context.startActivity(intent);
                ActivityCompat.finishAffinity(context);
                showToast(context , R.string.error_401);
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<Bank>>> call, Throwable t) {
        t.printStackTrace();
        UtilFunction.hideProgressBar();
    }

    public interface OnResponseInterface{
        void populateBanks(List<Bank> items);
        void onFailure(List<String> errors);
    }
}
