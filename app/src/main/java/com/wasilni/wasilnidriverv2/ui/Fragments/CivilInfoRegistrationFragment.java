package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CivilInfoRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CivilInfoRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CivilInfoRegistrationFragment extends Fragment implements
        FormContract,
        View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    private ColorStateList defaultTextColor;

    boolean hasAccountBank = true;


    private Spinner bankSp;
    private TextView  noAccountBankTV, yesAccountBankTV;
    private TextView convicatedImage;
    private TextView idFrontImageTV, idBackImageTV;
    private TextView  licenseStartDateTV, licenseEndDateTV;
    private CalendarDatePickerDialogFragment cdpLicenseStart, cdpLicenseEnd;

    private final int CAMERA_ID_FRONT_IMAGE_REQUEST_CODE = 1;
    private final int GALLERY_ID_FRONT_IMAGE_REQUEST_CODE = 2;
    private String cameraTempFileIdFront = "wasilni_id_front_" + (new Date().getTime()) +  ".jpg";

    private final int CAMERA_ID_BACK_IMAGE_REQUEST_CODE = 3;
    private final int GALLERY_ID_BACK_IMAGE_REQUEST_CODE = 4;
    private String cameraTempFileIDBack = "wasilni_id_back_" + (new Date().getTime()) +  ".jpg";

    private final int CAMERA_CONVICTION_IMAGE_REQUEST_CODE = 5;
    private final int GALLERY_CONVICTION_IMAGE_REQUEST_CODE = 6;
    private String cameraTempFileConviction = "wasilni_conviction_" + (new Date().getTime()) +  ".jpg";

    public CivilInfoRegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CivilInfoRegistrationFragment.
     */
    public static CivilInfoRegistrationFragment newInstance(String param1, String param2) {
        CivilInfoRegistrationFragment fragment = new CivilInfoRegistrationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.cdpLicenseStart = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        String date = ""+year+"-"+monthOfYear+"-"+dayOfMonth ;
                        licenseStartDateTV.setText(date);
                    }
                })
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(2015, 12, 1)
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

        this.cdpLicenseEnd = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        String date = ""+year+"-"+monthOfYear+"-"+dayOfMonth ;
                        licenseEndDateTV.setText(date);
                    }
                })
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(2015, 12, 1)
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

        return inflater.inflate(R.layout.fragment_civil_info_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.noAccountBankTV = view.findViewById(R.id.bank_no);
        this.yesAccountBankTV = view.findViewById(R.id.bank_yes);
        this.convicatedImage = view.findViewById(R.id.convicted_image);
        this.licenseEndDateTV = view.findViewById(R.id.license_date_end);
        this.licenseStartDateTV = view.findViewById(R.id.license_date_start);
        this.idBackImageTV = view.findViewById(R.id.id_back_page);
        this.idFrontImageTV = view.findViewById(R.id.id_front_page);
        this.bankSp = view.findViewById(R.id.bank_spinner);


        this.defaultTextColor = noAccountBankTV.getTextColors();


        this.noAccountBankTV.setOnClickListener(this);
        this.yesAccountBankTV.setOnClickListener(this);
        this.convicatedImage.setOnClickListener(this);
        this.licenseStartDateTV.setOnClickListener(this);
        this.licenseEndDateTV.setOnClickListener(this);


        UtilFunction.underlineWidget(this.licenseEndDateTV);
        UtilFunction.underlineWidget(this.licenseStartDateTV);
        UtilFunction.underlineWidget(this.convicatedImage);
        UtilFunction.underlineWidget(this.idBackImageTV);
        UtilFunction.underlineWidget(this.idFrontImageTV);

        this.setHasBankAccountChoice(true);

        this.idBackImageTV.setOnClickListener(this);
        this.idFrontImageTV.setOnClickListener(this);
        this.convicatedImage.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if( CAMERA_CONVICTION_IMAGE_REQUEST_CODE == requestCode || GALLERY_CONVICTION_IMAGE_REQUEST_CODE == requestCode)
            {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_CONVICTION_IMAGE_REQUEST_CODE,
                        GALLERY_CONVICTION_IMAGE_REQUEST_CODE,
                        this.cameraTempFileConviction,
                        data,
                        this.convicatedImage);
            }
            else if( CAMERA_ID_BACK_IMAGE_REQUEST_CODE == requestCode || GALLERY_ID_BACK_IMAGE_REQUEST_CODE == requestCode)
            {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_ID_BACK_IMAGE_REQUEST_CODE,
                        GALLERY_ID_BACK_IMAGE_REQUEST_CODE,
                        this.cameraTempFileIDBack,
                        data,
                        this.idBackImageTV);
            }
            else if( CAMERA_ID_FRONT_IMAGE_REQUEST_CODE == requestCode || GALLERY_ID_FRONT_IMAGE_REQUEST_CODE == requestCode)
            {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_ID_FRONT_IMAGE_REQUEST_CODE,
                        GALLERY_ID_FRONT_IMAGE_REQUEST_CODE,
                        this.cameraTempFileIdFront,
                        data,
                        this.idFrontImageTV);
            }
        }
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
            case R.id.convicted_image:
                this.startCameraGalleryDialog(this.cameraTempFileConviction, CAMERA_CONVICTION_IMAGE_REQUEST_CODE, GALLERY_CONVICTION_IMAGE_REQUEST_CODE);
                break;
            case R.id.bank_no:
                this.setHasBankAccountChoice(false);
                break;
            case R.id.bank_yes:
                this.setHasBankAccountChoice(true);
                break;
            case R.id.license_date_start:
                cdpLicenseStart.show(getActivity().getSupportFragmentManager(),"license_date_start");
                break;
            case R.id.license_date_end:
                cdpLicenseEnd.show(getActivity().getSupportFragmentManager(),"license_date_end");
                break;
            case R.id.id_front_page:
                this.startCameraGalleryDialog(this.cameraTempFileIdFront,CAMERA_ID_FRONT_IMAGE_REQUEST_CODE, GALLERY_ID_FRONT_IMAGE_REQUEST_CODE);
                break;
            case R.id.id_back_page:
                this.startCameraGalleryDialog(this.cameraTempFileIDBack,CAMERA_ID_BACK_IMAGE_REQUEST_CODE, GALLERY_ID_BACK_IMAGE_REQUEST_CODE);
                break;
        }
    }

    private void startCameraGalleryDialog(String tempFile, int cameraCode, int galleryCode){
        UtilFunction.startCameraGalleryDialogFromFragment(
                getActivity(),
                getString(R.string.dialog_image_title),
                getString(R.string.dialog_image_message),
                getString(R.string.dialog_image_gallery),
                getString(R.string.dialog_image_camera),
                tempFile,
                this,
                cameraCode,
                galleryCode);
    }

    private void setHasBankAccountChoice(boolean isSame){
        this.hasAccountBank = isSame;
        UtilFunction.setYesNoChoice(getActivity(), isSame, this.noAccountBankTV, this.yesAccountBankTV, defaultTextColor, R.color.colorPrimary);
        if(this.hasAccountBank){
            this.bankSp.setVisibility(View.VISIBLE);
        }
        else{
            this.bankSp.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        if(this.licenseEndDateTV.getText().toString().equals(getString(R.string.end_date))) {
            valid = false;
            this.licenseEndDateTV.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_white_border_red));
        }

        if(this.licenseStartDateTV.getText().toString().equals(getString(R.string.start_date))) {
            valid = false;
            this.licenseStartDateTV.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_white_border_red));
        }

        if(this.hasAccountBank && this.bankSp.getSelectedItemPosition() == 1){
            valid = false;
            this.bankSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }

        return valid;
    }

    @Override
    public void resetValidation() {
        this.licenseStartDateTV.setBackground(getActivity().getResources().getDrawable(R.drawable.gray_border));
        this.licenseEndDateTV.setBackground(getActivity().getResources().getDrawable(R.drawable.gray_border));
        this.bankSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
    }

    @Override
    public boolean submit() {
        if(this.validate())
        {
            this.mListener.submitCivilData(this.licenseStartDateTV.getText().toString(), this.licenseEndDateTV.getText().toString(), "");
            return true;
        }
        else{
            return false;
        }

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
        void submitCivilData(String licenseStartDate, String licenseEndDate, String bank);
    }
}
