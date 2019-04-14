package com.wasilni.wasilnidriverv2.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.wasilni.wasilnidriverv2.R;
import com.wasilni.wasilnidriverv2.mvp.model.Bank;
import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.BrandModel;
import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.model.Location;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnegative;

public class ObjectNameAdapter extends ArrayAdapter{
    private LayoutInflater mInfalter;
    private int mResource;
    private Object labelObject;
    private List<Object> mObjects;
    private ArrayList<Location> suggestion = new ArrayList<>();

    public static final int DISABLED_ITEM_INDEX = 0;

    public ObjectNameAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ObjectNameAdapter(@NonNull Context context, int resource,  ArrayList<Object> objects){
        super(context, resource, objects);
        this.mResource = resource;
        this.labelObject = null;
        this.mObjects = objects;

        this.mInfalter = LayoutInflater.from(context);

    }


    public ObjectNameAdapter(@NonNull Context context, int resource,  ArrayList<Object> objects, String label){
        super(context, resource, objects);
        this.mResource = resource;
        this.labelObject = new Color(-1, label);
        this.insertLabelObject();
        this.mObjects = objects;

        this.mInfalter = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object o = this.getItem(position);
        View row = convertView;
        String name = "";
        TextView nameTV ;


        if(row == null) {
            row = mInfalter.inflate(mResource, null);
        }
        nameTV = (TextView) row.findViewById(R.id.name);
        if(o instanceof Location) {
            Log.d("SAED", "getview: " + ((Location) o).getName());

        }


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
            } else if(o instanceof Location){
                name = ((Location)o).getName();
            } else if(o instanceof String){
                name = (String)o;
            }

            // if first item set as disabled
            if(position == 0 && labelObject != null) {
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
        } else if(o instanceof Location){
            name = ((Location)o).getName();
        } else if(o instanceof Bank){
            name = ((Bank)o).getName();
        } else if(o instanceof String){
            name = (String)o;
        }

        // if first item set as disabled
        if(position == 0 && labelObject != null) {
            nameTV.setTextColor(android.graphics.Color.GRAY);
        }
        nameTV.setText(name);
        return row;

    }

    @Override
    public boolean isEnabled(int position) {
        if(labelObject == null)
            return true;

        if(position <= DISABLED_ITEM_INDEX)
        {
            return false;
        }
        return true;
    }

    public void updateItems(List<Object> items){
        this.mObjects = items;

        this.clear();
        this.addAll(items);
        Log.d("SAED", "updateItems: count " + this.getCount());
        this.insertLabelObject();
        this.notifyDataSetChanged();
    }

    private void insertLabelObject(){
        if(labelObject != null)
        {
            this.insert(labelObject,0);
        }
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
//        return super.getFilter();
    }

    private Filter nameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            if(constraint != null)
            {
//                suggestion.clear();
//                Log.d("SAED", "performFiltering: " + mObjects.size());
//                for (Object object : mObjects) {
//                    Location location = (Location)object;
//                    Log.d("SAED", "performFiltering: location name " + location.getName());
//
//                    Log.d("SAED", "performFiltering: constraint " + constraint);
//                    Log.d("SAED", "performFiltering: if result " + location.getName().toLowerCase()
//                            .startsWith(constraint.toString().toLowerCase()));
//                    if (location.getName().toLowerCase()
//                            .startsWith(constraint.toString().toLowerCase())) {
//                        Log.d("SAED", "performFiltering: location name inside " + location.getName());
//                        Log.d("SAED", "performFiltering: constraint inside" + constraint);
//                        suggestion.add(location);
//                    }
//                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mObjects;
                filterResults.count = mObjects.size();
//
                return filterResults;

            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Location> filteredList = (ArrayList<Location>) results.values;

            if (results != null && results.count > 0) {
                clear();
                for (Location c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }

        }
    };
}
