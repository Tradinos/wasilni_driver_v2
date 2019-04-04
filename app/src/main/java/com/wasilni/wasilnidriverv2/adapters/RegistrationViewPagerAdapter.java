package com.wasilni.wasilnidriverv2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wasilni.wasilnidriverv2.ui.Fragments.CarInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.CivilInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PersonalInfoRegistrationFragment;

public class RegistrationViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment mCurrentFragment;

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public RegistrationViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }


    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new PersonalInfoRegistrationFragment(); //ChildFragment1 at position 0
            case 1:
                return new CivilInfoRegistrationFragment(); //ChildFragment1 at position 0
            case 2:
                return new CarInfoRegistrationFragment(); //ChildFragment1 at position 0
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return 3;
    }
}
