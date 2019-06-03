package com.wasilni.wasilnidriverv2.ui.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.BookingSummary;
import com.wasilni.wasilnidriverv2.ui.adapters.DailyReportAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

public class DailyReportActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    DailyReportAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this, "ar");
        setContentView(R.layout.activity_daily_report);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        booking = new Booking() ;
        booking.setName("مجد");
        booking.setPickUpName("جرمانا");
        booking.setPullDownName("الجمارك");
         bookingSummary = new BookingSummary() ;
        bookingSummary.setBooking_time(15);
        bookingSummary.setWaiting_time_count(4);
        bookingSummary.setKm_count(12);
        booking.setSummary(bookingSummary);
        list.add(booking);


        adapter = new DailyReportAdapter(this , list ) ;
        recyclerView.setAdapter(adapter);
    }
}
