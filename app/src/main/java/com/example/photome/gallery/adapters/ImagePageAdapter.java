package com.example.photome.gallery.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.photome.Intensifyimage.BaseImageView;
import com.example.photome.Intensifyimage.Constants;
import com.example.photome.Intensifyimage.IntensifyImageView;

import java.io.IOException;
import java.util.ArrayList;

public class ImagePageAdapter extends PagerAdapter {

    private static String TAG = ImagePageAdapter.class.getSimpleName();

    private Context mContext;
    private int mPosition;
    private ArrayList<String> mPhotoPathList = new ArrayList<String>();


    public ImagePageAdapter(Context context, ArrayList<String> photoPathList, int position) {
        mContext = context;
        mPosition = position;
        mPhotoPathList = photoPathList;
    }

    @Override
    public int getCount() {
        return mPhotoPathList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        IntensifyImageView imageView = new IntensifyImageView(container.getContext());
        imageView.setScaleType(BaseImageView.ScaleType.FIT_AUTO);
        imageView.setImage(mPhotoPathList.get(mPosition));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}