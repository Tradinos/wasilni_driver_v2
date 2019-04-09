package com.wasilni.wasilnidriverv2.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.view.FormContract;

import java.util.ArrayList;

import static com.wasilni.wasilnidriverv2.util.UtilFunction.hideSoftKeyboard;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhoneVerificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhoneVerificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneVerificationFragment extends Fragment implements
        FormContract,
        View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    ArrayList<Integer> oneDigitEditTexts ;
    boolean isLastKeyStrokeBackspace = false;

    // Widgets
    private Button verifyBtn;


    public PhoneVerificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PhoneVerificationFragment.
     */
    public static PhoneVerificationFragment newInstance() {
        PhoneVerificationFragment fragment = new PhoneVerificationFragment();
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
        return inflater.inflate(R.layout.fragment_phone_verification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        verifyBtn = view.findViewById(R.id.verify);
        verifyBtn.setOnClickListener(this);

        oneDigitEditTexts = new ArrayList<>();
        oneDigitEditTexts.add(R.id.digit_1);
        oneDigitEditTexts.add(R.id.digit_2);
        oneDigitEditTexts.add(R.id.digit_3);
        oneDigitEditTexts.add(R.id.digit_4);

        for (final Integer editTextId : oneDigitEditTexts) {
            EditText currentEditText = (EditText)view.findViewById(editTextId);

            currentEditText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    resetValidation();
                    if(isLastKeyStrokeBackspace && oneDigitEditTexts.indexOf(v.getId()) > 0 && keyCode == KeyEvent.KEYCODE_DEL){
                        view.findViewById(oneDigitEditTexts.get(oneDigitEditTexts.indexOf(v.getId()) - 1)).requestFocus();
                        isLastKeyStrokeBackspace = false;
                        return false;
                    }

                    if(keyCode == KeyEvent.KEYCODE_DEL){
                        isLastKeyStrokeBackspace = true;
                    }

                    return false;
                }
            });

            currentEditText.addTextChangedListener(new TextWatcher() {
                String oldText ;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    oldText = s.toString();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("SAED", "onTextChanged: " + s.toString());
                    isLastKeyStrokeBackspace = false;
                    if(oldText.isEmpty() && !s.toString().isEmpty()){
                        if(oneDigitEditTexts.indexOf(editTextId) + 1 < oneDigitEditTexts.size()) {
                            view.findViewById(oneDigitEditTexts.get(oneDigitEditTexts.indexOf(editTextId) + 1)).requestFocus();
                        } else {
                            hideSoftKeyboard(getActivity());
                        }
                    } else if(!oldText.isEmpty() && s.toString().isEmpty()){
                        isLastKeyStrokeBackspace = true;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
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
            case R.id.verify:
                if(validate()) {
                    this.submit();
                }
                break;
        }
    }

    @Override
    public boolean validate(){
        boolean valid = true;
        for (final Integer editTextId : oneDigitEditTexts) {
            EditText currentET = (EditText)getView().findViewById(editTextId);
            if(currentET.getText().toString().isEmpty()){
                valid = false;
                currentET.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_white_border_red));
            }
        }
        return valid;
    }

    @Override
    public void resetValidation(){
        for (final Integer editTextId : oneDigitEditTexts) {
            EditText currentET = (EditText)getView().findViewById(editTextId);
            if(currentET.getText().toString().isEmpty()){
                currentET.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_white_border_green));
            }
        }
    }

    @Override
    public boolean submit() {
        this.mListener.goToRegistrationFragment();
        return true;
    }

    private String getVerificationcCode(){
        StringBuilder code = new StringBuilder();
        for (final Integer editTextId : oneDigitEditTexts) {
            EditText currentET = (EditText) getView().findViewById(editTextId);
            code.append(currentET.getText().toString());
        }
        return code.toString();
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
        void goToRegistrationFragment();
    }
}
