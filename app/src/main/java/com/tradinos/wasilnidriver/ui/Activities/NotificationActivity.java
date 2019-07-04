package com.tradinos.wasilnidriver.ui.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tradinos.wasilnidriver.R;

import static com.tradinos.wasilnidriver.DriverApplication.updateResources;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this , "ar");
        setContentView(R.layout.activity_notification);
    }
}
