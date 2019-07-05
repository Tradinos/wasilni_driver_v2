package com.tradinos.wasilnidriver.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class UserCostAdapter extends RecyclerView.Adapter<UserCostAdapter.MyViewHolder> {

    private Activity activity ;
    private List<Booking> list ;
    private int ispooling ;
    public UserCostAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity ;
    }

    public UserCostAdapter(Activity activity, int ispooling) {
        this.activity = activity;
        this.ispooling = ispooling;
    }

    public void setIspooling(int ispooling) {
        this.ispooling = ispooling;
    }

    @NonNull
    @Override
    public UserCostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.past_ride_cost_item, parent, false);

        return new UserCostAdapter.MyViewHolder(itemView);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserCostAdapter.MyViewHolder holder, int position) {
        try {
            Booking booking = list.get(position) ;
            holder.name.setText(""+booking.getName()+" : ");
            holder.calcCost.setText(""+booking.getTo_pay());
            holder.deliveredMoney.setText(""+booking.getPaid());
            if(ispooling == 1){
                holder.deliveredMoneylo.setVisibility(View.GONE);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Booking> bookings) {
        list = bookings;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, calcCost  , deliveredMoney;
        View deliveredMoneylo ;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            calcCost = itemView.findViewById(R.id.calculated_cost);
            deliveredMoney = itemView.findViewById(R.id.booking_cost);
            deliveredMoneylo = itemView.findViewById(R.id.deliveredmoneylo);
        }
    }
}