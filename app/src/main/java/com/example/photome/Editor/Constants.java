package com.example.photome.Editor;

import android.util.Log;

import com.example.photome.R;

import java.util.ArrayList;
import java.util.Map;


/**
 * Create by zomi. 2019.5.2
 * Update by zomi. 2019.5.3
 * This class is store the tools filter icons and its name.
 */
public class Constants {
    private static final String TAG = "Editor Constant";

    ArrayList<Integer> mBasicEffectToolsIcon;
    ArrayList<Integer> mAdvanceEffectToolsIcon;
    ArrayList<String> mBasicEffectToolsName;
    ArrayList<String> mAdvanceEffectToolsName;

    Map<String, Integer> mMapBasicTools;
    Map<String, Integer> mMapAdvanceTools;

    private void setBasicEffectToolArray() {
        mBasicEffectToolsIcon = new ArrayList<Integer>();
        // 调整
        mBasicEffectToolsIcon.add(R.drawable.ic_light_24);// 光效
        mBasicEffectToolsIcon.add(R.drawable.ic_curve_24);// 曲线
        mBasicEffectToolsIcon.add(R.drawable.ic_crop_24);// 裁剪
        mBasicEffectToolsIcon.add(R.drawable.ic_rotate_24);// 旋转
        mBasicEffectToolsIcon.add(R.drawable.ic_tone_24);// 色调
        mBasicEffectToolsIcon.add(R.drawable.ic_scale_24);// 色阶
        mBasicEffectToolsIcon.add(R.drawable.ic_details_24);// 突出细节
        mBasicEffectToolsIcon.add(R.drawable.ic_hsl_24);// HSL
    }

    ;

    private void setBasicEffectToolsName() {
        mBasicEffectToolsName = new ArrayList<String>();
        // 调整
        mBasicEffectToolsName.add("Light");// 光效
        mBasicEffectToolsName.add("Curve");// 曲线
        mBasicEffectToolsName.add("Crop");// 裁剪
        mBasicEffectToolsName.add("Rotate");// 旋转
        mBasicEffectToolsName.add("Tone");// 色调
        mBasicEffectToolsName.add("Scale");// 色阶
        mBasicEffectToolsName.add("Details");// 突出细节
        mBasicEffectToolsName.add("HSL");// HSL
    }

    private void setAdvanceEffectToolArray() {
        mAdvanceEffectToolsIcon = new ArrayList<Integer>();
        // 特效
        mAdvanceEffectToolsIcon.add(R.drawable.ic_diamond_24);// 光晕
        mAdvanceEffectToolsIcon.add(R.drawable.ic_blur_24);// 模糊
        mAdvanceEffectToolsIcon.add(R.drawable.ic_local_24);// 局部
        mAdvanceEffectToolsIcon.add(R.drawable.ic_difference_24);// 色差
        mAdvanceEffectToolsIcon.add(R.drawable.ic_hdr_24);// HDR
        mAdvanceEffectToolsIcon.add(R.drawable.ic_text_24);// 文字
        mAdvanceEffectToolsIcon.add(R.drawable.ic_brush_24);// 笔刷
        mAdvanceEffectToolsIcon.add(R.drawable.ic_pixel_24);// 像素化
        mAdvanceEffectToolsIcon.add(R.drawable.ic_exposure_24);// 双重曝光
        mAdvanceEffectToolsIcon.add(R.drawable.ic_face_24);// 人脸识别
        mAdvanceEffectToolsIcon.add(R.drawable.ic_compress_24);// 压缩
    }

    ;

    private void setAdvanceEffectToolsName() {
        mAdvanceEffectToolsName = new ArrayList<String>();
        // 特效
        mAdvanceEffectToolsName.add("Halo");// 光晕
        mAdvanceEffectToolsName.add("Blur");// 模糊
        mAdvanceEffectToolsName.add("Local");// 局部
        mAdvanceEffectToolsName.add("difference");// 色差
        mAdvanceEffectToolsName.add("HDR");// HDR
        mAdvanceEffectToolsName.add("Text");// 文字
        mAdvanceEffectToolsName.add("Brush");// 笔刷
        mAdvanceEffectToolsName.add("Pixelated");// 像素化
        mAdvanceEffectToolsName.add("Double exposure");// 双重曝光
        mAdvanceEffectToolsName.add("Face recognition");// 人脸识别
        mAdvanceEffectToolsName.add("Compress");// 压缩
    }

    /**
     * @return
     */
    public ArrayList<Integer> getBasicEffectToolArray() {
        setBasicEffectToolArray();

        if (mBasicEffectToolsIcon.size() == 0) {
            Log.e(TAG, "empty effect tool.");
        }

        return mBasicEffectToolsIcon;
    }

    public ArrayList<Integer> getAdvanceEffectToolArray() {
        setAdvanceEffectToolArray();

        if (mAdvanceEffectToolsIcon.size() == 0) {
            Log.e(TAG, "empty effect tool.");
        }

        return mAdvanceEffectToolsIcon;
    }

    public ArrayList<String> getBasicEffectToolsName() {
        setBasicEffectToolsName();

        if (mBasicEffectToolsName.size() == 0) {
            Log.e(TAG, "empty effect tool.");
        }
        return mBasicEffectToolsName;
    }

    public ArrayList<String> getAdvanceEffectToolsName() {
        setAdvanceEffectToolsName();

        if (mAdvanceEffectToolsName.size() == 0) {
            Log.e(TAG, "empty effect tool.");
        }

        return mAdvanceEffectToolsName;
    }

    public Map<String, Integer> getMapBasicTool() {
        if (mBasicEffectToolsIcon.size() == mBasicEffectToolsName.size()) {
            for (int i = 0; i < mBasicEffectToolsIcon.size(); i++) {
                mMapBasicTools.put(mBasicEffectToolsName.get(i), mBasicEffectToolsIcon.get(i));
            }
        } else {
            Log.e(TAG, "empty effect tool.");
        }
        return mMapBasicTools;
    }


    public Map<String, Integer> getMapAdvanceTool() {
        if (mAdvanceEffectToolsIcon.size() == mAdvanceEffectToolsName.size()) {
            for (int i = 0; i < mAdvanceEffectToolsIcon.size(); i++) {
                mMapAdvanceTools.put(mAdvanceEffectToolsName.get(i), mAdvanceEffectToolsIcon.get(i));
            }
        } else {
            Log.e(TAG, "empty effect tool.");
        }
        return mMapAdvanceTools;
    }
}
