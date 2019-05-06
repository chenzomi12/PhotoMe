package com.example.photome.gallery.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.photome.Intensifyimage.IntensifyImage;
import com.example.photome.Intensifyimage.IntensifyImageView;

import java.io.IOException;
import java.util.ArrayList;

public class ImagePageAdapter extends PagerAdapter {

    private static String TAG = ImagePageAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<String> mPhotoPathList = new ArrayList<String>();


    public ImagePageAdapter(Context context, ArrayList<String> photoPathList) {
        mContext = context;

        mPhotoPathList = photoPathList;

        Log.d(TAG, "ImagePageAdapter length:" + mPhotoPathList.size());
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
        // TODO: the intensifyImageView is too slow in loading. can change to Glide?
        IntensifyImageView imageView = new IntensifyImageView(container.getContext());
        imageView.setScaleType(IntensifyImage.ScaleType.FIT_AUTO);
        imageView.setImage(mPhotoPathList.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }




}