package com.wasilni.wasilnidriverv2.ui.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.BookingSummary;
import com.wasilni.wasilnidriverv2.ui.adapters.DailyReportAdapter;

import java.util.ArrayList;
import java.util.List;

public class DailyReportActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    DailyReportAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview) ;

        List<Booking> list = new ArrayList<>();
        Booking booking = new Booking() ;
        booking.setName("محمود");
        booking.setPickUpName("ميدان");
        booking.setPullDownName("نهر عيشة");
        BookingSummary bookingSummary = new BookingSummary() ;
        bookingSummary.setBooking_time(12);
        bookingSummary.setWaiting_time_count(6);
        bookingSummary.setKm_count(20);
        booking.setSummary(bookingSummary);
        list.add(booking);


        adapter = new DailyReportAdapter(this , list ) ;
        recyclerView.setAdapter(adapter);
    }
}
