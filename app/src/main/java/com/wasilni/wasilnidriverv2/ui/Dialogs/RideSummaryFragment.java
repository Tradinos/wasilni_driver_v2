package com.wasilni.wasilnidriverv2.ui.Dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Payment;
import com.wasilni.wasilnidriverv2.mvp.presenter.PayPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.PayContract;
import com.wasilni.wasilnidriverv2.mvp.view.RideSummaryContract;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.RideStatus;

import java.util.List;

/**
 * A simple {@link BottomSheetDialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RideSummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RideSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")

public class RideSummaryFragment extends BottomSheetDialogFragment implements RideSummaryContract.RideSummaryView {
    public ImageView passengerPictureIV,stationImageView,time_imgImageView;
    public TextView rateBtn ,trip_wait_duration,trip_duration, trip_length,trip_source,trip_destination,calculated_cost,booking_cost;
    public Button submit ;
    public EditText moneyCost ;
    public OnFragmentInteractionListener mListener;
    public Booking  sendedBooking;
    public View view ;
    public HomeActivity activity ;
    public TripPassengersActionsFragment tripPassengersActionsFragment ;


    public RideSummaryFragment(HomeActivity activity) {
        this.activity = activity ;

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RideSummaryFragment.
     */
    public static RideSummaryFragment newInstance(HomeActivity activity) {
        RideSummaryFragment fragment = new RideSummaryFragment(activity);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        this.setCancelable(false);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initView();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void initView() {
        this.passengerPictureIV = view.findViewById(R.id.passenger_photo);
        this.rateBtn = view.findViewById(R.id.rate_btn);
        this.submit = view.findViewById(R.id.done_btn);
        this.trip_wait_duration = view.findViewById(R.id.trip_wait_duration);
        this.trip_source = view.findViewById(R.id.trip_source );
        this.trip_destination = view.findViewById(R.id.trip_destination);
        this.moneyCost = view.findViewById(R.id.delivered_money);
        this.time_imgImageView = view.findViewById(R.id.time_img) ;
        this.stationImageView = view.findViewById(R.id.station) ;
        this.trip_duration = view.findViewById(R.id.trip_duration);
        this.trip_length= view.findViewById(R.id.trip_length);
        this.calculated_cost= view.findViewById(R.id.calculated_cost);
        this.booking_cost= view.findViewById(R.id.booking_cost);
        final PayContract.PayPresenter presenter = new PayPresenterImp(this);
        if(sendedBooking != null){
            this.trip_wait_duration.setText(""+sendedBooking.getSummary().getWaiting_time_count());
            this.trip_source.setText(sendedBooking.getPickUpName());
            this.calculated_cost.setText(sendedBooking.getTo_pay());
            this.booking_cost.setText(sendedBooking.getPrice());
            this.trip_destination.setText(sendedBooking.getPullDownName());

            if(sendedBooking.getSummary() == null){
                this.time_imgImageView.setVisibility(View.GONE);
                this.stationImageView.setVisibility(View.GONE);
                this.trip_duration.setVisibility(View.GONE);
                this.trip_length.setVisibility(View.GONE);

            }else{
                this.trip_duration.setText(""+sendedBooking.getSummary().getBooking_time());
                this.trip_length.setText(""+sendedBooking.getSummary().getKm_count());
            }
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendToServer(new Payment(sendedBooking , moneyCost.getText().toString()));


            }
        });

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void responseCode200(Booking response ,TripPassengersActionsFragment tripPassengersActionsFragment ) {
        sendedBooking = response;
        this.tripPassengersActionsFragment = tripPassengersActionsFragment;
        show(((FragmentActivity)activity).getSupportFragmentManager(),"123");
        Log.e("TAG", "responseCode200: "+response.getIs_pooling() );
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog d = super.onCreateDialog(savedInstanceState);
        d.setContentView(R.layout.fragment_trip_summary);
        // ... do stuff....
        Log.e("123", "onCreateDialog: " );
         onViewCreated(d.findViewById(R.id.frame), savedInstanceState);
         return d;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
