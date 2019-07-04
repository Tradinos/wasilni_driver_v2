package com.tradinos.wasilnidriver.mvp.presenter;

import android.util.Log;
import android.view.View;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.ui.adapters.UpcomingRidesAdapter;
import com.tradinos.wasilnidriver.mvp.model.Ride;
import com.tradinos.wasilnidriver.mvp.view.AdapterContract;
import com.tradinos.wasilnidriver.mvp.view.RideContruct;
import com.tradinos.wasilnidriver.ui.Dialogs.TripPassengersActionsFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpComingRidesAdapterPresenterImp implements AdapterContract.AdapterPresenter<Ride, UpcomingRidesAdapter.RideViewHolder> {
    private TripPassengersActionsFragment fragment;
    RideContruct.RideBookingsPresenter presenter;

    public UpComingRidesAdapterPresenterImp(TripPassengersActionsFragment fragment) {
        this.fragment = fragment;
        presenter = new RideBookingsPresenterImp(fragment);
    }

    @Override
    public void ObjectToHolder(final Ride object, int position, UpcomingRidesAdapter.RideViewHolder holder) {
        object.setNumber(position);

        holder.allItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendToServer(object);
//                    fragment.activity.passengersActionsBtn.setVisibility(View.VISIBLE);
            }
        });

        if (position == 0) {

            Log.e("ObjectToHolder: ", "1233");

            presenter.sendToServer(object);
            Log.e("ObjectToHolder: ", "8888" + object.getId());
            fragment.activity.passengersActionsBtn.setVisibility(View.VISIBLE);
        }
        Log.e( "ObjectToHolder: ",object.getStart_datetime() );
//        String aux = new SimpleDateFormat("hh:mm a").format(object.getStart_datetime());
//        holder.duration.setText(aux);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm aa");
        Date dt;
        try {
            dt = sdf.parse(object.getStart_datetime());
            holder.duration.setText(sdfs.format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.startPlace.setText(object.getPick_up_location_name());
        if (object.getBookings_count() == 1) {
            holder.passengerName.setText(object.getPassenger_name());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item: {
                // change bottom sheet adapter data ;

                break;
            }
        }
    }
}
