package com.wasilni.wasilnidriverv2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wasilni.wasilnidriverv2.ui.Fragments.CarInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.CivilInfoRegistrationFragment;
import com.wasilni.wasilnidriverv2.ui.Fragments.PersonalInfoRegistrationFragment;

import java.util.ArrayList;

public class RegistrationViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment mCurrentFragment;
    private ArrayList<Fragment> fragments;

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public RegistrationViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
        this.fragments.add(new PersonalInfoRegistrationFragment());
        this.fragments.add(new CivilInfoRegistrationFragment());
        this.fragments.add(new CarInfoRegistrationFragment());

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
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
