package com.wasilni.wasilnidriverv2.mvp.presenter;

import android.util.Log;

import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.mvp.view.OnOffDriverContract;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;

import retrofit2.Call;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class OnOffDriverPresenterImp implements OnOffDriverContract.OnOffDriverPresenter {
    HomeActivity activity ;
    public OnOffDriverPresenterImp(HomeActivity activity) {
        this.activity = activity ;
    }

    @Override
    public void sendToServer(Boolean request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<Response<Boolean>> call =
                service.ChangeDriverState(Token);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Boolean>> call, retrofit2.Response<Response<Boolean>> response) {
        Log.e("onResponse",response.message()+" code :"+response.code());

        switch(response.code())
        {
            case 200 :
                activity.responseCode200(response.body().getData());
                break;
            case 422 :
                activity.responseCode422();
                break;
            case 500 :
                activity.responseCode500();
                break;
            case 400:
                activity.responseCode400();
                break;
            case 401:
                activity.responseCode401();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<Boolean>> call, Throwable t) {
        Log.e("onFailure",t.getMessage());
        activity.onFailure(t);
    }

}
