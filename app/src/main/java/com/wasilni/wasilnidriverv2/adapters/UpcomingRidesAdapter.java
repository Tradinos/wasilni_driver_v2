package com.wasilni.wasilnidriverv2.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;

import java.util.List;

public class UpcomingRidesAdapter extends RecyclerView.Adapter<UpcomingRidesAdapter.RideViewHolder> {
    private List<Ride> list ;
    private Activity activity ;
    // Provide a suitable constructor (depends on the kind of dataset)
    public UpcomingRidesAdapter() {
//        mDataset = myDataset;
    }

    public UpcomingRidesAdapter(List<Ride> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public UpcomingRidesAdapter.RideViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v;
        if(viewType == 1)
        {
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pooling_trip_item, parent, false);
        }
        else{
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.trip_item, parent, false);

        }
        RideViewHolder vh = new RideViewHolder(v);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 1)
        {
            return 1;
        }
        else{
            return 0;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RideViewHolder holder, int position) {
//        holder.textView.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class RideViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView startPlace , duration , passengerName ;
        CardView allItem ;
        public RideViewHolder(View v) {
            super(v);
            startPlace = v.findViewById(R.id.start_place);
            duration = v.findViewById(R.id.duration);
            passengerName = v.findViewById(R.id.passenger_name);
            allItem = v.findViewById(R.id.item);
            Log.d("SED", "RideViewHolder: I am doing it the right way right ?");
        }
    }
}
