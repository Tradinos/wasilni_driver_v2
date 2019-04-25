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

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class BrandsPresenterImp implements BrandContract.BrandsPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public BrandsPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Brand request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        UtilFunction.showProgressBar(context);
        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<PaginationAPI<Brand>>> call =
                service.Brands( Token );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Brand>>> call, retrofit2.Response<Response<PaginationAPI<Brand>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());
        UtilFunction.hideProgressBar();
        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateBrands((List<Brand>) response.body().getData().getData());
                break;
            case 422 :
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
        UtilFunction.hideProgressBar();
    }

    public interface OnResponseInterface{
        void populateBrands(List<Brand> items);
        void onFailure(List<String> errors);
    }
}
