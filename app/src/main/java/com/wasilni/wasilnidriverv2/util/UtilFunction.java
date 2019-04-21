package com.wasilni.wasilnidriverv2.util;

import android.Manifest;
import android.app.Activity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.kaopiz.kprogresshud.KProgressHUD;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.media.ExifInterface;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.wasilni.wasilnidriverv2.network.ApiServiceInterface;
import com.wasilni.wasilnidriverv2.network.Response;
import com.wasilni.wasilnidriverv2.network.RetorfitSingelton;
import com.wasilni.wasilnidriverv2.ui.Activities.Base.NavigationActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;


import static com.wasilni.wasilnidriverv2.network.RetorfitSingelton.URL;
import static com.wasilni.wasilnidriverv2.util.Constants.ETAG;
import static com.wasilni.wasilnidriverv2.util.Constants.Token;


public class UtilFunction {
    private static int pro = 0 ;
    public static String INTENT_REQUESTER = "intent-requester";
    public static final int REQUEST_CHECK_SETTINGS = 0x1;
    public static KProgressHUD hud ;

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



    public static void sendRegistrationToServer(String refreshedToken , Context context) {
        // Add custom implementation, as needed.
        SharedPreferenceUtils.getEditorInstance(context)
                .putString("fcm_token", refreshedToken);

        // To implement: Only if user is registered, i.e. UserId is available in preference, update token on server.
        int userId = SharedPreferenceUtils.getPreferencesInstance(context).getInt("user_id", -1);
        if (userId != -1) {
            ApiServiceInterface service = RetorfitSingelton.getRetrofitInstance().create(ApiServiceInterface.class);

            Call<Response> call = service.FCMToken(Token, refreshedToken);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<com.wasilni.wasilnidriverv2.network.Response> call, retrofit2.Response<com.wasilni.wasilnidriverv2.network.Response> response) {
                    Log.e("onResponse", "11111 ");

                }

                @Override
                public void onFailure(Call<com.wasilni.wasilnidriverv2.network.Response> call, Throwable t) {
                    Log.e("onFailure", t.getMessage());

                }
            });
        }
    }
    public static void CheckFCMToken(Context context){
        String fcm_token = SharedPreferenceUtils.getPreferencesInstance(context).getString("fcm_token",null);
        if(fcm_token != null){
            sendRegistrationToServer(fcm_token,context);
        }
    }

    public static void showToast(Context activity , int messageID){
        Toast.makeText(activity ,messageID,Toast.LENGTH_SHORT ).show();
    }
    public static void showToast(Context activity , String message){
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

    public static void hideSoftKeyboard(Activity act){
        // Check if no view has focus:
        if(act != null){
            View view = act.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void setYesNoChoice(Activity activity, boolean yes, TextView noTextView, TextView yesTextView, ColorStateList defaultTextColor, int newColor){
        if(activity == null) {
            return;
        }

        yesTextView.setPaintFlags(yesTextView.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));
        noTextView.setPaintFlags(noTextView.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));

        noTextView.setTextColor(defaultTextColor);
        yesTextView.setTextColor(defaultTextColor);

        yesTextView.setTypeface(null, Typeface.NORMAL);
        noTextView.setTypeface(null, Typeface.NORMAL);


        if(yes) {
            underlineWidget(yesTextView);
            yesTextView.setTextColor(activity.getResources().getColor(newColor));
            yesTextView.setTypeface(null, Typeface.BOLD);
        }
        else{
            underlineWidget(noTextView);
            noTextView.setTextColor(activity.getResources().getColor(newColor));
            noTextView.setTypeface(null, Typeface.BOLD);
        }

    }

    public static void underlineWidget(TextView tv){
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void startCameraGalleryDialogFromFragment(final Activity activity, String title, String message, String galleryOption, String cameraOption, final String tempFileName, final Fragment fragment, final int cameraRequestCode, final int galleryRequestCode) {
        if(activity == null || fragment  == null)
            return;

        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                activity);
        myAlertDialog.setTitle(title);
        myAlertDialog.setMessage(message);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        myAlertDialog.setPositiveButton(galleryOption,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Dexter.withActivity(activity)
                        .withPermissions(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {

                                if(report.areAllPermissionsGranted()) {
                                    Intent pictureActionIntent = null;

                                    pictureActionIntent = new Intent(
                                            Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    fragment.startActivityForResult(
                                            pictureActionIntent,
                                            galleryRequestCode);
                                }
                            }
                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                            }
                        })
                        .check();
                }
            });

        myAlertDialog.setNegativeButton(cameraOption,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                    Dexter.withActivity(activity)
                        .withPermissions(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {

                                if(report.areAllPermissionsGranted()) {
                                    Intent intent = new Intent(
                                            MediaStore.ACTION_IMAGE_CAPTURE);
                                    File f = new File(Environment
                                            .getExternalStorageDirectory(),tempFileName);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                            Uri.fromFile(f));

                                    fragment.startActivityForResult(intent,
                                            cameraRequestCode);

                                }
                            }
                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                            }
                        })
                        .check();
                }
            });
        myAlertDialog.show();
    }

    public static void startCameraGalleryDialog(final Activity activity, String title, String message, String galleryOption, String cameraOption, final String tempFileName) {
        if(activity == null)
            return;

        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder( activity);

        myAlertDialog.setTitle(title);
        myAlertDialog.setMessage(message);

        myAlertDialog.setPositiveButton(galleryOption,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Dexter.withActivity(activity)
                            .withPermission(Manifest.permission.CAMERA)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    Intent pictureActionIntent = null;

                                    pictureActionIntent = new Intent(
                                            Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    activity.startActivityForResult(
                                            pictureActionIntent,
                                            1);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {
                                    showToast(activity,"I was denied");
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                                }
                            })
                            .check();

                    }
                });

        myAlertDialog.setNegativeButton(cameraOption,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment
                                .getExternalStorageDirectory(),tempFileName);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(f));

                        activity.startActivityForResult(intent,
                                0);

                    }
                });
        myAlertDialog.show();
    }

    public static String handleCameraIntent(Activity activity, String tempFile){
        Bitmap bitmap = null;
        String imagePath = null;
        File f = new File( Environment.getExternalStorageDirectory(),tempFile);

        if (!f.exists()) {
            Toast.makeText(activity,
                    "Error while capturing image", Toast.LENGTH_LONG)
                    .show();
            return imagePath;
        }
        try {


            return f.getAbsolutePath();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imagePath;
    }

    public static String handleGalleryIntent(Activity activity, Intent data) {
        String selectedImagePath = null;
        Bitmap bitmap = null;
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = activity.getContentResolver().query(selectedImage, filePath,
                    null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            selectedImagePath = c.getString(columnIndex);
            c.close();

            bitmap = BitmapFactory.decodeFile(selectedImagePath); // load
            return selectedImagePath;

        } else {
            Toast.makeText(activity, "Cancelled",
                    Toast.LENGTH_SHORT).show();
        }
        return selectedImagePath;
    }

    public static void setTextFromPickingPictureResult(Activity activity, int requestCode, int cameraCode, int galleryCode, String tempFileName, Intent data, TextView tv){

        String filePath = null;
        if (requestCode == cameraCode) {
            filePath = UtilFunction.handleCameraIntent(activity,  tempFileName);
        }
        else if(requestCode == galleryCode) {
            filePath = UtilFunction.handleGalleryIntent(activity, data);
        }
        if(filePath != null)
        {
            tv.setText(new File(filePath).getName());
        }
    }

    public static void setErrorToInputLayout(TextInputLayout il, String message){
        il.setErrorEnabled(true);
        il.setError(message);
    }

    public static void removeErrorToInputLayout(TextInputLayout il){
        il.setErrorEnabled(false);
        il.setError(null);
    }

    public static float convertDpToPx(Activity activity, float dip){
        Resources r = activity.getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return px;
    }

    public static List<String> generateYears(int from, int to){
        ArrayList<String> years = new ArrayList<>();
        for (Integer i = from ; i <= to; i++){
            years.add(i.toString());
        }
        return years;
    }

    public static String generateDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);

        Date date = new Date(c.getTimeInMillis());

        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dateStr;
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

    public static void showProgressBar(Activity activity) {
        pro++;
        Log.e("TAG", "showProgressBar: " +pro);
        if(hud == null  ){
        hud =KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("الرجاء الانتظار")
                .setDetailsLabel("جاري التحميل")
                .setCancellable(false)
                .setAnimationSpeed(2);
        }
        if(activity != null){
            return;
        }

        if(!hud.isShowing()){
            hud.show();
        }
    }

    public static void hideProgressBar() {
        if(pro >= 0 ) {
            pro--;
            Log.e("TAG", "hideProgressBar: " + pro);
            if (pro == 0) {
                hud.dismiss();
            }
        }
    }
    public static void settingsRequest(final Activity activity) {

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient
        builder.setNeedBle(true);
        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build());
        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.

                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                resolvable.startResolutionForResult(
                                        activity,
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException | ClassCastException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            }
        });
    }
    public static LatLng getLatLng(List<Double> list){
        return new LatLng(list.get(1),list.get(0));
    }

}
