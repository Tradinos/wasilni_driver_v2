package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Nationality;
import com.wasilni.wasilnidriverv2.mvp.view.NationalityContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;

import java.util.List;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class NationalitiesPresenterImp implements NationalityContract.NationalitiesPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public NationalitiesPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Nationality request) {
        Log.d("SAED", "sendToServer: I am requesting data");
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<List<Nationality>>> call =
                service.Nationalities( Token );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<List<Nationality>>> call, retrofit2.Response<Response<List<Nationality>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateNationalities((List<Nationality>) response.body());
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
    public void onFailure(Call<Response<List<Nationality>>> call, Throwable t) {
        t.printStackTrace();
//        Log.d("SAED", t.getStackTrace());
        Log.e("onFailure RideBooking",t.getStackTrace().toString());

    }

    public interface OnResponseInterface{
        void populateNationalities(List<Nationality> items);
        void onFailure(List<String> errors);
    }
}
