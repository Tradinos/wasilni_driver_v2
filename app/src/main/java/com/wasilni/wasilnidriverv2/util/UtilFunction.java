package com.wasilni.wasilnidriverv2.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.wasilni.wasilnipassengerv2.network.RetorfitSingelton.URL;
import static com.wasilni.wasilnipassengerv2.util.Constants.ETAG;


public class UtilFunction {

    public static void setFontBAHIJ(Activity activity) {
        Calligrapher calligrapher = new Calligrapher(activity);
        calligrapher.setFont(activity, "bahij_thesansarabic_bold.ttf", true);
    }

    public static void doExtends(ViewGroup mainLayout, Activity activity, int layoutID) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutID, null, false);
        mainLayout.addView(contentView, 0);
    }
    public static LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(
                    strAddress,
                    5);
            if (address == null) {
                return null;
            }
            if(address.size()>0) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }





    public static void showToast(Activity activity , int messageID){
        Toast.makeText(activity ,messageID,Toast.LENGTH_SHORT ).show();
    }
    public static void showToast(Activity activity , String message){
        Toast.makeText(activity ,message,Toast.LENGTH_SHORT ).show();
    }

    private void showDialog(Activity activity){
        final AlertDialog.Builder builder1 = new AlertDialog.Builder( activity);
        String s ;
        s = "" ;
        builder1.setMessage(s);
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "نعم",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton("لا",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                //  holder.mButton.setClickable(true);
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void dexterLib(Activity activity){
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        /* ... */

                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        /* ... */
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        /* ... */

                    }
                }).check();
    }
    static public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgBytes , android.util.Base64.DEFAULT) ;
    }

    public static  BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    public static void p(){
        Log.e(ETAG,"00000") ;
    }
    public static void p(String s){
        Log.e(ETAG,s) ;
    }

    public static String getFullURL(String url){
        return URL + url;
    }

}
