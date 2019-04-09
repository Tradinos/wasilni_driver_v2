package com.wasilni.wasilnidriverv2;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;

import retrofit2.Call;
import retrofit2.Callback;

import static com.wasilni.wasilnidriverv2.util.Constants.Token;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM_Token", "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

        Call<com.wasilni.wasilnidriverv2.network.Response> call = service.FCMToken(Token, refreshedToken);

        call.enqueue(new Callback<com.wasilni.wasilnidriverv2.network.Response>() {
            @Override
            public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response> call, retrofit2.Response<com.wasilni.wasilnidriverv2.network.Response> response) {
                Log.e("onResponse", "11111 " );

            }

            @Override
            public void onFailure(Call<com.wasilni.wasilnidriverv2.network.Response> call, Throwable t) {
                Log.e("onFailure", t.getMessage());

            }
        });
    }
}
