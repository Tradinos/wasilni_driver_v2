package com.tradinos.wasilnidriver.ui.Activities.Base;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tradinos.wasilnidriver.BuildConfig;
import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.view.NavigationContract;
import com.tradinos.wasilnidriver.ui.Activities.DailyReportActivity;
import com.tradinos.wasilnidriver.ui.Activities.RegistrationActivity;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UserUtil;
import com.tradinos.wasilnidriver.util.UtilFunction;

import org.json.JSONObject;

import static com.tradinos.wasilnidriver.util.UtilFunction.p;

public abstract class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        NavigationContract.NavigationView {
    protected DrawerLayout mainLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //UtilFunction.doExtends(BasicmainLayout , this ,R.layout.activity_navigation);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_navigation);
        initNavigationView();
    }

    @Override
    public void initNavigationView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mainLayout =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mainLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView version = headerView.findViewById(android.R.id.wasilni_version) ;
        String versionName = BuildConfig.VERSION_NAME;
        version.setText("Wasilni Version "+versionName);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {

        } else if (id == R.id.schedule) {

        } else if (id == R.id.dailly) {
            startActivity(new Intent(this , DailyReportActivity.class));
        } else if (id == R.id.weekly) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.notification) {

        } else if (id == R.id.logout) {
            UserUtil.getUserInstance().setChecked(false);
            UserUtil.getUserInstance().setAccessToken(null);
            SharedPreferenceUtils.getEditorInstance(this).remove("auth_token");
            SharedPreferenceUtils.getEditorInstance(this).commit();
            startActivity(new Intent(this , RegistrationActivity.class));
            ActivityCompat.finishAffinity(this);
        }

//        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public ProgressBar initProgressBar() {
        return null;
    }

    @Override
    public void responseCode422(String message) {
        JSONObject jsonObject = null;
        try {
            String res = message  ;
            jsonObject = new JSONObject(res);
            String userMessage = jsonObject.getString("message");
            UtilFunction.showToast(this , userMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

        SharedPreferenceUtils.getEditorInstance(getApplicationContext()).remove("auth_token");
        SharedPreferenceUtils.getEditorInstance(getApplicationContext()).commit();
        Intent intent = new Intent(this, RegistrationActivity.class);
        this.startActivity(intent);
        ActivityCompat.finishAffinity(this);
    }

}
