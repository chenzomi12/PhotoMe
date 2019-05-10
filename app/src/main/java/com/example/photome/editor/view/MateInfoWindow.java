package com.example.photome.editor.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import com.example.photome.R;
import com.example.photome.exif2.ExifInterface;
import com.example.photome.exif2.ExifTag;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 1. crete PopupWindow object;
 * 2. set the background, register event listener and add animation;
 * 3. show PopupWindow.
 */
public class MateInfoWindow extends BaseWindow {

    private static String TAG = MateInfoWindow.class.getSimpleName();

    private ExifInterface mExif;

    public MateInfoWindow(Context context, View view, Window window, String path) {
        this.mView = view;
        this.mContext = context;
        this.mWindow = window;

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.editor_mateinfo_window, null);
        mPopWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);

        initWindow();
        initData(path);
    }

    private void initData(String assetname) {
        InputStream stream;
        try {
            stream = mContext.getAssets().open(assetname);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        processInputStream(stream);

    }

    private void processInputStream(InputStream stream) {
        mExif = new ExifInterface();
        List<ExifTag> all_tags = null;

        // check the input stream(path) is empty or not.
        if( null != stream ) {
            long t1 = System.currentTimeMillis();
            try {
                mExif.readExif( stream, ExifInterface.Options.OPTION_ALL );
            } catch( IOException e ) {
                e.printStackTrace();
                mExif = null;
            }
            long t2 = System.currentTimeMillis();
            Log.d( TAG, "parser time: " + ( t2 - t1 ) + "ms" );

            if(null != mExif) {
                all_tags = mExif.getAllTags();
                Log.d(TAG, "total tags: " + (all_tags != null ? all_tags.size() : 0));
            }
        }

        // if have the exif info then go on.
        if( null != mExif ) {

        }
    }


}
