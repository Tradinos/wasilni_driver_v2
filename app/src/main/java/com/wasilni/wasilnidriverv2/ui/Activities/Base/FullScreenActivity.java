package com.wasilni.wasilnidriverv2.ui.Activities.Base;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.util.UtilFunction;


public class FullScreenActivity extends BasicActivity {
    protected RelativeLayout mainLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        UtilFunction.doExtends(BasicmainLayout , this , R.layout.activity_full_screen);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        UtilFunction.setFontBAHIJ(this);
        mainLayout = findViewById(R.id.relative_layout_full_screen_activity);

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void responseCode200() {

    }
}
