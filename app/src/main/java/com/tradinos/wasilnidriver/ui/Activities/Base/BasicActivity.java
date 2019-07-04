package com.tradinos.wasilnidriver.ui.Activities.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.view.MainContract;
import com.tradinos.wasilnidriver.util.UtilFunction;


public abstract class BasicActivity extends AppCompatActivity implements MainContract {
    protected ProgressBar progressBar ;
    protected RelativeLayout BasicmainLayout , grayLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_basic);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        BasicmainLayout = findViewById(R.id.basic_RL);
        grayLayout = findViewById(R.id.gray_RL);
        progressBar = initProgressBar();
    }

    @Override
    public ProgressBar initProgressBar() {
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(250,
                250);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        BasicmainLayout.addView(progressBar, params);
        progressBar.setVisibility(View.GONE);
        return progressBar;
    }

    public void showProgressBar(Activity activity) {
        grayLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        grayLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void responseCode422(String message) {
        UtilFunction.showToast(this, message);
    }

    @Override
    public void responseCode500() {
        UtilFunction.showToast(this, R.string.error_500);
    }

    @Override
    public void responseCode400() {
        UtilFunction.showToast(this, R.string.error_400);
    }

    @Override
    public void responseCode401() {
        UtilFunction.showToast(this, R.string.error_401);
    }
}
