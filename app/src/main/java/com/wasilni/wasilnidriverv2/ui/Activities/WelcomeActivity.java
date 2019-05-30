package com.wasilni.wasilnidriverv2.ui.Activities;

import android.os.Bundle;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.FullScreenActivity;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

public class WelcomeActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this , "ar");
        UtilFunction.doExtends(mainLayout , this , R.layout.activity_welcome);
    }
}
