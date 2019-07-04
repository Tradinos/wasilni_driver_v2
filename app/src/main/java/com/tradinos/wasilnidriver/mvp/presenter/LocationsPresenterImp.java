package com.tradinos.wasilnidriver.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.Location;
import com.tradinos.wasilnidriver.mvp.model.pojo.PaginationAPI;
import com.tradinos.wasilnidriver.mvp.view.LocationContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.Constants.Token;

public class LocationsPresenterImp implements LocationContract.LocationsPresenter {

    OnResponseInterface responseInterface ;
    Activity context ;
    public static boolean ischecked = false  ;
    public LocationsPresenterImp(OnResponseInterface _responseInterface , Activity activity) {
        this.responseInterface = _responseInterface ;
        this.context = activity ;
    }

    @Override
    public void sendToServer(Location request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/

        Call<Response<PaginationAPI<Location>>> call =
                service.Locations( Token, request.getName() );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<PaginationAPI<Location>>> call, retrofit2.Response<Response<PaginationAPI<Location>>> response) {
        Log.e("onResponse RideBooking",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                this.responseInterface.populateLocations((List<Location>) response.body().getData().getData());
                break;
            case 422 :
                JSONObject jsonObject = null;
                try {
                    String res = response.errorBody().string()  ;
                    jsonObject = new JSONObject(res);
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
    public void onFailure(Call<Response<PaginationAPI<Location>>> call, Throwable t) {
        t.printStackTrace();

    }

    public interface OnResponseInterface{
        void populateLocations(List<Location> locations);
        void onFailure(List<String> errors);
    }
}
