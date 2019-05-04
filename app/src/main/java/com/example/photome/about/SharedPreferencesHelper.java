package com.example.photome.about;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.photome.R;

/**
 * create by zomi. 2019.5.2
 * Description: Used for setting.
 */

public final class SharedPreferencesHelper {
    private static final String TAG = SharedPreferencesHelper.class.getSimpleName();

    /**
     * Retrieves the default SharedPreference object used to store or read values in this app.
     */
    public static SharedPreferences getSharedPrefsInstance(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * With default option how to save the Format and quality of the photo.
     * It should have JPG 100%, JPG 95%, JPG 80%, JPG 60%.
     * @param context
     * @return
     */
    public static int getPhotoSaveQuality(Context context){
        String value = getSharedPrefsInstance(context).
                getString(context.getResources().getString(R.string.pref_general_key_jpeg_quality), "100");
        if (!value.equals("")){
            return Integer.parseInt(value);
        } else {
            return 95;
        }
    }

    /**
     * Default option the photo resolution.
     * it have 4 parameters: PPI 72, PPI150, PPI 300, PPI 450.
     * @param context
     * @return
     */
    public static int getPhotoResolution(Context context){
        String value = getSharedPrefsInstance(context).
                getString(context.getResources().getString(R.string.pref_general_key_photo_resolution), "72");
        if (!value.equals("")){
            return Integer.parseInt(value);
        } else {
            return 72;
        }
    }

    /**
     * Set the maximal photo size.
     * Max pixel: 4000, 2000, 1920, 1366, 800, 0(no change).
     * @param context
     * @return
     */
    public static int getPhotoSize(Context context){
        String value = getSharedPrefsInstance(context).
                getString(context.getResources().getString(R.string.pref_general_key_photo_size), "0");
        if (!value.equals("")){
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }
}
