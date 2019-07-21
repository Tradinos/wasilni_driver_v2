package com.tradinos.wasilnidriver.ui.Dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.Report;
import com.tradinos.wasilnidriver.mvp.model.ReportChild;
import com.tradinos.wasilnidriver.mvp.model.UploadReport;
import com.tradinos.wasilnidriver.mvp.presenter.ReportProblemPresenter;
import com.tradinos.wasilnidriver.mvp.presenter.SendReportPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")

public class ReportingFragment extends DialogFragment implements
        ReportProblemPresenter.Listener ,
        SendReportPresenter.Listener
{
    private ConstraintLayout numberL , dateL , textL  ;
    private LinearLayout checkL , radioL , questionL , formL ;
    private Button button ;
    private List<EditText> editTexts = new ArrayList<>();
    private List<RadioButton> radioButtons = new ArrayList<>();

    private List<ReportChild> editTextsId = new ArrayList<>();
    private List<ReportChild> checkBoxesId = new ArrayList<>();
    private List<ReportChild> radioButtonsId = new ArrayList<>();

    private Map<Integer , List<String>>checkBoxes= new HashMap<>() ;


    private SendReportPresenter sendReportPresenter  ;
    private ReportProblemPresenter reportProblemPresenter  ;
    private OnFragmentInteractionListener mListener;
    private Activity activity ;
    private UploadReport report = new UploadReport();
    private CalendarDatePickerDialogFragment cdpRide;

    public ReportingFragment() {
        // Required empty public constructor
    }

    public ReportingFragment(Activity activity) {
        this.activity = activity;
    }

    public static ReportingFragment newInstance(Activity activity) {
        ReportingFragment fragment = new ReportingFragment(activity);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_reporting, container, false);

        // Set transparent background and no title
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        return  v;
    }
    public void setBookingId(int id){
        report.setBookingId(id);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numberL = view.findViewById(R.id.ans_numberfield);
        formL = view.findViewById(R.id.form);
        textL = view.findViewById(R.id.ans_textfield);
        checkL = view.findViewById(R.id.checks);
        questionL = view.findViewById(R.id.questions);
        radioL = view.findViewById(R.id.radios);
        dateL = view.findViewById(R.id.ans_datefield);
        button = view.findViewById(R.id.submit);
        sendReportPresenter = new SendReportPresenter(activity , this) ;
        reportProblemPresenter = new ReportProblemPresenter(this , activity) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo send report
//            Log.e("button: ",""+report.getId() );
                report.setAnswers(new HashMap());
                int i = 0 ;
                for(EditText editText : editTexts) {
                    report.getAnswers().put("question_"+editTextsId.get(i).getId(), editText.getText().toString());
                    i++;
                }
                i = 0;
                for(Integer id : checkBoxes.keySet()) {
                    report.getAnswers().put("question_"+id , checkBoxes.get(id));
                    i++;
                }
                i=0;
                for(RadioButton radioButton : radioButtons) {
                    report.getAnswers().put("question_"+ radioButtonsId.get(i).getId(), radioButton.getText().toString());
                    i++;
                }
                sendReportPresenter.sendToServer(report);
            }
        });

        reportProblemPresenter.sendToServer(report);

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showReportDialog(Report data) {
        Log.e( "showReportDialog",""+data.getType() );
        if(data.getType().equals("FORM")){
            button.setVisibility(View.VISIBLE);
            questionL.setVisibility(View.GONE);
            formL.setVisibility(View.VISIBLE) ;
            List<ReportChild> questions = data.getList();
            LayoutInflater inflater = LayoutInflater.from(activity);


            LayoutInflater linf;

            linf = LayoutInflater.from(activity);
            Typeface face = Typeface.createFromAsset(activity.getAssets(),
                    "bahij_thesansarabic_bold.ttf");

            for(final ReportChild reportChild : questions){
                switch (reportChild.getType()){
                    case "TEXT" :{
                        View v = linf.inflate(R.layout.text_component, null);
                        TextView title = v.findViewById(R.id.title);
                        TextView details = v.findViewById(R.id.details);
                        details.setText(reportChild.getDetails());
                        details.setTypeface(face);
                        EditText editText = v.findViewById(R.id.report_text);
                        title.setText(reportChild.getTitle());
                        title.setTypeface(face);
                        title.setTextColor(Color.BLACK);
                        if(reportChild.getRequire() == 1){
                            title.setText(reportChild.getTitle()+"*");
                        }
                        title.setPadding(8,8,8,8);
                        editText.setPadding(8,8,8,8);
                        editText.setTypeface(face);
                        editTexts.add(editText);
                        editTextsId.add(reportChild);
                        formL.addView(v);
                        break;
                    }
                    case "MULTISELECT" :{
                        View v = linf.inflate(R.layout.checks_component, null);
                        LinearLayout linearLayout = v.findViewById(R.id.checks);
                        linearLayout.setPadding(8,8,8,8);
                        List<String>strings = reportChild.getExtra();

                        TextView textView = v.findViewById(R.id.details);
                        textView.setText(reportChild.getDetails());

                        TextView title = v.findViewById(R.id.title);
                        title.setText(reportChild.getTitle());
                        if(reportChild.getRequire() == 1){
                            title.setText(reportChild.getTitle()+"*");
                        }
                        for(String s : strings){
                            final CheckBox checkBox = new CheckBox(activity);
                            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            checkBox.setText(s);
                            checkBox.setGravity(Gravity.CENTER);
                            checkBox.setTypeface(face);
                            checkBox.setTextSize(12);
                            checkBoxes.put(reportChild.getId(), new ArrayList<String>());
                            checkBox.setTextColor(Color.BLACK);
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked){
                                        checkBoxes.get(reportChild.getId()).add(checkBox.getText().toString());
                                    }else{
                                        checkBoxes.get(reportChild.getId()).remove(checkBox.getText().toString());
                                    }
                                }
                            });
                            linearLayout.addView(checkBox);
                        }
                        formL.addView(v);
                        break;
                    }
                    case "SELECT" :{
                        View v = linf.inflate(R.layout.radios_component, null);

                        TextView textView = v.findViewById(R.id.details);
                        textView.setText(reportChild.getDetails());

                        TextView title = v.findViewById(R.id.title);
                        title.setText(reportChild.getTitle());
                        if(reportChild.getRequire() == 1){
                            title.setText(reportChild.getTitle()+"*");
                        }
                        RadioGroup radioGroup = v.findViewById(R.id.radiogroup);
                        radioGroup.setPadding(8,8,8,8);
                        List<String>strings = reportChild.getExtra();
                        List<RadioButton> buttonList = new ArrayList<>();
                        for(String s : strings){
                            final RadioButton radioButton = new RadioButton(activity) ;
                            radioButton.setText(s);
                            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            radioButton.setGravity(Gravity.CENTER);
                            radioButton.setTypeface(face);
                            radioButton.setTextSize(12);
                            radioButton.setTextColor(Color.BLACK);
                            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked){
                                        Log.e("onCheckedChanged","isChecked" );
                                        radioButtons.add(radioButton);
                                        radioButtonsId.add(reportChild);
                                    }else{
                                        Log.e("onCheckedChanged","!!!!isChecked" );
                                        radioButtons.remove(radioButton) ;
                                        radioButtonsId.remove(reportChild);
                                    }
                                }
                            });
                            radioGroup.addView(radioButton);
                            buttonList.add(radioButton);
                        }

                        formL.addView(v);
                        break;
                    }
                    case "DATE" :{
                        Date today = Calendar.getInstance().getTime();
                        today.setTime(today.getTime() +(15*60*1000));
                        View v = linf.inflate(R.layout.date_component, null);
                        TextView title = v.findViewById(R.id.title);
                        title.setText(reportChild.getTitle());
                        title.setPadding(8,8,8,8);
                        title.setTypeface(face);
                        title.setTextColor(Color.BLACK);

                        TextView details = v.findViewById(R.id.details);
                        details.setText(reportChild.getDetails());
                        details.setTypeface(face);

                        final TextView date = v.findViewById(R.id.select_date);
                        date.setPadding(8,8,8,8);
                        date.setTypeface(face);


                        cdpRide = new CalendarDatePickerDialogFragment()
                                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                                        date.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                                        EditText editText1 = new EditText(activity);
                                        editText1.setText(date.getText().toString());
                                        editTexts.add(editText1);
                                    }
                                })
                                .setFirstDayOfWeek(Calendar.SUNDAY)
                                .setDoneText(activity.getString(R.string.yes))
                                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

                        date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cdpRide.show(getActivity().getSupportFragmentManager(), "ride_date");
                            }
                        });


                        editTextsId.add(reportChild);
                        formL.addView(v);
                        break;
                    }
                    case "NUMBER" :{
                        View v = linf.inflate(R.layout.number_component, null);
                        TextView title = v.findViewById(R.id.title);
                        TextView details = v.findViewById(R.id.details);
                        details.setText(reportChild.getDetails());
                        details.setTypeface(face);
                        EditText editText = v.findViewById(R.id.report_text);
                        title.setText(reportChild.getTitle());
                        title.setPadding(8,8,8,8);
                        title.setTypeface(face);
                        title.setTextColor(Color.BLACK);
                        if(reportChild.getRequire() == 1){
                            title.setText(reportChild.getTitle()+"*");
                        }
                        editText.setTypeface(face);
                        editText.setPadding(8,8,8,8);
                        editTexts.add(editText);
                        editTextsId.add(reportChild);
                        formL.addView(v);
                        break;
                    }
                    case "CHECK" :{
                        View v = linf.inflate(R.layout.radios_component, null);

                        TextView textView = v.findViewById(R.id.details);
                        textView.setText(reportChild.getDetails());

                        TextView title = v.findViewById(R.id.title);
                        title.setText(reportChild.getTitle());
                        if(reportChild.getRequire() == 1){
                            title.setText(reportChild.getTitle()+"*");
                        }
                        RadioGroup radioGroup = v.findViewById(R.id.radiogroup);
                        radioGroup.setPadding(8,8,8,8);
                        List<String>strings = new ArrayList<>();
                        strings.add("نعم");
                        strings.add("لا");
                        List<RadioButton> buttonList = new ArrayList<>();
                        for(String s : strings){
                            final RadioButton radioButton = new RadioButton(activity) ;
                            radioButton.setText(s);
                            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            radioButton.setGravity(Gravity.CENTER);
                            radioButton.setTypeface(face);
                            radioButton.setTextSize(12);
                            radioButton.setTextColor(Color.BLACK);
                            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked){
                                        Log.e("onCheckedChanged","isChecked" );
                                        radioButtons.add(radioButton);
                                        radioButtonsId.add(reportChild);
                                    }else{
                                        Log.e("onCheckedChanged","!!!!isChecked" );
                                        radioButtons.remove(radioButton) ;
                                        radioButtonsId.remove(reportChild.getId());
                                    }
                                }
                            });
                            radioGroup.addView(radioButton);
                            buttonList.add(radioButton);
                        }

                        formL.addView(v);
                        break;
                    }
                }
            }
        }else{
            questionL.setVisibility(View.VISIBLE);
            questionL.removeAllViews();
            questionL.setPadding(8,8,8,8);
            List<ReportChild> questions = data.getList();
            List<TextView> titles = new ArrayList<>();
            List<TextView> hints = new ArrayList<>();
            Typeface face = Typeface.createFromAsset(activity.getAssets(),
                    "bahij_thesansarabic_bold.ttf");

            TextView text = new TextView(activity) ;
            text.setText(R.string.quesetions_report);
            text.setTextSize(16);
            text.setTextColor(Color.BLACK);
            text.setTypeface(face);
            text.setPadding(8,8,8,8);
//            text.setCompoundDrawables(getActivity().getDrawable(R.drawable.ic_keyboard_arrow_right_green_24dp),null,null,null);
            questionL.addView(text);

            for(final ReportChild reportChild : questions){
                Log.e( "showReportDialog1 ",reportChild.getTitle() );
                TextView textView = new TextView(activity) ;
                titles.add(textView);
                textView.setText(reportChild.getTitle());
                textView.setTextSize(14);
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(face);
                textView.setPadding(8,8,8,0);
                textView.setCompoundDrawablePadding(8);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,0,(R.drawable.ic_keyboard_arrow_left_green_24dp),0);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        report.setLastQuestionId(reportChild.getId());
                        reportProblemPresenter.sendToServer(report);

                    }
                });
                questionL.addView(textView);

                textView = new TextView(activity) ;
                hints.add(textView);
                textView.setPadding(8,0,8,8);
                textView.setText(reportChild.getDetails());
                textView.setTextSize(10);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        report.setLastQuestionId(reportChild.getId());
                        reportProblemPresenter.sendToServer(report);
                    }
                });
                textView.setTypeface(face);
                questionL.addView(textView);

            }
        }
    }

    @Override
    public void ReportSubmited() {
        dismiss();
    }

    public void setTicketTypeId(int i) {
        report.setTicketTypeId(i);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(boolean repeatedRide);
    }
}
