package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;

import com.tradinos.wasilnidriver.mvp.view.OnOffDriverContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;
import com.tradinos.wasilnidriver.ui.Activities.HomeActivity;

import java.io.IOException;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.Constants.Token;
import static com.tradinos.wasilnidriver.util.UtilFunction.hideProgressBar;
import static com.tradinos.wasilnidriver.util.UtilFunction.showProgressBar;

public class OnOffDriverPresenterImp implements OnOffDriverContract.OnOffDriverPresenter {
    HomeActivity activity ;
    public OnOffDriverPresenterImp(HomeActivity activity) {
        this.activity = activity ;
    }

    @Override
    public void sendToServer(Boolean request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);
        showProgressBar(activity);
        /** Call the method with parameter in the interface to get the notice data*/
        Call<Response<Boolean>> call =
                service.ChangeDriverState(Token);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<Boolean>> call, retrofit2.Response<Response<Boolean>> response) {
        Log.e("onResponse",response.message()+" code :"+response.code());
        hideProgressBar();

        switch(response.code())
        {
            case 200 :
                activity.responseCode200(response.body().getData());
                break;
            case 422 :
                try {
                    activity.responseCode422(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
//        Log.e("onFailure",t.getMessage());
        activity.onFailure(t);
        t.printStackTrace();
        hideProgressBar();

    }

}
