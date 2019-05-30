package com.wasilni.wasilnidriverv2.ui.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wasilni.wasilnidriverv2.R;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this , "ar");
        setContentView(R.layout.activity_notification);
    }
}
