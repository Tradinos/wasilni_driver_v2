package com.wasilni.wasilnidriverv2.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Bank;
import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.BrandModel;
import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnegative;

public class ObjectNameAdapter extends ArrayAdapter{
    private LayoutInflater mInfalter;
    private int mResource;
    private Object labelObject;

    public static final int DISABLED_ITEM_INDEX = 0;

    public ObjectNameAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ObjectNameAdapter(@NonNull Context context, int resource,  ArrayList<Object> objects, String label){
        super(context, resource, objects);
        this.mResource = resource;
        this.labelObject = new Color(-1, label);
        this.insertLabelObject();

        this.mInfalter = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object o = this.getItem(position);
        View row = convertView;
        String name = "";


        if(row == null) {
            row = mInfalter.inflate(mResource, null);
        }
        TextView nameTV = (TextView) row.findViewById(R.id.name);


        if(o != null)
        {
            if(o instanceof Brand){
                name = ((Brand)o).getName();
            } else if(o instanceof BrandModel){
                name = ((BrandModel)o).getName();
            } else if(o instanceof Nationality){
                name = ((Nationality)o).getName();
            } else if(o instanceof Color){
                name = ((Color)o).getName();
            } else if(o instanceof Bank){
                name = ((Bank)o).getName();
            } else if(o instanceof String){
                name = (String)o;
            }

            // if first item set as disabled
            if(position == 0) {
                nameTV.setTextColor(android.graphics.Color.GRAY);
            }
            nameTV.setText(name);
        }
        return row;
    }


    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View row = mInfalter.inflate(mResource, parent, false);

        TextView nameTV = (TextView) row.findViewById(R.id.name);
        String name = "";
        Object o = getItem(position);

        if(o instanceof Brand){
            name = ((Brand)o).getName();
        } else if(o instanceof BrandModel){
            name = ((BrandModel)o).getName();
        } else if(o instanceof Nationality){
            name = ((Nationality)o).getName();
        } else if(o instanceof Color){
            name = ((Color)o).getName();
        } else if(o instanceof Bank){
            name = ((Bank)o).getName();
        } else if(o instanceof String){
            name = (String)o;
        }

        // if first item set as disabled
        if(position == 0) {
            nameTV.setTextColor(android.graphics.Color.GRAY);
        }
        nameTV.setText(name);
        return row;

    }

    @Override
    public boolean isEnabled(int position) {
        if(position <= DISABLED_ITEM_INDEX)
        {
            return false;
        }
        return true;
    }

    public void updateItems(List<Object> items){
        this.clear();
        this.addAll(items);

        this.insertLabelObject();
        this.notifyDataSetChanged();
    }

    public void insertLabelObject(){
        this.insert(labelObject,0);
    }

}
