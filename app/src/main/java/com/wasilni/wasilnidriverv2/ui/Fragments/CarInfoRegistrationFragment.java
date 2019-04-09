package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 * {@link CarInfoRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarInfoRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarInfoRegistrationFragment extends Fragment implements
        FormContract,
        View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    Spinner brandSp, manufacturYearSp, modelSp, colorSp;
    TextView insuranceImageTV, insuranceDateTV;
    TextView mechanicFrontPageTV, mechanicBackPageTV;
    TextInputEditText carNumberET;
    TextInputLayout carNumberLY;
    CalendarDatePickerDialogFragment cdpInsuranceDate;

    private final int CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE = 1;
    private final int GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE = 2;
    private String cameraTempFileMechanicFront = "wasilni_mechanic_front_" + (new Date().getTime()) +  ".jpg";

    private final int CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE = 3;
    private final int GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE = 4;
    private String cameraTempFileMechanicBack = "wasilni_mechanic_back_" + (new Date().getTime()) +  ".jpg";

    private final int CAMERA_INSURANCE_IMAGE_REQUEST_CODE = 5;
    private final int GALLERY_INSURANCE_IMAGE_REQUEST_CODE = 6;
    private String cameraTempFileInsurance = "wasilni_insurance_mechanic" + (new Date().getTime()) +  ".jpg";

    public CarInfoRegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CarInfoRegistrationFragment.
     */
    public static CarInfoRegistrationFragment newInstance() {
        CarInfoRegistrationFragment fragment = new CarInfoRegistrationFragment();
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
        this.cdpInsuranceDate = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        insuranceDateTV.setText( UtilFunction.generateDate(year, monthOfYear + 1 , dayOfMonth) );
                    }
                })
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(2015, 12, 1)
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

        return inflater.inflate(R.layout.fragment_car_info_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.insuranceDateTV = view.findViewById(R.id.insurance_date);
        this.insuranceImageTV = view.findViewById(R.id.insurance_image);
        this.mechanicFrontPageTV = view.findViewById(R.id.front_page);
        this.mechanicBackPageTV = view.findViewById(R.id.back_page);
        this.modelSp = view.findViewById(R.id.model);
        this.manufacturYearSp = view.findViewById(R.id.manufacture_year);
        this.brandSp = view.findViewById(R.id.brand);
        this.colorSp = view.findViewById(R.id.car_color);
        this.carNumberET = view.findViewById(R.id.car_number_edit);
        this.carNumberLY = view.findViewById(R.id.car_number_layout);

        UtilFunction.underlineWidget(this.insuranceDateTV);
        UtilFunction.underlineWidget(this.insuranceImageTV);
        UtilFunction.underlineWidget(this.mechanicFrontPageTV);
        UtilFunction.underlineWidget(this.mechanicBackPageTV);

        this.insuranceImageTV.setOnClickListener(this);
        this.mechanicFrontPageTV.setOnClickListener(this);
        this.mechanicBackPageTV.setOnClickListener(this);
        this.insuranceDateTV.setOnClickListener(this);



        String[] colors = new String[]{
                "red",
                "black",
                "green",
                "blue"
        };
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, colors);
        this.colorSp.setAdapter(adapter3);

        String[] brands = new String[]{
                "brand 1",
                "brand 2",
                "brand 3",
                "brand 4",
        };
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, brands);
        this.brandSp.setAdapter(adapter4);

        String[] models = new String[]{
                "model 1",
                "model 2",
                "model 3",
                "model 4",
        };
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, models);
        this.modelSp.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, UtilFunction.generateYears(2000,2020));
        this.manufacturYearSp.setAdapter(adapter6);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if( CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE == requestCode || GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE == requestCode)
            {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE,
                        GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE,
                        this.cameraTempFileMechanicBack,
                        data,
                        this.mechanicBackPageTV);
            }
            else if( CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE == requestCode || GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE == requestCode)
            {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE,
                        GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE,
                        this.cameraTempFileMechanicFront,
                        data,
                        this.mechanicFrontPageTV);
            }
            else if( CAMERA_INSURANCE_IMAGE_REQUEST_CODE == requestCode || GALLERY_INSURANCE_IMAGE_REQUEST_CODE == requestCode)
            {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_INSURANCE_IMAGE_REQUEST_CODE,
                        GALLERY_INSURANCE_IMAGE_REQUEST_CODE,
                        this.cameraTempFileInsurance,
                        data,
                        this.insuranceImageTV);
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
        switch(v.getId()){
            case R.id.front_page:
                this.startCameraGalleryDialog(this.cameraTempFileMechanicFront, this.CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE, this.GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE);
                break;
            case R.id.back_page:
                this.startCameraGalleryDialog(this.cameraTempFileMechanicBack, this.CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE, this.GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE);
                break;
            case R.id.insurance_image:
                this.startCameraGalleryDialog(this.cameraTempFileInsurance, CAMERA_INSURANCE_IMAGE_REQUEST_CODE,GALLERY_INSURANCE_IMAGE_REQUEST_CODE);
                break;
            case R.id.insurance_date:
                cdpInsuranceDate.show(getActivity().getSupportFragmentManager(),"insurance_date");
                break;
        }
    }

    public void startCameraGalleryDialog(String tempFile, int cameraCode, int galleryCode){
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

    @Override
    public boolean validate() {
        boolean valid = true;
        if( this.brandSp.getSelectedItemPosition() == 1){
            valid = false;
            this.brandSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }
        if( this.modelSp.getSelectedItemPosition() == 1){
            valid = false;
            this.modelSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }
        if( this.manufacturYearSp.getSelectedItemPosition() == 1){
            valid = false;
            this.manufacturYearSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }
        if( this.colorSp.getSelectedItemPosition() == 1){
            valid = false;
            this.colorSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }
        return valid;
    }

    @Override
    public void resetValidation() {
        this.colorSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
        this.modelSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
        this.manufacturYearSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
        this.brandSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
        this.brandSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));

    }

    @Override
    public boolean submit() {
        if (this.validate())
        {
            this.mListener.submitCarData(this.colorSp.getSelectedItemPosition(),
                    this.modelSp.getSelectedItemPosition(),
                    (String)this.manufacturYearSp.getSelectedItem(),
                    this.brandSp.getSelectedItemPosition(),
                    this.carNumberET.getText().toString(),
                    this.insuranceDateTV.getText().toString() );
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
        void submitCarData(int color, int model, String manufactureYear, int brand, String number, String insuranceDate);
    }
}
