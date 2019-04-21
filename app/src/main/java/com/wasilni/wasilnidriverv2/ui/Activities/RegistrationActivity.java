package com.wasilni.wasilnidriverv2.ui.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.wasilni.wasilnidriverv2.ui.Activities.Base.FullScreenActivity;
import com.wasilni.wasilnidriverv2.ui.Fragments.CarInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.CivilInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.InterviewRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.LoginFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PersonalInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneVerificationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.RegistrationFragment;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UserUtil;

public class RegistrationActivity extends FullScreenActivity implements

        PhoneVerificationFragment.OnFragmentInteractionListener ,
        RegistrationFragment.OnFragmentInteractionListener ,
        InterviewRegistrationFragment.OnFragmentInteractionListener ,
        PersonalInfoRegistrationFragment.OnFragmentInteractionListener ,
        LoginFragment.OnFragmentInteractionListener ,
        CivilInfoRegistrationFragment.OnFragmentInteractionListener ,
        CarInfoRegistrationFragment.OnFragmentInteractionListener ,
        PhoneRegistrationFragment.OnFragmentInteractionListener {

    private FrameLayout frameContent;
    private Car car;

    private CompleteDataPresenterImp completeDataPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        UtilFunction.doExtends(BasicmainLayout , this , R.layout.activity_registration);

        this.car = new Car();
        this.completeDataPresenterImp = new CompleteDataPresenterImp(this);
        this.initView();

    }

    public void initView() {
        Log.d("SAED", "initView: I am doing this for the greater good ");
        checkAuth();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment phoneRegFragment = new PhoneRegistrationFragment();
        fragmentTransaction.add(R.id.content_frame,phoneRegFragment);
        fragmentTransaction.commit();

//        RatingDialog.newInstance().show(getSupportFragmentManager(),"rating_dialog");
//        PickingUpPassengerFragment.newInstance().show(getSupportFragmentManager(),"PickingUpPassengerFragment");
    }

    private void checkAuth() {
        String auth = SharedPreferenceUtils.getPreferencesInstance(getApplicationContext()).getString("auth_token",null);
        if(auth != null){
            Intent intent = new Intent(this , HomeActivity.class) ;
            startActivity(intent);
            ActivityCompat.finishAffinity(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void goToPhoneVerification(String phoneNumber) {
        UserUtil.getUserInstance().setUsername(phoneNumber);
        UserUtil.getUserInstance().setPhone_number(phoneNumber);
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
//        changeFragment(new InterviewRegistrationFragment());
        UserUtil.getUserInstance().setAccessToken(SharedPreferenceUtils
                .getPreferencesInstance(getApplicationContext()).getString("auth_token",null));
        Log.e("auth", UserUtil.getUserInstance().getAccessToken());
        startActivity(new Intent(this , InterviewActivity.class));
        ActivityCompat.finishAffinity(this);
    }

    @Override
    public void completeRegistration() {
        RegisterCaptain reg = new RegisterCaptain();
        reg.setCaptain(UserUtil.getUserInstance());
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
    public void submitCarData(int color, int model, String manufactureYear, int brand, String number, String insuranceDate) {
        this.car.setColor_id(color);
        this.car.setBrand_model_id(model);
        this.car.setCar_manufacture_year(manufactureYear);
        this.car.setBrand_id(brand);
        this.car.setNumber(number);
        this.car.setInsurance_expired_date(insuranceDate);
    }

    @Override
    public void submitCivilData(String licenseStartDate, String licenseEndDate, String bank) {
        UserUtil.getUserInstance().setDriving_certificate_end_date(licenseEndDate);
        UserUtil.getUserInstance().setDriving_certificate_start_date(licenseStartDate);
    }

    @Override
    public void submitPersonalData(String firstName,
                                   String lastName,
                                   String email,
                                   String whatsappNumber,
                                   int region,
                                   int nationality,
                                   String birthdate, int gender, String address, String password) {

        UserUtil.getUserInstance().setWhatsapp_number(whatsappNumber);
        UserUtil.getUserInstance().setFirst_name(firstName);
        UserUtil.getUserInstance().setLast_name(lastName);
        UserUtil.getUserInstance().setEmail(email);
        UserUtil.getUserInstance().setBirthday(birthdate);
        UserUtil.getUserInstance().setAddress(address);
        UserUtil.getUserInstance().setLocation_id(region);
        UserUtil.getUserInstance().setNationality_id(nationality);
        UserUtil.getUserInstance().setPassword(password);
        UserUtil.getUserInstance().setPassword_confirmation(password);

        if(gender == 0) {
            UserUtil.getUserInstance().setGender(true);
        }
        else{
            UserUtil.getUserInstance().setGender(false);
        }

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void responseCode200(Boolean response) {
        goToInterviewBooking();
    }
}
