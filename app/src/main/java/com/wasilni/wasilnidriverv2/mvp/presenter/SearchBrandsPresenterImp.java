package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.view.BrandContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.p;

public class SearchBrandsPresenterImp implements BrandContract.BrandsPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public SearchBrandsPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Brand request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<PaginationAPI<Brand>>> call =
                service.searchBrands(Token , request.getName() );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Brand>>> call, retrofit2.Response<Response<PaginationAPI<Brand>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());
        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateSearchBrands((List<Brand>) response.body().getData().getData());
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
    public void onFailure(Call<Response<PaginationAPI<Brand>>> call, Throwable t) {
        t.printStackTrace();
    }

    public interface OnResponseInterface{
        void populateSearchBrands(List<Brand> items);
        void onFailure(List<String> errors);
    }
}
