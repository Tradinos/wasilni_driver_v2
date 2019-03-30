package com.wasilni.wasilnidriverv2.ui.Activities;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.databinding.ActivityHomeBinding;
import com.wasilni.wasilnidriverv2.mvp.presenter.HomeActivityPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.util.UtilUser;

public class HomeActivity extends FragmentActivity implements View.OnClickListener , OnMapReadyCallback , HomeContract.HomeView {

    public GoogleMap mMap;
    public ImageView driverStatus;
    public TextView driverStatusTextView ;
    public LinearLayout bottomLinearLayout ;
    public ImageView notificationImageView ;
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
        bottomLinearLayout = findViewById(R.id.bottom_linear_layout) ;
        driverStatusTextView = findViewById(R.id.driver_status);
        notificationImageView = findViewById(R.id.notification_img);
        notificationImageView.bringToFront();



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
        }
    }
}
