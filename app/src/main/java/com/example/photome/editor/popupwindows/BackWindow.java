package com.example.photome.editor.popupwindows;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import com.example.photome.R;

/**
 * 1. crete PopupWindow object;
 * 2. set the background, register event listener and add animation;
 * 3. show PopupWindow.
 */
public class BackWindow extends PopupWindow {

    private static String TAG = MateInfoWindow.class.getSimpleName();

    private View mView;

    private Context mContext;

    private PopupWindow mWindow;

    public BackWindow(Context context, View view) {
        this.mView = view;
        this.mContext = context;

        initPopUp();
    }

    public void initPopUp() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.editor_back_window, null);

        mWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);

        mWindow.setAnimationStyle(R.style.animWindowTranslate);

        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mWindow.setOutsideTouchable(true);

        mWindow.setTouchable(true);
    }

    public void popUpShow() {
        mWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
    }
}
