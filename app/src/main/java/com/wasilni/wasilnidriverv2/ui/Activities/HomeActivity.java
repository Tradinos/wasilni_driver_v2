package com.wasilni.wasilnidriverv2.ui.Activities;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.presenter.CausePresenterImp;
import com.wasilni.wasilnidriverv2.mvp.presenter.HomeActivityPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.RateCauseContract;
import com.wasilni.wasilnidriverv2.adapters.TripsAdapter;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.ui.Dialogs.DropOffPassengerFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.GettingPassengerFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.PickedUpPassengerFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.PickingUpPassengerFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.util.UtilFunction;
import com.wasilni.wasilnidriverv2.util.UtilUser;

public class HomeActivity extends FragmentActivity implements
        PickingUpPassengerFragment.OnFragmentInteractionListener,
        PickedUpPassengerFragment.OnFragmentInteractionListener,
        GettingPassengerFragment.OnFragmentInteractionListener,
        DropOffPassengerFragment.OnFragmentInteractionListener,
        TripPassengersActionsFragment.OnFragmentInteractionListener,
        View.OnClickListener,
        OnMapReadyCallback,
        HomeContract.HomeView {

    public GoogleMap mMap;
    public ImageView driverStatus;
    public TextView driverStatusTextView ;
    public LinearLayout onlineOfflineLayout ;
    public LinearLayout bottomLayout ;
    public ImageView notificationImageView ;
    public ConstraintLayout newOrderLayout ;
    public Button notificationButton ;
    public ImageButton passengersActionsBtn;
    public TripPassengersActionsFragment tripPassengersActionsFragment = TripPassengersActionsFragment.newInstance();
    private BottomSheetLayout bottomSheet;

    public HomeContract.HomeActivityPresenter presenter = new HomeActivityPresenterImp(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.testTripList();

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
        passengersActionsBtn = findViewById(R.id.passenger_actions_btn);


        // Init bottom sheet
        bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheet.setInterceptContentTouch(false);
        bottomSheet.setShouldDimContentView(false);
        bottomSheet.setPeekOnDismiss(true);
        bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this,200));

        bottomSheet.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            @Override
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
                passengersActionsBtn.setVisibility(View.VISIBLE);
            }
        });

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

        this.testBottomSheet();


        RateCauseContract.CausePresenter presenter = new CausePresenterImp(this);
        presenter.sendToServer(null);
        passengersActionsBtn.setOnClickListener(this);
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
            case R.id.passenger_actions_btn:
                this.tripPassengersActionsFragment.show(getSupportFragmentManager(), R.id.bottomsheet);
                this.passengersActionsBtn.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void testBottomSheet(){
    }

    private void testTripList(){
        Log.d("SAED", "testTripList: what is going here");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        TripsAdapter mAdapter = new TripsAdapter();
        recyclerView.setAdapter(mAdapter);
    }
}
