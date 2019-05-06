package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Cause;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.RateCauseContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.p;

public class CausePresenterImp implements RateCauseContract.CausePresenter {
    HomeActivity activity ;
    public CausePresenterImp(HomeActivity activity) {
        this.activity = activity ;
    }


    @Override
    public void sendToServer(Cause request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<PaginationAPI<List<Cause>>>> call =
                service.GetCauses(Token , 100);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<List<Cause>>>> call, retrofit2.Response<Response<PaginationAPI<List<Cause>>>> response) {
        Log.e("onResponse",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                break;
            case 422 :
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
                    p(response.errorBody().toString());
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
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<List<Cause>>>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
    }

}
