package com.wasilni.wasilnidriverv2.ui.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.transition.FragmentTransitionSupport;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.wasilni.wasilnidriverv2.mvp.view.HomeContract;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.BasicActivity;
import com.wasilni.wasilnidriverv2.ui.Fragments.CarInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.CivilInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PersonalInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PhoneVerificationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.RegistrationFragment;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

import com.wasilni.wasilnidriverv2.R;

import org.greenrobot.eventbus.util.ErrorDialogManager;

public class RegistrationActivity extends BasicActivity implements
        HomeContract.HomeView,
        PhoneVerificationFragment.OnFragmentInteractionListener ,
        RegistrationFragment.OnFragmentInteractionListener ,
        PersonalInfoRegistrationFragment.OnFragmentInteractionListener ,
        CivilInfoRegistrationFragment.OnFragmentInteractionListener ,
        CarInfoRegistrationFragment.OnFragmentInteractionListener ,
        PhoneRegistrationFragment.OnFragmentInteractionListener {

    private FrameLayout frameContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        UtilFunction.doExtends(BasicmainLayout , this , R.layout.activity_registration);

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
    public void goToPhoneVerification() {
        changeFragment(new PhoneVerificationFragment());
    }

    @Override
    public void goToRegistrationFragment() {
        changeFragment(new RegistrationFragment());

    }
}
