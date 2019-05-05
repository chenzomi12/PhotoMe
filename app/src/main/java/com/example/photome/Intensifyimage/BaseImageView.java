package com.example.photome.Intensifyimage;

import java.io.File;

/**
 * Base ImageView Interface
 */
public interface BaseImageView {

    int DURATION_ZOOM = 300;

    // For GlideImageLoader
    void setImage(String path);

    // For ImageLoader
    void setImage(File file);

    // For Image base info
    int getImageWidth();

    int getImageHeight();

    // For Scale
    enum ScaleType {
        NONE(0), // should not occur.
        FIT_AUTO(1), // default.
        FIT_CENTER(2),
        CENTER(3),
        CENTER_INSIDE(4);

        ScaleType(int ni) {
            nativeInt = ni;
        }

        static ScaleType valueOf(int value) {
            if (value < 0 || value >= values().length) {
                return FIT_CENTER;
            }
            return values()[value];
        }

        final int nativeInt;
    }

    void setScale(float scale);

    float getScale();

    void setScaleType(ScaleType scaleType);

    ScaleType getScaleType();

    void addScale(float scale, float focusX, float focusY);

    void nextScale(float focusX, float focusY);

    void scaleEnd();

    // For normal Gesture
    void scroll(float distanceX, float distanceY);

    void fling(float velocityX, float velocityY);

    void onTouch(float x, float y);

    void singleTap(float x, float y);

    void doubleTap(float x, float y);

    void longPress(float x, float y);

    // ImageView Listener interface
    interface OnSingleTapListener {
        void onSingleTap(boolean inside);
    }

    interface OnDoubleTapListener {
        boolean onDoubleTap(boolean inside);
    }

    interface OnLongPressListener {
        void onLongPress(boolean inside);
    }

    interface OnScaleChangeListener {
        void onScaleChange(float scale);
    }

}
