package com.example.photome.Intensifyimage;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Intensify should blind to ImageView.
 */
public class IntensifyAttacher implements View.OnTouchListener {

    private static String TAG = IntensifyAttacher.class.getSimpleName();

    private Context mContext;

    private IntensifyImageView mIntensifyView;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;

    public IntensifyAttacher(IntensifyImageView intensifyView) {
        mIntensifyView = intensifyView;
        mContext = intensifyView.getContext();
        mScaleGestureDetector = new ScaleGestureDetector(mContext, new OnScaleGestureAdapter());
        mGestureDetector = new GestureDetector(mContext, new OnGestureAdapter());

        mIntensifyView.setOnTouchListener(this);
    }

    // Listen when event touch in the view.
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event) | mScaleGestureDetector.onTouchEvent(event);
    }

    // On Scale Gesture
    private class OnScaleGestureAdapter extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mIntensifyView.addScale(detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            mIntensifyView.scaleEnd();
        }
    }

    // On Normal Gesture
    private class OnGestureAdapter extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            mIntensifyView.onTouch(e.getX(), e.getY());
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mIntensifyView.doubleTap(e.getX(), e.getY());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mIntensifyView.scroll(distanceX, distanceY);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mIntensifyView.fling(-velocityX, -velocityY);
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            mIntensifyView.singleTap(e.getX(), e.getY());
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mIntensifyView.longPress(e.getX(), e.getY());
        }
    }
}
