package com.wasilni.wasilnidriverv2.ui.Activities;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.presenter.InterviewPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.InterviewContract;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

public class InterviewActivity extends AppCompatActivity
        implements View.OnClickListener , InterviewContract.InterviewView{
    private String time, date ;
    private TextView interviewDateTV, interviewTimeTV;
    private CalendarDatePickerDialogFragment cdpInterview;
    private RadialTimePickerDialogFragment rtpInterview;
    private Button setInterviewBtn;
    private InterviewContract.InterviewPresenter presenter = new InterviewPresenterImp(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(getApplicationContext() , "ar");
        setContentView(R.layout.activity_interview);
        initView();
    }

    private void initView() {


        this.cdpInterview = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        Date cDate = new Date();
                        cDate.setYear(year-1900);
                        cDate.setMonth(monthOfYear);
                        cDate.setDate(dayOfMonth);
                        date = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
                        interviewDateTV.setText(date);
                    }
                })
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(2015, 12, 1)
                .setDoneText(this.getString(R.string.yes))
                .setCancelText(this.getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

        this.rtpInterview = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {

                        Date cDate = new Date();
                        cDate.setHours(hourOfDay);
                        cDate.setMinutes(minute);
                        time = new SimpleDateFormat("HH:mm").format(cDate);


                        interviewTimeTV.setText(time);

                    }
                })
                .setForced24hFormat()
                .setDoneText(this.getString(R.string.yes))
                .setCancelText(this.getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

        this.interviewDateTV = findViewById(R.id.interview_date);
        this.interviewTimeTV = findViewById(R.id.interview_time);
        this.setInterviewBtn = findViewById(R.id.set_interview);

        this.setInterviewBtn.setOnClickListener(this);
        this.interviewDateTV.setOnClickListener(this);
        this.interviewTimeTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.interview_date:
                cdpInterview.show(this.getSupportFragmentManager(),"interview_date");
                break;
            case R.id.interview_time:
                rtpInterview.show(this.getSupportFragmentManager(),"interview_time");
                break;
            case R.id.set_interview :
                presenter.sendToServer(
                        date+" "+time
                );
                break;
        }
    }
    @Override
    public void responseCode200() {
        startActivity(new Intent(this , HomeActivity.class));
        ActivityCompat.finishAffinity(this);
    }

    @Override
    public void responseCode422() {
        UtilFunction.showToast(this, R.string.error_422_interview);

    }

    @Override
    public void responseCode500() {

    }

    @Override
    public void responseCode400() {

    }

    @Override
    public void responseCode401() {

    }
}
