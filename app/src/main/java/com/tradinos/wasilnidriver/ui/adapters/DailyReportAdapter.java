package com.tradinos.wasilnidriver.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.Booking;
import com.tradinos.wasilnidriver.mvp.model.Ride;

import java.util.ArrayList;
import java.util.List;


public class DailyReportAdapter extends RecyclerView.Adapter<DailyReportAdapter.MyViewHolder> {

    private Activity activity ;
    private List<Ride> list ;

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
            Ride ride = list.get(position) ;
            String names ="";
            int i = 1 ;
            for(Booking booking : ride.getBookings()) {
                names += booking.getName() ;
                if(i != ride.getBookings().size()){
                    names += "+" ;
                }
                i++;
            }
            holder.name.setText("الزبون: "+names);
            holder.id.setText(""+ride.getId());
            holder.src.setText("من: "+ride.getBookings().get(0).getPickUpName());
            holder.des.setText("إلى: "+ride.getBookings().get(ride.getBookings().size()-1).getPullDownName());
            holder.distance.setText(""+(int)ride.getKm_count()+" كم ");
            holder.duration.setText(""+(int)ride.getBooking_time()+" د ");
            holder.waitingTime.setText("انتظار "+(int)ride.getWaiting_time_count()+" د ");
//            holder.calcCost.setText(booking.getCalcCost());
//            holder.bookingCost.setText(booking.getBookingCost());
            holder.deliveredMoney.setText("القيمة الاجمالية المدفوعة : "+ride.getCashReceived());
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            holder.adapter.setList(ride.getBookings());
            holder.recyclerView.setAdapter(holder.adapter);
            if(ride.getBookings().size() == 1){
                holder.price_money.setVisibility(View.GONE);
            }else {
                holder.price_money.setText("قيمة الرحلة : "+ride.getMeterPrice().intValue());
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

    public void setList(List<Ride> bookings) {
        list = bookings;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, src , des ,distance , duration , id ,
                waitingTime   , deliveredMoney , price_money;
        RecyclerView recyclerView ;
        UserCostAdapter adapter ;
        public MyViewHolder(View itemView) {
            super(itemView);
            adapter = new UserCostAdapter(activity);
            id = itemView.findViewById(R.id.id);
            recyclerView = itemView.findViewById(R.id.recyclerview);
            name = itemView.findViewById(R.id.name);
            src = itemView.findViewById(R.id.ride_source);
            des = itemView.findViewById(R.id.ride_destination);
            distance = itemView.findViewById(R.id.distance);
            duration = itemView.findViewById(R.id.duration);
            waitingTime = itemView.findViewById(R.id.wait_time);
            deliveredMoney = itemView.findViewById(R.id.total_delivered_money);
            price_money = itemView.findViewById(R.id.price_money);
        }
    }
}