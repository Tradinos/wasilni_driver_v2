package com.wasilni.wasilnidriverv2.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasilni.wasilnidriverv2.R;

public class TripPassengersActionsAdapter extends RecyclerView.Adapter<TripPassengersActionsAdapter.MyViewHolder> {

    public TripPassengersActionsAdapter() {
//        mDataset = myDataset;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.textView.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
        {
            return 0;
        }
        else if(position == 1)
        {
            return 1;
        }
        else if(position == 2){
            return 2;
        }
        else if(position == 3){
            return 3;
        }
        else{
            return 0;
        }
    }


    @Override
    public TripPassengersActionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        // create a new view
        View v = null;
        if(viewType == 0)
        {
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_getting_passenger, parent, false);
        }
        else if(viewType == 1){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_picking_up_passenger, parent, false);
        }
        else if(viewType == 2){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_picked_up_passenger, parent, false);
        }
        else if(viewType == 3){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_drop_off_passenger, parent, false);
        }

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public MyViewHolder(View v) {
            super(v);

            Log.d("SED", "MyViewHolder: I am doing it the right way right ?");
        }
    }
}
