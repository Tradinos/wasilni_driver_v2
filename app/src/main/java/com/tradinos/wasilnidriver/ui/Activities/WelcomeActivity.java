package com.tradinos.wasilnidriver.ui.Activities;

import android.os.Bundle;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.ui.Activities.Base.FullScreenActivity;
import com.tradinos.wasilnidriver.util.UtilFunction;

import static com.tradinos.wasilnidriver.DriverApplication.updateResources;

public class WelcomeActivity extends FullScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this , "ar");
        UtilFunction.doExtends(mainLayout , this , R.layout.activity_welcome);
    }
}
