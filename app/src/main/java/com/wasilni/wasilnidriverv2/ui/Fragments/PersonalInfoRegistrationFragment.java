package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.squareup.picasso.Picasso;
import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.adapters.ObjectNameAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.Location;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;
import com.wasilni.wasilnidriverv2.mvp.presenter.LocationsPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.presenter.NationalitiesPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.wasilni.wasilnidriverv2.ui.adapters.ObjectNameAdapter.DISABLED_ITEM_INDEX;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalInfoRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalInfoRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoRegistrationFragment extends Fragment implements
        FormContract,
        NationalitiesPresenterImp.OnResponseInterface,
        View.OnClickListener, LocationsPresenterImp.OnResponseInterface {
    private OnFragmentInteractionListener mListener;

    private TextInputEditText firstnameET, lastnameET, whatsappPhoneET, emailET, passwordET, addressDetailsEdit;
    private TextInputLayout firstnameIL, lastnameIL, whatsappPhoneIN, emailIN, passwordLT, regionLT;

    private AutoCompleteTextView regionAC;
    private TextView birthdateTV, noWhatsappTV, yesWhatsappTV;
    private ImageView profilePicture;
    private Spinner genderSp, nationalityAC;
    private LinearLayout whatsappPhoneLL;

    private CalendarDatePickerDialogFragment cdp;

    boolean isWhatsappSameAsPhone = true;
    private ColorStateList defaultTextColor;

    private String cameraTempFile = "wasilni_profile_picture_" + (new Date().getTime()) +  ".jpg";

    private final int GALLERY_PROFILE_IMAGE_REQUEST_CODE = 1;
    private final int CAMERA_PROFILE_IMAGE_REQUEST_CODE = 2;

    ArrayList<String> genders;
    private ObjectNameAdapter nationalitiesAdapter, regionsAdapter;
    private NationalitiesPresenterImp nationalitiesPresenterImp;
    private LocationsPresenterImp locationsPresenterImp;

    ArrayList<String> regions = new ArrayList<String>();
    public PersonalInfoRegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PersonalInfoRegistrationFragment.
     */
    public static PersonalInfoRegistrationFragment newInstance() {
        PersonalInfoRegistrationFragment fragment = new PersonalInfoRegistrationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.nationalitiesPresenterImp = new NationalitiesPresenterImp(this, getActivity());
        this.locationsPresenterImp = new LocationsPresenterImp(this, getActivity());
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        birthdateTV.setText( UtilFunction.generateDate(year, monthOfYear + 1 , dayOfMonth) );
                    }
                })
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(1996, 12, 1)
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);


        return inflater.inflate(R.layout.fragment_personal_info_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.birthdateTV = view.findViewById(R.id.birthdate);
        this.genderSp = view.findViewById(R.id.gender);
        this.noWhatsappTV = view.findViewById(R.id.whatsapp_no);
        this.yesWhatsappTV = view.findViewById(R.id.whatsapp_yes);
        this.profilePicture = view.findViewById(R.id.profile_picture);
        this.whatsappPhoneLL = view.findViewById(R.id.whatsapp_phone_layout);
        this.firstnameET = view.findViewById(R.id.first_name_edit);
        this.lastnameET = view.findViewById(R.id.last_name_edit);
        this.firstnameIL = view.findViewById(R.id.first_name_layout);
        this.lastnameIL = view.findViewById(R.id.last_name_layout);
        this.whatsappPhoneET = view.findViewById(R.id.phone_edit);
        this.whatsappPhoneIN = view.findViewById(R.id.phone_layout);
        this.emailET = view.findViewById(R.id.email_edit);
        this.emailIN = view.findViewById(R.id.email_layout);
        this.regionAC = view.findViewById(R.id.region_auto_complete);
        this.regionLT = view.findViewById(R.id.region_layout);
        this.nationalityAC = view.findViewById(R.id.nationality);
        this.addressDetailsEdit = view.findViewById(R.id.address_details_edit);
        this.passwordET = view.findViewById(R.id.password_edit);
        this.passwordLT = view.findViewById(R.id.password_layout);


        this.defaultTextColor = this.noWhatsappTV.getTextColors();

        UtilFunction.underlineWidget(this.birthdateTV);


        this.birthdateTV.setOnClickListener(this);
        this.noWhatsappTV.setOnClickListener(this);
        this.yesWhatsappTV.setOnClickListener(this);
        this.profilePicture.setOnClickListener(this);

        this.setWhatsappChoice(true);

        this.setUpAdapters();

        this.fetchData();
    }

    private void setUpAdapters(){

        this.genders = new ArrayList<>();
        this.genders.add(getString(R.string.female));
        this.genders.add(getString(R.string.male));

        ObjectNameAdapter adapter = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, (ArrayList)genders, getString(R.string.gender));
        this.genderSp.setAdapter(adapter);

        nationalitiesAdapter = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, new ArrayList<Object>(), getString(R.string.nationality));
        this.nationalityAC.setAdapter(nationalitiesAdapter);

        this.regionsAdapter = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, new ArrayList<Object>());
        this.regionAC.setAdapter(regionsAdapter);

        this.regionAC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                locationsPresenterImp.sendToServer(new Location(0, regionAC.getText().toString()));
            }
        });

        this.regionAC.setOnFocusChangeListener(
            new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (regions.size() == 0 ||
                                regions.indexOf(regionAC.getText().toString()) == -1) {
                            regionAC.setError(getString(R.string.auto_complete_selection));
                        };
                    }
                }
            }
        );
    }

    private void fetchData(){
        this.nationalitiesPresenterImp.sendToServer(null);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( CAMERA_PROFILE_IMAGE_REQUEST_CODE == requestCode || GALLERY_PROFILE_IMAGE_REQUEST_CODE == requestCode)
        {
            String filePath = null;
            if (resultCode == RESULT_OK && requestCode == CAMERA_PROFILE_IMAGE_REQUEST_CODE) {
                filePath = UtilFunction.handleCameraIntent(getActivity(),  this.cameraTempFile);
            }
            else if(resultCode == RESULT_OK && requestCode == GALLERY_PROFILE_IMAGE_REQUEST_CODE) {
               filePath =UtilFunction.handleGalleryIntent(getActivity(), data);
            }
            if(filePath != null)
            {
                Picasso.get()
                        .load(new File(filePath))
                        .into(profilePicture);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthdate:
                this.cdp.show(getActivity().getSupportFragmentManager(), "birthdate");
                break;
            case R.id.whatsapp_yes:
                this.setWhatsappChoice(true);
                break;
            case R.id.whatsapp_no:
                this.setWhatsappChoice(false);
                break;
            case R.id.profile_picture:
                UtilFunction.startCameraGalleryDialogFromFragment(
                        getActivity(),
                       getString(R.string.dialog_image_title),
                        getString(R.string.dialog_image_message),
                        getString(R.string.dialog_image_gallery),
                        getString(R.string.dialog_image_camera),
                        this.cameraTempFile,
                        this,
                        this.CAMERA_PROFILE_IMAGE_REQUEST_CODE,
                        this.GALLERY_PROFILE_IMAGE_REQUEST_CODE);
                break;
        }
    }

    private void setWhatsappChoice(boolean isSame){
        this.isWhatsappSameAsPhone = isSame;
        UtilFunction.setYesNoChoice(getActivity(), isSame, this.noWhatsappTV, this.yesWhatsappTV, defaultTextColor, R.color.colorPrimary);
        if(!this.isWhatsappSameAsPhone){
            this.whatsappPhoneLL.setVisibility(View.VISIBLE);
        } else{
            this.whatsappPhoneLL.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean validate(){
        boolean valid = true;
        resetValidation();
        String requiredFieldStrId = getString(R.string.required_field);

        if(this.firstnameET.getText().toString().isEmpty()){
            valid = false;
            UtilFunction.setErrorToInputLayout(firstnameIL, requiredFieldStrId);
        }

        if(this.lastnameET.getText().toString().isEmpty()){
            valid = false;
            UtilFunction.setErrorToInputLayout(lastnameIL, requiredFieldStrId);
        }

        if(!this.isWhatsappSameAsPhone && this.whatsappPhoneET.getText().toString().isEmpty()){
            valid = false;
            UtilFunction.setErrorToInputLayout(whatsappPhoneIN, requiredFieldStrId);
        }

        if(this.emailET.getText().toString().isEmpty()){
            valid = false;
            UtilFunction.setErrorToInputLayout(emailIN, requiredFieldStrId);
        }

        if(birthdateTV.getText().toString().equals(getString(R.string.date_hint))){
            valid = false;
           birthdateTV.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_white_border_red));
        }

        if(this.nationalityAC.getSelectedItemPosition() == DISABLED_ITEM_INDEX){
            valid = false;
            this.nationalityAC.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }

        if(this.passwordET.getText().toString().isEmpty()){
            valid = false;
            UtilFunction.setErrorToInputLayout(passwordLT, requiredFieldStrId);
        }

        if(this.genderSp.getSelectedItemPosition() != DISABLED_ITEM_INDEX){
            valid = false;
            this.genderSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }

        if (regions.size() == 0 || regions.indexOf(regionAC.getText().toString()) == -1) {
            valid = false;
            UtilFunction.setErrorToInputLayout(regionLT, getString(R.string.auto_complete_selection));
        }

        return valid;
    }

    @Override
    public void resetValidation() {

        UtilFunction.removeErrorToInputLayout(emailIN);
        UtilFunction.removeErrorToInputLayout(firstnameIL);
        UtilFunction.removeErrorToInputLayout(lastnameIL);
        UtilFunction.removeErrorToInputLayout(whatsappPhoneIN);
        UtilFunction.removeErrorToInputLayout(regionLT);
        this.birthdateTV.setBackground(getActivity().getResources().getDrawable(R.drawable.gray_border));
        this.nationalityAC.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
        this.genderSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
    }

    @Override
    public boolean submit() {
        if(this.validate())
        {
            this.mListener.submitPersonalData(
                    this.firstnameET.getText().toString(),
                    this.lastnameET.getText().toString(),
                    this.emailET.getText().toString(),
                    this.whatsappPhoneET.getText().toString(),
                    1,
                    ((Nationality)this.nationalityAC.getSelectedItem()).getId(),
                    this.birthdateTV.getText().toString(),
                    this.genderSp.getSelectedItemPosition(),
                    this.addressDetailsEdit.getText().toString(),
                    this.passwordET.getText().toString()
            );
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public void populateNationalities(List<Nationality> items) {
        this.nationalitiesAdapter.updateItems((List)items);
    }

    @Override
    public void populateLocations(List<Location> locations) {
        Log.d("SIZE", "populateLocations: " + locations.size());
        this.regionsAdapter.updateItems((List)locations);
    }

    @Override
    public void onFailure(List<String> errors) {

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
        void submitPersonalData(String firstName,
                                String lastName,
                                String email,
                                String whatsappNumber,
                                int region,
                                int nationality,
                                String birthdate,
                                int gender, String address, String password);
    }
}
