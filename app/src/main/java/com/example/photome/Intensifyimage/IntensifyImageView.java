package com.example.photome.Intensifyimage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class IntensifyImageView extends View implements BaseImageView {

    private Constants mConstants;

    // Crater
    public IntensifyImageView(Context context) {
        this(context, null, 0);
    }

    public IntensifyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IntensifyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        mConstants = new Constants();
    }

    // For Image base info /////////
    @Override
    public void setImage(String path) {

    }

    @Override
    public void setImage(File file) {

    }

    @Override
    public int getImageWidth() {
        return 0;
    }

    @Override
    public int getImageHeight() {
        return 0;
    }

    // For Scale ////////////
    @Override
    public void setScale(float scale) {

    }

    @Override
    public float getScale() {
        return 0;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {

    }

    @Override
    public ScaleType getScaleType(){
        return ScaleType.FIT_AUTO;
    }


    @Override
    public void addScale(float scale, float focusX, float focusY) {

    }

    @Override
    public void nextScale(float focusX, float focusY) {

    }

    @Override
    public void scaleEnd() {

    }

    // For normal Gesture //////////////
    @Override
    public void scroll(float distanceX, float distanceY) {

    }

    @Override
    public void fling(float velocityX, float velocityY) {

    }

    @Override
    public void onTouch(float x, float y) {

    }

    @Override
    public void singleTap(float x, float y) {

    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void longPress(float x, float y) {

    }
}
