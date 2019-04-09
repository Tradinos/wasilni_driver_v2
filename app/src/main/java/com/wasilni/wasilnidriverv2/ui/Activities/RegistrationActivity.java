package com.wasilni.wasilnidriverv2.ui.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.wasilni.wasilnidriverv2.mvp.model.Car;
import com.wasilni.wasilnidriverv2.mvp.model.RegisterCaptain;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.presenter.CompleteDataPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.BasicActivity;
import com.wasilni.wasilnidriverv2.ui.Fragments.CarInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.CivilInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.InterviewRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.LoginFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PersonalInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneVerificationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.RegistrationFragment;

import com.wasilni.wasilnidriverv2.R;

public class RegistrationActivity extends BasicActivity implements
        HomeContract.HomeView,
        PhoneVerificationFragment.OnFragmentInteractionListener ,
        RegistrationFragment.OnFragmentInteractionListener ,
        InterviewRegistrationFragment.OnFragmentInteractionListener ,
        PersonalInfoRegistrationFragment.OnFragmentInteractionListener ,
        LoginFragment.OnFragmentInteractionListener ,
        CivilInfoRegistrationFragment.OnFragmentInteractionListener ,
        CarInfoRegistrationFragment.OnFragmentInteractionListener ,
        PhoneRegistrationFragment.OnFragmentInteractionListener {

    private FrameLayout frameContent;
    private User user;
    private Car car;

    private CompleteDataPresenterImp completeDataPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        UtilFunction.doExtends(BasicmainLayout , this , R.layout.activity_registration);

        this.user = new User();
        this.completeDataPresenterImp = new CompleteDataPresenterImp();
        this.initView();

    }

    @Override
    public void initView() {
        Log.d("SAED", "initView: I am doing this for the greater good ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment phoneRegFragment = new PhoneRegistrationFragment();
        fragmentTransaction.add(R.id.content_frame,phoneRegFragment);
        fragmentTransaction.commit();

//        RatingDialog.newInstance().show(getSupportFragmentManager(),"rating_dialog");
//        PickingUpPassengerFragment.newInstance().show(getSupportFragmentManager(),"PickingUpPassengerFragment");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void goToPhoneVerification(String phoneNumber) {
        this.user.setPhone_number(phoneNumber);
        changeFragment(new PhoneVerificationFragment());
    }

    @Override
    public void goToLogin() {
        changeFragment(new LoginFragment());
    }

    @Override
    public void goToRegistrationFragment() {
        changeFragment(new RegistrationFragment());
    }

    @Override
    public void goToInterviewBooking() {
        changeFragment(new InterviewRegistrationFragment());
    }

    @Override
    public void completeRegistration() {
        RegisterCaptain reg = new RegisterCaptain();
        reg.setUser(this.user);
        reg.setCar(this.car);
        this.completeDataPresenterImp.sendToServer(reg);
    }

    @Override
    public void goToPhoneRegistration() {
        changeFragment(new PhoneRegistrationFragment());
    }

    @Override
    public void goToMainView(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void submitCarData(int color, int model, String manufactureYear, int brand, String number) {
        this.car.setColorId(color);
        this.car.setModelId(model);
        this.car.setCarManufactureYear(manufactureYear);
        this.car.setBrandId(brand);
        this.car.setNumber(number);
    }

    @Override
    public void submitCivilData(String licenseStartDate, String licenseEndDate, String bank) {
        this.user.setLicenseEnd(licenseEndDate);
        this.user.setLicenseStart(licenseStartDate);
    }

    @Override
    public void submitPersonalData(String firstName,
                                   String lastName,
                                   String email,
                                   String whatsappNumber,
                                   int region,
                                   int nationality,
                                   String birthdate, int gender, String address) {

        this.user.setWhatsapp_number(whatsappNumber);
        this.user.setFirst_name(firstName);
        this.user.setLast_name(lastName);
        this.user.setEmail(email);
        this.user.setBirthday(birthdate);
        this.user.setAddress(address);
        this.user.setRegionId(region);
        this.user.setNationalityId(nationality);

        if(gender == 0) {
            this.user.setGender(true);
        }
        else{
            this.user.setGender(false);
        }

    }
}
