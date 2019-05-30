package com.wasilni.wasilnidriverv2.ui.Dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wasilni.wasilnidriverv2.R;

import java.util.ArrayList;

import static com.wasilni.wasilnidriverv2.DriverApplication.updateResources;

public class RatingDialog extends DialogFragment implements View.OnClickListener {

    ArrayList<Integer> stars ;
    ArrayList<ImageView> starsIV ;

    public static RatingDialog newInstance() {
        RatingDialog fragment = new RatingDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(getActivity() , "ar");

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        this.stars = new ArrayList<>();
        this.starsIV = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.rating_dialog, null);


        builder.setView(view);
        Dialog dialog = builder.create();

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        setStars(view);

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setStars(View view){
        this.stars.add(R.id.star_1);
        this.stars.add(R.id.star_2);
        this.stars.add(R.id.star_3);
        this.stars.add(R.id.star_4);
        this.stars.add(R.id.star_5);


        for (Integer starId : this.stars) {
            ImageView iv = view.findViewById(starId);
            this.starsIV.add(iv);
            iv.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        for (Integer starId : this.stars) {
            if(v.getId() == starId){
                this.markSelectedStars(v.getId());
                return;
            }
        }

        switch (v.getId()){

        }
    }

    private void markSelectedStars(int starId){
        for (int i = 0 ; i < this.stars.size(); i++)
        {
            Picasso.get()
                    .load(R.drawable.star_filled)
                    .into(this.starsIV.get(i));
        }

        for (int i = this.stars.indexOf(starId) + 1 ; i < this.stars.size(); i++)
        {
            Picasso.get()
                    .load(R.drawable.star_stroke)
                    .into(this.starsIV.get(i));
        }
    }
}
