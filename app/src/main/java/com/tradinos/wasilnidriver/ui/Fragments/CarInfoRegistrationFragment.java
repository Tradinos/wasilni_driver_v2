package com.tradinos.wasilnidriver.ui.Fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.mvp.model.User;
import com.tradinos.wasilnidriver.mvp.presenter.SearchBrandsPresenterImp;
import com.tradinos.wasilnidriver.ui.adapters.ObjectNameAdapter;
import com.tradinos.wasilnidriver.mvp.model.Brand;
import com.tradinos.wasilnidriver.mvp.model.BrandModel;
import com.tradinos.wasilnidriver.mvp.model.Color;
import com.tradinos.wasilnidriver.mvp.presenter.BrandModelsPresenterImp;
import com.tradinos.wasilnidriver.mvp.presenter.BrandsPresenterImp;
import com.tradinos.wasilnidriver.mvp.presenter.ColorsPresenterImp;
import com.tradinos.wasilnidriver.mvp.view.FormContract;
import com.tradinos.wasilnidriver.util.UtilFunction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.tradinos.wasilnidriver.DriverApplication.updateResources;
import static com.tradinos.wasilnidriver.ui.adapters.ObjectNameAdapter.DISABLED_ITEM_INDEX;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarInfoRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarInfoRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarInfoRegistrationFragment extends Fragment implements
        ColorsPresenterImp.OnResponseInterface,
        BrandsPresenterImp.OnResponseInterface,
        SearchBrandsPresenterImp.OnResponseInterface,
        BrandModelsPresenterImp.OnResponseInterface,
        FormContract,
        View.OnClickListener {
    private OnFragmentInteractionListener mListener;
    AutoCompleteTextView brandAutoCompleteTextView;
    Spinner manufacturYearSp, modelSp, colorSp;
    TextView insuranceImageTV, insuranceDateTV;
    TextView mechanicFrontPageTV, mechanicBackPageTV;
    TextInputEditText carNumberET;
    TextInputLayout carNumberLY;
    CalendarDatePickerDialogFragment cdpInsuranceDate;
    private SearchBrandsPresenterImp presenter ;
    ObjectNameAdapter adapterBrand;
    List<Brand> carBrands = new ArrayList<>();
    List<String> carBrandString = new ArrayList<>();
    private final int CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE = 1;
    private final int GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE = 2;
    private String cameraTempFileMechanicFront = "wasilni_mechanic_front_" + (new Date().getTime()) + ".jpg";

    private final int CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE = 3;
    private final int GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE = 4;
    private String cameraTempFileMechanicBack = "wasilni_mechanic_back_" + (new Date().getTime()) + ".jpg";

    private final int CAMERA_INSURANCE_IMAGE_REQUEST_CODE = 5;
    private final int GALLERY_INSURANCE_IMAGE_REQUEST_CODE = 6;
    private String cameraTempFileInsurance = "wasilni_insurance_mechanic" + (new Date().getTime()) + ".jpg";

    private ObjectNameAdapter colorsAdapter, brandModelsAdapter;
    private BrandsPresenterImp brandsPresenterImp;
    private BrandModelsPresenterImp brandModelsPresenterImp;
    private ColorsPresenterImp colorsPresenterImp;

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
        updateResources(getActivity() , "ar");
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
                        insuranceDateTV.setText(UtilFunction.generateDate(year, monthOfYear, dayOfMonth));
                    }
                })
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setDoneText(getActivity().getString(R.string.yes))
                .setCancelText(getActivity().getString(R.string.no))//.setThemeLight();
                .setThemeCustom(R.style.MyCustomBetterPickersDialogs);

        this.brandModelsPresenterImp = new BrandModelsPresenterImp(this, getActivity());
        this.colorsPresenterImp = new ColorsPresenterImp(this, getActivity());

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
        this.brandAutoCompleteTextView = view.findViewById(R.id.brand);
        this.colorSp = view.findViewById(R.id.car_color);
        this.carNumberET = view.findViewById(R.id.car_number_edit);
        this.carNumberLY = view.findViewById(R.id.car_number_layout);
        presenter = new SearchBrandsPresenterImp(this , getActivity());
        UtilFunction.underlineWidget(this.insuranceDateTV);
        UtilFunction.underlineWidget(this.insuranceImageTV);
        UtilFunction.underlineWidget(this.mechanicFrontPageTV);
        UtilFunction.underlineWidget(this.mechanicBackPageTV);

        brandAutoCompleteTextView.setThreshold(1);
        adapterBrand = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, new ArrayList<>());
        brandAutoCompleteTextView.setAdapter(adapterBrand);
//        adapterBrand.notifyDataSetChanged();

        brandAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.sendToServer(new Brand(1, brandAutoCompleteTextView.getText().toString()));
            }
        });
        this.brandAutoCompleteTextView.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            int idx = carBrandString.indexOf(brandAutoCompleteTextView.getText().toString()) ;
                            Log.e("onFocusChange: ", ""+idx+carBrands.size()  );
                            if (carBrands.size() == 0 || idx == -1) {
                                brandAutoCompleteTextView.setError(getString(R.string.auto_complete_selection));
                            }
                        }
                    }
                }
        );
        this.insuranceImageTV.setOnClickListener(this);
        this.mechanicFrontPageTV.setOnClickListener(this);
        this.mechanicBackPageTV.setOnClickListener(this);
        this.insuranceDateTV.setOnClickListener(this);
        this.setUpAdapters();

        this.fetchData();

    }

    private void setUpAdapters() {

        colorsAdapter = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, new ArrayList<Object>(), getString(R.string.color));
        brandModelsAdapter = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, new ArrayList<Object>(), getString(R.string.model));

        brandAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idx = carBrandString.indexOf(brandAutoCompleteTextView.getText().toString());
                Log.e( "onItemSelected: ", ""+idx);
                if(idx != -1){
                    brandModelsPresenterImp.sendToServer(carBrands.get(idx));
                }

            }
        });

        ObjectNameAdapter adapter6 = new ObjectNameAdapter(getActivity(), R.layout.name_spinner_item, (ArrayList) UtilFunction.generateYears(2000, 2020), getString(R.string.year));
        this.populateBrandModels(new ArrayList<BrandModel>());
        this.populateBrands(new ArrayList<Brand>());
        this.populateColors(new ArrayList<Color>());

        this.colorSp.setAdapter(colorsAdapter);
        this.modelSp.setAdapter(brandModelsAdapter);
        this.manufacturYearSp.setAdapter(adapter6);
    }

    private void fetchData() {
        this.colorsPresenterImp.sendToServer(null);
//        this.brandsPresenterImp.sendToServer(null);


//        List<Brand> brands = new ArrayList<>();
//        List<BrandModel> brandsModel = new ArrayList<>();
//
//        brands.add(new Brand(1,"syria"));
//        brands.add(new Brand(2,"egypt"));
//        brands.add(new Brand(3,"jordan"));
//
//
//        brandsModel.add(new BrandModel(1,"syria"));
//        brandsModel.add(new BrandModel(2,"egypt"));
//        brandsModel.add(new BrandModel(3,"jordan"));
//
//        this.populateBrandModels(brandsModel);
//        this.populateBrands(brands);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE == requestCode || GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE == requestCode) {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE,
                        GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE,
                        this.cameraTempFileMechanicBack,
                        data,
                        this.mechanicBackPageTV);
            } else if (CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE == requestCode || GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE == requestCode) {
                UtilFunction.setTextFromPickingPictureResult(
                        getActivity(),
                        requestCode,
                        CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE,
                        GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE,
                        this.cameraTempFileMechanicFront,
                        data,
                        this.mechanicFrontPageTV);
            } else if (CAMERA_INSURANCE_IMAGE_REQUEST_CODE == requestCode || GALLERY_INSURANCE_IMAGE_REQUEST_CODE == requestCode) {
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
        switch (v.getId()) {
            case R.id.front_page:
                this.startCameraGalleryDialog(this.cameraTempFileMechanicFront, this.CAMERA_MECHANIC_FRONT_IMAGE_REQUEST_CODE, this.GALLERY_MECHANIC_FRONT_IMAGE_REQUEST_CODE);
                break;
            case R.id.back_page:
                this.startCameraGalleryDialog(this.cameraTempFileMechanicBack, this.CAMERA_MECHANIC_BACK_IMAGE_REQUEST_CODE, this.GALLERY_MECHANIC_BACK_IMAGE_REQUEST_CODE);
                break;
            case R.id.insurance_image:
                this.startCameraGalleryDialog(this.cameraTempFileInsurance, CAMERA_INSURANCE_IMAGE_REQUEST_CODE, GALLERY_INSURANCE_IMAGE_REQUEST_CODE);
                break;
            case R.id.insurance_date:
                cdpInsuranceDate.show(getActivity().getSupportFragmentManager(), "insurance_date");
                break;
        }
    }

    public void startCameraGalleryDialog(String tempFile, int cameraCode, int galleryCode) {
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
        if (carBrands.size() == 0 || carBrandString.indexOf(brandAutoCompleteTextView.getText().toString()) == -1) {
            valid = false;
            brandAutoCompleteTextView.setError(getString(R.string.auto_complete_selection));
        }
        if (this.modelSp.getSelectedItemPosition() == DISABLED_ITEM_INDEX) {
            valid = false;
            this.modelSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }
        if (this.manufacturYearSp.getSelectedItemPosition() == DISABLED_ITEM_INDEX) {
            valid = false;
            this.manufacturYearSp.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner_border_red));
        }
        if (this.colorSp.getSelectedItemPosition() == DISABLED_ITEM_INDEX) {
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
        this.brandAutoCompleteTextView.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));
        this.brandAutoCompleteTextView.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_spinner));

    }

    @Override
    public boolean submit() {
        if (this.validate()) {
            this.mListener.submitCarData(this.colorSp.getSelectedItemPosition(),
                    ((BrandModel) this.modelSp.getSelectedItem()).getId(),
                    (String) this.manufacturYearSp.getSelectedItem(),
//                    ((Brand)this.brandAutoCompleteTextView.getSelectedItem()).getId(),
                    1, this.carNumberET.getText().toString(),
                    this.insuranceDateTV.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void responseCode200(User user) {

    }

    @Override
    public void populateBrandModels(List<BrandModel> brandModels) {
        brandModelsAdapter.updateItems((List) brandModels);
    }

    @Override
    public void populateBrands(List<Brand> brands) {

    }

    @Override
    public void populateColors(List<Color> colors) {
        colorsAdapter.updateItems((List) colors);

    }

    @Override
    public void populateSearchBrands(List<Brand> brands) {
        int i = 0 ;
        adapterBrand.clear();
        adapterBrand.updateItems((List)brands);
        adapterBrand.notifyDataSetChanged();
        carBrands.clear();
        carBrandString.clear();
        for(Brand brand : brands){
           carBrands.add(brand);
           carBrandString.add(brand.getName());
            Log.e("populateSearchBrands: ",brand.getName()  );
            i++;
        }
        Log.e("populateSearchBrands: ","111" );
        Log.e("populateSearchBrands: ","222" );
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
        void submitCarData(int color, int model, String manufactureYear, int brand, String number, String insuranceDate);
    }
}
