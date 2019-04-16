package com.wasilni.wasilnidriverv2.ui.Activities.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.view.MainContract;


public abstract class BasicActivity extends AppCompatActivity implements MainContract {
    protected ProgressBar progressBar ;
    protected RelativeLayout BasicmainLayout , grayLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public void showProgressBar(Activity activity) {
        grayLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        grayLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void responseCode422() {

    }

    @Override
    public void responseCode500() {

    }

    @Override
    public void responseCode400() {

    }

    @Override
    public void responseCode401() {

    }
}
