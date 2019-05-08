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

import com.example.photome.R;

/**
 * 1. crete PopupWindow object;
 * 2. set the background, register event listener and add animation;
 * 3. show PopupWindow.
 */
public class MateInfoWindow extends PopupWindow {
    private static String TAG = MateInfoWindow.class.getSimpleName();

//    public void MateInfoWindow(Context context) {
//
//        View contentView = LayoutInflater.from(context).inflate(R.layout.editor_back_window, null);
//
//        PopupWindow window = new PopupWindow(contentView, 100, 100, true);
//
//        window.setAnimationStyle(R.style.animWindowTranslate);
//
//
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        window.setOutsideTouchable(true);
//
//        window.setTouchable(true);
//
//        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getWindow().getAttributes();
//                lp.alpha = 1.0f;
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                getWindow().setAttributes(lp);
//            }
//        });
//
//        window.showAtLocation(activityPopup, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.3f;
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        getWindow().setAttributes(lp);
//
//
//    }
//
//    private Window getWindow() {
//    }
}
