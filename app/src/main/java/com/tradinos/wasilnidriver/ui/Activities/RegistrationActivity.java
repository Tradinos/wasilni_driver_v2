package com.tradinos.wasilnidriver.ui.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.tradinos.wasilnidriver.mvp.model.Car;
import com.tradinos.wasilnidriver.mvp.model.RegisterCaptain;
import com.tradinos.wasilnidriver.mvp.presenter.CompleteDataPresenterImp;
import com.tradinos.wasilnidriver.ui.Activities.Base.FullScreenActivity;
import com.tradinos.wasilnidriver.ui.Fragments.CarInfoRegistrationFragment;
import com.tradinos.wasilnidriver.ui.Fragments.CivilInfoRegistrationFragment;
import com.tradinos.wasilnidriver.ui.Fragments.InterviewRegistrationFragment;
import com.tradinos.wasilnidriver.ui.Fragments.LoginFragment;
import com.tradinos.wasilnidriver.ui.Fragments.PersonalInfoRegistrationFragment;
import com.tradinos.wasilnidriver.ui.Fragments.PhoneRegistrationFragment;
import com.tradinos.wasilnidriver.ui.Fragments.PhoneVerificationFragment;
import com.tradinos.wasilnidriver.ui.Fragments.RegistrationFragment;

import com.tradinos.wasilnidriver.R;
import com.tradinos.wasilnidriver.util.SharedPreferenceUtils;
import com.tradinos.wasilnidriver.util.UserUtil;

import java.util.Calendar;

import static com.tradinos.wasilnidriver.DriverApplication.updateResources;

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
        updateResources(this , "ar");

        setContentView(R.layout.activity_registration);
//        UtilFunction.doExtends(BasicmainLayout , this , R.layout.activity_registration);

        checkActivitionCode();


        this.car = new Car();
        this.completeDataPresenterImp = new CompleteDataPresenterImp(this);
        this.initView();

    }

    private void checkActivitionCode() {
        long time = SharedPreferenceUtils.getPreferencesInstance(this).getLong("activition_code_time",0);
        time += (30*60*1000);
        if(time > Calendar.getInstance().getTime().getTime()){
            String phonenumber = SharedPreferenceUtils.getPreferencesInstance(this).getString("phonenumber","");

//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            Fragment phoneRegFragment = new PhoneVerificationFragment();
//            fragmentTransaction.add(R.id.content_frame,phoneRegFragment);
//            fragmentTransaction.commit();
            goToPhoneVerification(phonenumber);
        }
    }

    public void initView() {
        checkAuth();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment phoneRegFragment = new LoginFragment();
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
