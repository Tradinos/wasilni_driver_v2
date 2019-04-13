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
import com.wasilni.wasilnidriverv2.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;
import com.wasilni.wasilnidriverv2.mvp.presenter.CausePresenterImp;
import com.wasilni.wasilnidriverv2.mvp.presenter.GetMyRidesPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.presenter.HomeActivityPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.presenter.RideBookingsPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.RateCauseContract;
import com.wasilni.wasilnidriverv2.adapters.UpcomingRidesAdapter;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.mvp.view.RideContruct;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.BasicActivity;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.NavigationActivity;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripPassengersActionsFragment;
import com.wasilni.wasilnidriverv2.ui.Dialogs.TripSummaryFragment;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.util.ArrayList;
import java.util.List;

import static com.wasilni.wasilnidriverv2.mvp.presenter.RideBookingsPresenterImp.ischecked;

public class HomeActivity extends NavigationActivity implements
        TripPassengersActionsFragment.OnFragmentInteractionListener,
        TripSummaryFragment.OnFragmentInteractionListener ,
        BookingAdapter.OnAdapterInteractionListener,
        View.OnClickListener,
        OnMapReadyCallback,
        HomeContract.HomeView {

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

    public static final int PEEK_HEIGHT_DROP_OFF = 210;
    public static final int PEEK_HEIGHT_PICKED_UP = 150;
    public static final int PEEK_HEIGHT_NORMAL = 200;
    public static HomeActivity homeActivity ;
    public HomeContract.HomeActivityPresenter presenter = new HomeActivityPresenterImp(this);
    public RideContruct.MyRidesPresenter myRidesPresenter ;

    public UpcomingRidesAdapter mAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ischecked = false;
        super.onCreate(savedInstanceState);
        UtilFunction.doExtends(mainLayout , this , R.layout.activity_home);
        homeActivity = this;
        myRidesPresenter = new GetMyRidesPresenterImp(this);
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
        recyclerView = findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new UpcomingRidesAdapter(tripPassengersActionsFragment,this) ;
        recyclerView.setAdapter(mAdapter);

        myRidesPresenter.sendToServer(null);



        // Init bottom sheet
        bottomSheet =  findViewById(R.id.bottomsheet);
        bottomSheet.setInterceptContentTouch(false);
        bottomSheet.setShouldDimContentView(false);
        bottomSheet.setPeekOnDismiss(true);
        bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, PEEK_HEIGHT_NORMAL));

        bottomSheet.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            @Override
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
                passengersActionsBtn.setVisibility(View.VISIBLE);
            }
        });

        notificationButton.setOnClickListener(this);
        driverStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " );
            }
        });
        presenter.checkDriverStatus();
        this.testBottomSheet();


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
                if(!tripPassengersActionsFragment.isAdded()) {
                    this.tripPassengersActionsFragment.show(getSupportFragmentManager(), R.id.bottomsheet);
                }
                if(ischecked) {
                    this.passengersActionsBtn.setVisibility(View.INVISIBLE);
                }
                else
                {
                    UtilFunction.showToast(this, "please select ride");
                }
                break;
        }
    }

    public void changePeekHeight(int height){
        bottomSheet.setPeekSheetTranslation(UtilFunction.convertDpToPx(this, height));

    }

    private void testBottomSheet(){
    }

    private void testTripList(){
        recyclerView = findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        List<Ride> list = new ArrayList();
        list.add(new Ride("mahmoud","مزة",1));
        list.add(new Ride("ahmad","حمرا",1));
        list.add(new Ride("kinan","ركن الدين",1));
        list.add(new Ride("mahmoud","ميدان",3));
//        UpcomingRidesAdapter mAdapter = new UpcomingRidesAdapter(list,this,tripPassengersActionsFragment);
//        recyclerView.setAdapter(mAdapter);
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


}
