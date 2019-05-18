package com.wasilni.wasilnidriverv2.ui.Dialogs;

import android.annotation.SuppressLint;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.ui.adapters.BookingAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.view.BookingsFragmentContract;
import com.wasilni.wasilnidriverv2.util.UserUtil;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.util.ArrayList;
import java.util.List;

import static com.wasilni.wasilnidriverv2.util.Constants.DAMASCUSE;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.getLatLng;
import static com.wasilni.wasilnidriverv2.util.UtilFunction.showToast;

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
    public Ride  ride ;
    public HomeActivity activity ;
    public  List<Marker>  markers =new ArrayList<>();
    public List<MarkerOptions>markerOptionsList= new ArrayList<>();
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
    public void updateListList(Booking mBooking) {
        initMarker(ride);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void deleteBooking(Booking data) {
        List<Booking> list = mAdapter.getList();
        Booking deletedItem = null ;
        for (Booking booking : list){
            if(booking.getId().equals(data.getId())){
                deletedItem = booking ;
                break;
            }
        }
        list.remove(deletedItem);

        if(list.size() == 0){
            Ride deletedRide= null ;
            List<Ride> rides = activity.mAdapter.getList();
            for (Ride ride : rides){
                if(ride.getId() == deletedItem.getRideId()){
                    deletedRide = ride ;
                    break;
                }
            }
            rides.remove(deletedRide);
            activity.mAdapter.setList(rides);
            activity.mAdapter.notifyDataSetChanged();
        }

        mAdapter.setList(list);
        Log.e( "deleteBooking: ", "notifyDataSetChanged");
        mAdapter.notifyDataSetChanged();
        initMarker(ride);
    }

    @Override
    public void initView() {
        RecyclerView recyclerView =  getView().findViewById(R.id.trip_passengers_actions);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        try {
            if (UserUtil.getUserInstance().getLocation() != null) {
                List<Booking> list = mAdapter.getList();
                LatLng origin = UserUtil.getUserInstance().getLatLan();
                LatLng dest;

                // Getting URL to the Google Directions API
                if (activity.markerPoints == null)
                    activity.markerPoints = new ArrayList<LatLng>();
                Log.e("initView: ", ""+list.size());
                activity.markerPoints.clear();
                activity.markerPoints.add(UserUtil.getUserInstance().getLatLan());
                for (Booking booking : list) {
                    if (booking.getStatus().equals("PICKED_UP")) {
                        activity.markerPoints.add(getLatLng(booking.getPullDownLocation().getCoordinates()));
                    }else {
                        activity.markerPoints.add(getLatLng(booking.getPickUpLocation().getCoordinates()));
                    }
                }
                dest = activity.markerPoints.get(1);
                String url = activity.getDirectionsUrl(origin, dest);
                Log.e("initView: ", url);
                HomeActivity.DownloadTask downloadTask = new HomeActivity.DownloadTask();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);
            }
        }catch (Exception e){
            showToast(activity,"1");
        }
    }
    @Override
    public void initMarker(Ride ride){
        activity.mMap.clear();
        for(Marker marker : markers){
            marker.remove();
        }
        markers.clear();
        markerOptionsList.clear();
        for(Booking booking : ride.getBookings()){
            if(booking.getStatus().equals("DONE")) {
                continue;
            }

            MarkerOptions markerOptions = new MarkerOptions();
            if(booking.getStatus().equals("PICKED_UP")){
                markerOptions.position(getLatLng(booking.getPullDownLocation().getCoordinates()));
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }else{
                if(booking.getStatus().equals("DONE")) {
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                }else{
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
                }
                markerOptions.position(getLatLng(booking.getPickUpLocation().getCoordinates()));
            }
            markerOptions.title(booking.getName());
            Marker locationMarker = activity.mMap.addMarker(markerOptions);
            locationMarker.showInfoWindow();

            markerOptionsList.add(markerOptions);
            markers.add(locationMarker);
        }
    }

    @Override
    public void setBookings(Ride ride) {
        this.ride = ride;

        initMarker(ride);

        ischecked = true;
        try {
            if (!this.isAdded()) {
                this.show(((FragmentActivity) activity).getSupportFragmentManager(), R.id.bottomsheet);
            } else {
                this.dismiss();
                this.show(((FragmentActivity) activity).getSupportFragmentManager(), R.id.bottomsheet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mAdapter.setList(ride.getBookings());
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

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
