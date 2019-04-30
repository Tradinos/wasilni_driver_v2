package com.wasilni.wasilnidriverv2.ui.Activities.Base;

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
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.gms.maps.model.LatLng;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.view.NavigationContract;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.ui.Activities.RegistrationActivity;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

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

        } else if (id == R.id.weekly) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.notification) {

        } else if (id == R.id.logout) {

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
        UtilFunction.showToast(this,message);
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

        SharedPreferenceUtils.getEditorInstance(getApplicationContext()).clear();
        SharedPreferenceUtils.getEditorInstance(getApplicationContext()).commit();
        Intent intent = new Intent(this, RegistrationActivity.class);
        this.startActivity(intent);
        ActivityCompat.finishAffinity(this);
    }

}
