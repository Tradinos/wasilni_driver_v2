package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.presenter.InterviewPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.InterviewContract;
import com.wasilni.wasilnidriverv2.ui.Activities.HomeActivity;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InterviewRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InterviewRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterviewRegistrationFragment extends Fragment implements
        View.OnClickListener , InterviewContract.InterviewView {
    private OnFragmentInteractionListener mListener;

    private String time, date ;
    private TextView interviewDateTV, interviewTimeTV;
    private CalendarDatePickerDialogFragment cdpInterview;
    private RadialTimePickerDialogFragment rtpInterview;
    private Button setInterviewBtn;
    private InterviewContract.InterviewPresenter presenter = new InterviewPresenterImp(this);
    public InterviewRegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InterviewRegistrationFragment.
     */
    public static InterviewRegistrationFragment newInstance(String param1, String param2) {
        InterviewRegistrationFragment fragment = new InterviewRegistrationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(getActivity() , "ar");

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


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
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
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
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);
        return inflater.inflate(R.layout.fragment_interview_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.interviewDateTV = view.findViewById(R.id.interview_date);
        this.interviewTimeTV = view.findViewById(R.id.interview_time);
        this.setInterviewBtn = view.findViewById(R.id.set_interview);

        this.setInterviewBtn.setOnClickListener(this);
        this.interviewDateTV.setOnClickListener(this);
        this.interviewTimeTV.setOnClickListener(this);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.interview_date:
                cdpInterview.show(getActivity().getSupportFragmentManager(),"interview_date");
                break;
            case R.id.interview_time:
                rtpInterview.show(getActivity().getSupportFragmentManager(),"interview_time");
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
        startActivity(new Intent(this.getActivity() ,HomeActivity.class));
    }

    @Override
    public void responseCode422() {
        UtilFunction.showToast(this.getActivity(), "الرجاء اختيار موعد اخر هذا الموعد محجوز");
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
