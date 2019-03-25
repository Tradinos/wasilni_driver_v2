package com.wasilni.wasilnidriverv2.ui.Activities;

import android.os.Bundle;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.FullScreenActivity;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

public class WelcomeActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilFunction.doExtends(mainLayout , this , R.layout.activity_welcome);
    }
}
