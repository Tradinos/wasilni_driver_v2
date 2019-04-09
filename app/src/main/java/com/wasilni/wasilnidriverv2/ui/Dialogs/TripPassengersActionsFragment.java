package com.wasilni.wasilnidriverv2.ui.Dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.util.RideStatus;

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
public class TripPassengersActionsFragment extends BottomSheetFragment {
    private OnFragmentInteractionListener mListener;
    public BookingAdapter mAdapter ;
    public TripPassengersActionsFragment() {
        mAdapter = new BookingAdapter(new ArrayList<Booking>() , this.getActivity());
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TripPassengersActionsFragment.
     */
    public static TripPassengersActionsFragment newInstance() {
        TripPassengersActionsFragment fragment = new TripPassengersActionsFragment();
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

        testTripList();

    }

    private void testTripList(){
        Log.d("SAED", "testTripList: what is going here");
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.trip_passengers_actions);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
       // recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(mAdapter);
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
