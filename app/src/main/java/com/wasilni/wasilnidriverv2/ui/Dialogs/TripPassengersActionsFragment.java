package com.wasilni.wasilnidriverv2.ui.Dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.ui.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.BookingsFragmentContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TripPassengersActionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TripPassengersActionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class TripPassengersActionsFragment extends BottomSheetFragment implements BookingsFragmentContract.BookingFragmentView {
    private OnFragmentInteractionListener mListener;
    public BookingAdapter mAdapter ;
    public HomeActivity activity ;
    public TripPassengersActionsFragment(HomeActivity activity) {
        this.activity = activity ;
        mAdapter = new BookingAdapter(new ArrayList<Booking>() , this);
        // Required empty public constructor
    }
    public static boolean ischecked = false  ;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TripPassengersActionsFragment.
     */
    public static TripPassengersActionsFragment newInstance(HomeActivity activity) {
        TripPassengersActionsFragment fragment = new TripPassengersActionsFragment(activity);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_passengers_actions, container, false);
    }

    @Override
    public void onViewCreated(View view,  @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        int flags = this.getActivity().getWindow().getAttributes().flags;
        Log.d("SAED", "onViewCreated: flags babye" + flags);

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
    public void updateListList() {
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void deleteBooking(Booking data) {
        List<Booking> list = mAdapter.getList();
        Booking deletedItem = null ;
        for (Booking booking : list){
            if(booking.getId().equals(data.getId())){
                deletedItem = booking ;
            }
        }
        Log.e("deleteBooking", "deleteBooking: "+deletedItem );
        list.remove(deletedItem);
        mAdapter.setList(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        RecyclerView recyclerView =  getView().findViewById(R.id.trip_passengers_actions);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setBookings(Ride ride) {

        ischecked = true;
        if(!this.isAdded()) {
            this.show(((FragmentActivity) activity).getSupportFragmentManager(), R.id.bottomsheet);
        }
        mAdapter.setList(ride.getBookings());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailuer() {

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
