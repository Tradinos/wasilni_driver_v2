package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.ui.adapters.RegistrationViewPagerAdapter;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    private ViewPager viewPager;
    private RegistrationViewPagerAdapter registrationViewPagerAdapter;
    private Button registerBtn;
    private User user;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegistrationFragment.
     */
    public static RegistrationFragment newInstance() {
        RegistrationFragment fragment = new RegistrationFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.registrationViewPagerAdapter = new RegistrationViewPagerAdapter(getActivity().getSupportFragmentManager());

        this.viewPager = view.findViewById(R.id.register_view_pager);
        this.registerBtn = view.findViewById(R.id.register);


        this.viewPager.setOffscreenPageLimit(2);

        this.viewPager.setAdapter(this.registrationViewPagerAdapter);


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(this.viewPager, true);

        this.registerBtn.setOnClickListener(this);
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
            case R.id.register:
                this.validate();
//                if(((FormContract)this.registrationViewPagerAdapter.getCurrentFragment()).validate()) {
//                    UtilFunction.showToast(getActivity(),"I am valid");
//                } else {
//                    UtilFunction.showToast(getActivity(),"I am not valid damn it");
//                }
            break;
        }
    }

    private void validate(){

        boolean valid = true;
        for (int i = 0 ; i < 3 ; i++){
            if(!((FormContract)this.registrationViewPagerAdapter.getItem(i)).submit()) {
                valid = false;
                this.viewPager.setCurrentItem(i,true);
                break;
            }
        }
        if(valid)
        {
            this.mListener.completeRegistration();
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
        void goToInterviewBooking();
        void completeRegistration();
    }
}
