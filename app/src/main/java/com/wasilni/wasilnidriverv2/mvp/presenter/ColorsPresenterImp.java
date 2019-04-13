package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.view.ColorContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class ColorsPresenterImp implements ColorContract.ColorsPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public ColorsPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Color request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<List<Color>>> call =
                service.Colors( Token );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<List<Color>>> call, retrofit2.Response<Response<List<Color>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateColors((List<Color>) response.body());
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
    public void onFailure(Call<Response<List<Color>>> call, Throwable t) {

    }

    public interface OnResponseInterface{
        void populateColors(List<Color> colors);
        void onFailure(List<String> errors);
    }
}
