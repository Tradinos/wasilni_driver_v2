package com.wasilni.wasilnidriverv2.ui.Dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.Payment;
import com.wasilni.wasilnidriverv2.mvp.presenter.PayPresenterImp;
import com.wasilni.wasilnidriverv2.mvp.view.PayContract;

/**
 * A simple {@link BottomSheetDialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TripSummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TripSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripSummaryFragment extends BottomSheetDialogFragment {
    private ImageView passengerPictureIV;
    private TextView rateBtn;
    private Button submit ;
    public EditText moneyCost ;
    private OnFragmentInteractionListener mListener;
    private Booking dataToShow , mBooking;

    public void setDataToShow(Booking dataToShow) {
        this.dataToShow = dataToShow;
    }

    public void setmBooking(Booking mBooking) {
        this.mBooking = mBooking;
    }

    public TripSummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TripSummaryFragment.
     */
    public static TripSummaryFragment newInstance() {
        TripSummaryFragment fragment = new TripSummaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        this.setCancelable(false);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.passengerPictureIV = view.findViewById(R.id.passenger_photo);
        this.rateBtn = view.findViewById(R.id.rate_btn);
        this.submit = view.findViewById(R.id.done_btn);
        this.moneyCost = view.findViewById(R.id.delivered_money);
        final PayContract.PayPresenter presenter = new PayPresenterImp(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendToServer(new Payment(mBooking , moneyCost.getText().toString()));

            }
        });

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
    }
}
