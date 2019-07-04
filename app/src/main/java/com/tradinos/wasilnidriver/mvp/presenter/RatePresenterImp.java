package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;

import com.tradinos.wasilnidriver.mvp.model.BookingCause;
import com.tradinos.wasilnidriver.mvp.view.RateCauseContract;
import com.tradinos.wasilnidriver.network.ApiServiceInterface;
import com.tradinos.wasilnidriver.network.Response;
import com.tradinos.wasilnidriver.network.RetorfitSingelton;

import retrofit2.Call;

import static com.tradinos.wasilnidriver.util.Constants.Token;

public class RatePresenterImp implements RateCauseContract.RatePresenter {
    @Override
    public void sendToServer(BookingCause request) {
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<Response<BookingCause>> call =
                service.Rate(Token , request.getBook_id() , request.getCause().getId() , request.getCause().getCause());

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Response<BookingCause>> call, retrofit2.Response<Response<BookingCause>> response) {
        Log.e("onResponse",response.message()+" code :"+response.code());

        switch (response.code())
        {
            case 200 :
                break;
            case 422 :
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
    public void onFailure(Call<Response<BookingCause>> call, Throwable t) {
//        Log.e("onFailure",t.getMessage());
        t.printStackTrace();
    }
}
