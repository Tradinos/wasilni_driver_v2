package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhoneRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhoneRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneRegistrationFragment extends Fragment implements
        FormContract,
        View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    TextInputLayout phoneLayout;
    TextInputEditText phoneEdit;
    Button registerBtn;

    public PhoneRegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PhoneRegistrationFragment.
     */
    public static PhoneRegistrationFragment newInstance() {
        PhoneRegistrationFragment fragment = new PhoneRegistrationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SAED", "onCreate: I don't know what I am doing but I am fine thanks for asking");
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.phoneLayout = view.findViewById(R.id.phone_layout);
        this.phoneEdit = view.findViewById(R.id.phone_edit);
        this.registerBtn = view.findViewById(R.id.register);

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
                if(this.validate()){
                    this.submit();
                }
                break;
        }
    }

    @Override
    public boolean validate(){
        resetValidation();

        boolean valid = true;
        if(phoneEdit.getText().toString().isEmpty())
        {
            valid = false;
            phoneLayout.setErrorEnabled(true);
            phoneLayout.setError(getString(R.string.required_field));
        }
        return valid;
    }

    @Override
    public void resetValidation(){
        phoneLayout.setErrorEnabled(false);
        phoneLayout.setError(null);

    }

    @Override
    public void submit() {
        this.mListener.goToPhoneVerification();

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
        void goToPhoneVerification();
    }
}
