package com.wasilni.wasilnidriverv2.ui.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.presenter.GetUserDataPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.UserData;
import com.wasilni.wasilnidriverv2.ui.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.presenter.GetMyRidesPresenterImp;
import com.wasilni.wasilnidriverv2.ui.adapters.UpcomingRidesAdapter;
import com.wasilni.wasilnidriverv2.mvp.presenter.OnOffDriverPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.mvp.view.OnOffDriverContract;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.receivers.NotificationReceiver;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.NavigationActivity;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.RideSummaryFragment;
import com.wasilni.wasilnidriverv2.gps.GpsUtils;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UtilFunction;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment.ischecked;
import static com.wasilni.wasilnidriverv2.util.Constants.DAMASCUSE;
import static com.wasilni.wasilnidriverv2.util.Constants.GPS_REQUEST;
import static com.wasilni.wasilnidriverv2.util.Constants.Token;
import static com.wasilni.wasilnidriverv2.util.Constants.ZOOM1;
import static com.wasilni.wasilnidriverv2.util.Constants.ZOOM2;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.REQUEST_CHECK_SETTINGS;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.bitmapDescriptorFromVector;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.settingsRequest;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.updatePlayService;

public class HomeActivity extends NavigationActivity implements
        TripPassengersActionsFragment.OnFragmentInteractionListener,
        RideSummaryFragment.OnFragmentInteractionListener ,
        BookingAdapter.OnAdapterInteractionListener,
        View.OnClickListener,
        OnMapReadyCallback,
        HomeContract.HomeView {
    public Marker myMarker ;
    public MarkerOptions myMarkerOptions;
    public GoogleMap mMap;
    public RecyclerView recyclerView;
    public ImageView driverStatus;
    public TextView driverStatusTextView ;
    public LinearLayout onlineOfflineLayout ;
    public LinearLayout bottomLayout ;
    public ImageView notificationImageView ;
    public ConstraintLayout newOrderLayout ;
    public Button notificationButton ;
    public ImageButton passengersActionsBtn;
    public TripPassengersActionsFragment tripPassengersActionsFragment = TripPassengersActionsFragment.newInstance(this);
    public BottomSheetLayout bottomSheet;

    private NotificationReceiver notificationReceiver = new NotificationReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            recreate();
        }
    };


    public static final int PEEK_HEIGHT_DROP_OFF = 210;
    public static final int PEEK_HEIGHT_PICKED_UP = 150;
    public static final int PEEK_HEIGHT_NORMAL = 200;
    public static HomeActivity homeActivity ;


    private RideContruct.MyRidesPresenter myRidesPresenter ;
    private OnOffDriverContract.OnOffDriverPresenter onOffDriverPresenter = new OnOffDriverPresenterImp(this);
    private UserData.GetUserData userDataPresenter = new GetUserDataPresenterImp(this);
    public UpcomingRidesAdapter mAdapter ;

    @Override
    public void setRides(List<Ride> data) {
        mAdapter.setList(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ischecked = false;
        super.onCreate(savedInstanceState);
        UtilFunction.doExtends(mainLayout , this , R.layout.activity_home);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        moveCamera(DAMASCUSE);
    }

    @Override
    public void initView() {
        updatePlayService(this);
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
        passengersActionsBtn = findViewById(R.id.passenger_actions_btn);
        recyclerView = findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new UpcomingRidesAdapter(tripPassengersActionsFragment) ;
        recyclerView.setAdapter(mAdapter);




        // Init bottom sheet
        bottomSheet =  findViewById(R.id.bottomsheet);
        bottomSheet.setInterceptContentTouch(false);
        bottomSheet.setShouldDimContentView(false);
        bottomSheet.setPeekOnDismiss(true);
        bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_NORMAL));

        bottomSheet.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            @Override
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
//                passengersActionsBtn.setVisibility(View.VISIBLE);
            }
        });

        driverStatus.setOnClickListener(this);
        passengersActionsBtn.setOnClickListener(this);


        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        Log.e("home", "onPermissionsChecked: true" );
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Log.e("home", "onPermissionsChecked: false" );

                    }
                }).check();



        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                Log.e( "gpsStatus: ",""+isGPSEnable );
            }
        });
        addDriverLocationToMap();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTokenFromLocalData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        homeActivity = this;
        myRidesPresenter = new GetMyRidesPresenterImp(this);
        initView();
        regesterRecivers();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregesterRecivers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.driver_status_image :
                driverStatusOnclick();
                break;
            case R.id.passenger_actions_btn:
                if(!tripPassengersActionsFragment.isAdded()) {
                    this.tripPassengersActionsFragment.show(getSupportFragmentManager(), R.id.bottomsheet);
                }else{
                    this.tripPassengersActionsFragment.dismiss();
                    this.tripPassengersActionsFragment.show(getSupportFragmentManager(), R.id.bottomsheet);

                }
//                if(ischecked) {
//                    this.passengersActionsBtn.setVisibility(View.INVISIBLE);
//                }
//                else
//                {
//                    UtilFunction.showToast(this, "please select ride");
//                }
                break;
        }
    }

    public void changePeekHeight(int height){
        bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, height));

    }


    @Override
    public void itemChanged(String status) {
        switch (status){
            case "APPROVED" :{
                bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_NORMAL));
                break;
            }
            case "STARTED" :{
                bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_NORMAL));
                break;
            }
            case "ARRIVED" :{
                bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_PICKED_UP));
                break;
            }
            case "PICKED_UP" :{
                bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_DROP_OFF));
                break;
            }
            case "DONE" :{
                bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_NORMAL));
                break;
            }
        }
        this.bottomSheet.peekSheet();
    }

    @Override
    public void driverStatusOnclick() {
        onOffDriverPresenter.sendToServer(null);
    }


    @Override
    public void regesterNotification() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wasilni.wasilnidriverv2.receivers");
        registerReceiver(notificationReceiver , intentFilter) ;
    }

    @Override
    public void regesterRecivers() {
        regesterNotification();
    }

    @Override
    public void unregesterNotification() {
        unregisterReceiver(notificationReceiver); ;

    }

    @Override
    public void unregesterRecivers() {
        unregesterNotification();
    }

    @Override
    public void checkDriverStatus() {
        Log.e("checkDriverStatus",""+ UserUtil.getUserInstance().isChecked() );
        if(!UserUtil.getUserInstance().isChecked()){
            UserUtil.getUserInstance().setChecked(false);
            driverStatus.setImageResource(R.mipmap.power_off);
            driverStatusTextView.setText(R.string.you_are_offline);
            ViewAnimator
                    .animate(bottomLayout)
                    .translationY(onlineOfflineLayout.getHeight() , 0)
                    .duration(1000)
                    .start();

        }
        else
        {
            myRidesPresenter.sendToServer(null);
            UserUtil.getUserInstance().setChecked(true);
            driverStatus.setImageResource(R.mipmap.power_on);
            driverStatusTextView.setText(R.string.you_are_online);
            ViewAnimator
                    .animate(bottomLayout)
                    .translationY(0 ,onlineOfflineLayout.getHeight() )
                    .duration(1000)
                    .start();
        }


    }

    @Override
    public void setDriverStatus(User user) {
        if(user.getLast_check() == 1) {
            user.setChecked(true);
        }
        else{
            user.setChecked(false);
        }
        UserUtil.setUser(user);
        UserUtil.getUserInstance().setAccessToken(SharedPreferenceUtils.getPreferencesInstance(getApplicationContext()).getString("auth_token",null));
        Token = UserUtil.getUserInstance().getAccessToken();
        Log.e("checkDriverStatus",""+user.isChecked() );
        checkDriverStatus();
    }

    @Override
    public void addDriverLocationToMap(){
        final Activity activity = this;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if( UserUtil.getUserInstance().getLocation()!=null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(myMarker == null){
                                myMarkerOptions = new MarkerOptions();
                                myMarkerOptions.title(getResources().getString(R.string.me));
                                LatLng latLng = new LatLng(UserUtil.getUserInstance().getLocation().getLatitude() ,UserUtil.getUserInstance().getLocation().getLongitude());
                                myMarkerOptions.position(latLng);
                                myMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                                myMarker = mMap.addMarker(myMarkerOptions);
                            }

                            LatLng latLng = new LatLng(UserUtil.getUserInstance().getLocation().getLatitude() ,UserUtil.getUserInstance().getLocation().getLongitude());
                            myMarkerOptions.position(latLng);
                            myMarker.remove();
                            myMarker = mMap.addMarker(myMarkerOptions);
                        }
                    });
                }
            }
        },0,10000);
    }

    @Override
    public void getTokenFromLocalData() {
        UserUtil.getUserInstance().setAccessToken(SharedPreferenceUtils.getPreferencesInstance(getApplicationContext()).getString("auth_token",null));
        Token = UserUtil.getUserInstance().getAccessToken();
        userDataPresenter.sendToServer(null);
    }

    @Override
    public void onFailure(Throwable t) {
        UtilFunction.showToast(this,"onFailure : "+t.getMessage());

    }

    @Override
    public void responseCode200(Boolean response) {
        if(!response){
            UserUtil.getUserInstance().setChecked(false);
            driverStatus.setImageResource(R.mipmap.power_off);
            driverStatusTextView.setText(R.string.you_are_offline);
            mMap.clear();
            passengersActionsBtn.setVisibility(View.INVISIBLE);
            if(bottomLayout.isShown()){
                bottomSheet.dismissSheet();
            }
            if(tripPassengersActionsFragment.isVisible()){
                tripPassengersActionsFragment.setMenuVisibility(false);
            }
            recyclerView.setVisibility(View.INVISIBLE);
            UserUtil.getUserInstance().setChecked(false);
            ViewAnimator
                    .animate(bottomLayout)
                    .translationY(onlineOfflineLayout.getHeight() , 0)
                    .duration(1000)
                    .start();

        }
        else
        {
            myRidesPresenter.sendToServer(null);
            UserUtil.getUserInstance().setChecked(true);
            driverStatus.setImageResource(R.mipmap.power_on);
            driverStatusTextView.setText(R.string.you_are_online);
            recyclerView.setVisibility(View.VISIBLE);
            UserUtil.getUserInstance().setChecked(true);
            ViewAnimator
                    .animate(bottomLayout)
                    .translationY(0 ,onlineOfflineLayout.getHeight() )
                    .duration(1000)
                    .start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        if (requestCode == GPS_REQUEST) {

                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        settingsRequest(this);
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    public void moveCamera(LatLng latLng){
        if(latLng == DAMASCUSE) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM1));
        }
        else{
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM2));
        }
    }
}
