package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.presenter.LoginPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;
import com.wasilni.wasilnidriverv2.util.UtilFunction;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements
        FormContract,
        View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    TextInputLayout phoneLayout, passwordLayout;
    TextInputEditText phoneEdit, passwordEdit;
    Button registerBtn, loginBtn;

    LoginPresenterImp loginPresenterImp;
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginPresenterImp = new LoginPresenterImp(getActivity());
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.phoneLayout = view.findViewById(R.id.phone_layout);
        this.phoneEdit = view.findViewById(R.id.phone_edit);
        this.passwordLayout = view.findViewById(R.id.password_layout);
        this.passwordEdit = view.findViewById(R.id.password_edit);

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
                this.mListener.goToPhoneRegistration();
                break;
            case R.id.login:
                if(this.validate()) {
                    this.submit();
                }
                break;
        }
    }

    @Override
    public boolean validate() {
        resetValidation();

        boolean valid = true;
        String requiredFieldStrId = getString(R.string.required_field);
        if(phoneEdit.getText().toString().isEmpty())
        {
            valid = false;
            UtilFunction.setErrorToInputLayout(phoneLayout, requiredFieldStrId);
        }

        if(passwordEdit.getText().toString().isEmpty())
        {
            valid = false;
            UtilFunction.setErrorToInputLayout(passwordLayout, requiredFieldStrId);
        }

        return valid;
    }

    @Override
    public void resetValidation() {
        UtilFunction.removeErrorToInputLayout(phoneLayout);
        UtilFunction.removeErrorToInputLayout(passwordLayout);
    }

    @Override
    public boolean submit() {
        User user = new User();
        user.setPassword(this.passwordEdit.getText().toString());
        user.setPhone_number(this.phoneEdit.getText().toString());
        this.loginPresenterImp.sendToServer(user);
//        mListener.goToMainView();
        return true;
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
        void goToPhoneRegistration();
        void goToMainView();
    }
}
