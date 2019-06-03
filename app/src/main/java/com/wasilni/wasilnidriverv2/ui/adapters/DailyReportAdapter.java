package com.wasilni.wasilnidriverv2.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;

import java.util.ArrayList;
import java.util.List;


public class DailyReportAdapter extends RecyclerView.Adapter<DailyReportAdapter.MyViewHolder> {

    private Activity activity ;
    private List<Booking> list ;

    public DailyReportAdapter(Activity activity) {
        this.activity = activity;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_report_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            Booking booking = list.get(position) ;
            holder.name.setText("الزبون: "+booking.getName());
            holder.id.setText("الزبون: "+booking.getId());
            holder.src.setText("من: "+booking.getPickUpName());
            holder.des.setText("إلى: "+booking.getPullDownName());
            holder.distance.setText(""+(int)booking.getSummary().getKm_count()+" كم ");
            holder.duration.setText(""+(int)booking.getSummary().getBooking_time()+" د ");
            holder.waitingTime.setText("انتظار "+(int)booking.getSummary().getWaiting_time_count()+" د ");
//            holder.calcCost.setText(booking.getCalcCost());
//            holder.bookingCost.setText(booking.getBookingCost());
//            holder.deliveredMoney.setText(booking.getDeliveredMoney());
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
        TextView name, src , des ,distance , duration , id ,
                waitingTime , calcCost ,bookingCost , deliveredMoney;
        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            src = itemView.findViewById(R.id.ride_source);
            des = itemView.findViewById(R.id.ride_destination);
            distance = itemView.findViewById(R.id.distance);
            duration = itemView.findViewById(R.id.duration);
            waitingTime = itemView.findViewById(R.id.wait_time);
            calcCost = itemView.findViewById(R.id.calculated_cost);
            bookingCost = itemView.findViewById(R.id.booking_cost);
            deliveredMoney = itemView.findViewById(R.id.delivered_money);
        }
    }
}