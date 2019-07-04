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
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.presenter.RequestActivitionCodePresentreImp;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;
import com.wasilni.wasilnidriverv2.mvp.view.RequestActivitionCodeContract;
import com.wasilni.wasilnidriverv2.util.SharedPreferenceUtils;
import com.wasilni.wasilnidriverv2.util.UtilFunction;
import com.wasilni.wasilnidriverv2.util.UserUtil;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

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

    RequestActivitionCodeContract.RequestActivitionCodePresentre presentre = new RequestActivitionCodePresentreImp(this);
    TextInputLayout phoneLayout;
    TextInputEditText phoneEdit;

    Button registerBtn ;
    TextView loginBtn;

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
        updateResources(getActivity() , "ar");

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
        this.loginBtn = view.findViewById(R.id.login);

        this.registerBtn.setOnClickListener(this);
        this.loginBtn.setOnClickListener(this);
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
                this.submit();
                break;
            case R.id.login:
                mListener.goToLogin();
                break;
        }
    }

    @Override
    public boolean validate(){
        resetValidation();

        boolean valid = true;
        String requiredFieldStrId = getString(R.string.required_field);
        if(phoneEdit.getText().toString().isEmpty())
        {
            valid = false;
            UtilFunction.setErrorToInputLayout(phoneLayout,requiredFieldStrId);
        }
        return valid;
    }

    @Override
    public void resetValidation(){
        UtilFunction.removeErrorToInputLayout(phoneLayout);

    }

    @Override
    public boolean submit() {
        if(this.validate()) {
            UserUtil.getUserInstance().setPhone_number(phoneEdit.getText().toString());
            presentre.sendToServer(UserUtil.getUserInstance());
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public void responseCode200(User user) {
        this.mListener.goToPhoneVerification(this.phoneEdit.getText().toString());
        SharedPreferenceUtils.getEditorInstance(getActivity()).putString("phonenumber",this.phoneEdit.getText().toString()).commit();
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
        void goToPhoneVerification(String phoneNumber);
        void goToLogin();
    }
}
