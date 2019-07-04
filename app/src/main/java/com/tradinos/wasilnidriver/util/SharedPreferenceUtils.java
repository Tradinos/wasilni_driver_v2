package com.tradinos.wasilnidriver.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceUtils {
    private static final String MY_PREFS_NAME = "driver_app";
    private static SharedPreferences prefs ;
    private static SharedPreferences.Editor editor ;
    public static SharedPreferences  getPreferencesInstance(Context context) {
        if(prefs == null) {
            prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        }
        return prefs;
    }

    public static SharedPreferences.Editor  getEditorInstance(Context context) {
        getPreferencesInstance(context);
        if(editor == null) {
            editor = prefs.edit();
        }
        return editor;
    }


}
