package com.tradinos.wasilnidriver;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;

import static com.tradinos.wasilnidriver.util.UtilFunction.CheckFCMToken;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM_Token", "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        SharedPreferenceUtils.getEditorInstance(getApplicationContext())
                .putString("fcm_token", refreshedToken);
        SharedPreferenceUtils.getEditorInstance(getApplicationContext()).commit();
        CheckFCMToken(getApplicationContext());
    }

}
