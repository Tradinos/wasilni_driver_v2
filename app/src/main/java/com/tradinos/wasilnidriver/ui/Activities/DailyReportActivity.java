package com.tradinos.wasilnidriver.ui.Activities;

import android.arch.lifecycle.ReportFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.Ride;
import com.tradinos.wasilnidriver.mvp.presenter.DailyReportPresenter;
import com.tradinos.wasilnidriver.ui.Dialogs.DateFragment;
import com.tradinos.wasilnidriver.ui.Dialogs.ReportingFragment;
import com.tradinos.wasilnidriver.ui.adapters.DailyReportAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.tradinos.wasilnidriver.DriverApplication.updateResources;

public class DailyReportActivity extends AppCompatActivity implements
        DailyReportPresenter.OnResponseInterface ,
        ReportingFragment.OnFragmentInteractionListener ,
        DateFragment.OnFragmentInteractionListener {
    RecyclerView recyclerView ;
    DailyReportAdapter adapter ;
    TextView date ;
    DailyReportActivity activity = this ;
    DailyReportPresenter presenter = new DailyReportPresenter(this,this) ;
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
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFragment dateFragment = new DateFragment();
                try {
                    if (!dateFragment.isAdded()) {
                        dateFragment.show(activity.getSupportFragmentManager(), "dateFragment");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = df.format(c);
        date.setText(formattedDate);
        adapter = new DailyReportAdapter(this  ) ;
        recyclerView.setAdapter(adapter);
        presenter.sendToServer(formattedDate);
    }

    @Override
    public void populateBooking(List<Ride> bookings) {
        adapter.setList(bookings);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectNewDate(String formattedDate) {
        presenter.sendToServer(formattedDate);
        date.setText(formattedDate);
    }

    @Override
    public void onFragmentInteraction(boolean repeatedRide) {

    }
}
