package com.wasilni.wasilnidriverv2.ui.Activities;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.presenter.CausePresenterImp;
import com.wasilni.wasilnidriverv2.mvp.presenter.HomeActivityPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.RateCauseContract;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.util.UtilUser;

public class HomeActivity extends FragmentActivity implements View.OnClickListener , OnMapReadyCallback , HomeContract.HomeView {

    public GoogleMap mMap;
    public ImageView driverStatus;
    public TextView driverStatusTextView ;
    public LinearLayout onlineOfflineLayout ;
    public LinearLayout bottomLayout ;
    public ImageView notificationImageView ;
    public ConstraintLayout newOrderLayout ;
    public Button notificationButton ;
    public HomeContract.HomeActivityPresenter presenter = new HomeActivityPresenterImp(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    public void initView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        driverStatus = findViewById(R.id.driver_status_image) ;
        onlineOfflineLayout = findViewById(R.id.bottom_linear_layout) ;
        driverStatusTextView = findViewById(R.id.driver_status);
        notificationImageView = findViewById(R.id.notification_img);
        notificationImageView.bringToFront();
        newOrderLayout = findViewById(R.id.new_order_layout) ;
        bottomLayout = findViewById(R.id.bottom_layout) ;
        notificationButton = findViewById(R.id.saw);

        notificationButton.setOnClickListener(this);
        driverStatus.setOnClickListener(this);
        if(!UtilUser.getUserInstance().isChecked()){
            driverStatus.setImageResource(R.mipmap.power_off);
            driverStatusTextView.setText("You're offline");
        }
        else
        {
            driverStatus.setImageResource(R.mipmap.power_on);
            driverStatusTextView.setText("You're online");
        }


        RateCauseContract.CausePresenter presenter = new CausePresenterImp(this);
        presenter.sendToServer(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.regesterRecivers();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unregesterRecivers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.driver_status_image :
                presenter.driverStatusOnclick();
                break;
            case R.id.saw :
                presenter.notificationButotnOnclick();
                break;

        }
    }
}
