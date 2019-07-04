package com.tradinos.wasilnidriver.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.Brand;
import com.tradinos.wasilnidriver.mvp.model.BrandModel;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.mvp.view.BrandModelContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.Constants.Token;
import static com.tradinos.wasilnidriver.util.UtilFunction.p;

public class BrandModelsPresenterImp implements BrandModelContract.BrandModelsPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public BrandModelsPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Brand request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        UtilFunction.showProgressBar(context);
        Call<Response<PaginationAPI<BrandModel>>> call =
                service.BrandModels( Token, request.getId());

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<BrandModel>>> call, retrofit2.Response<Response<PaginationAPI<BrandModel>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();

        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateBrandModels((List<BrandModel>) response.body().getData().getData());
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
                break;
            case 400 :
                break;
            case 401 :
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<PaginationAPI<BrandModel>>> call, Throwable t) {
        t.printStackTrace();
        UtilFunction.hideProgressBar();

    }

    public interface OnResponseInterface{
        void populateBrandModels(List<BrandModel> items);
        void onFailure(List<String> errors);
    }
}
