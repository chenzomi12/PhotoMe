package com.example.photome.Gallery.Loader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.photome.R;

/**
 * GlideImageLoader
 */
public class GlideImageLoader implements ImageLoader {

    private final static String TAG = GlideImageLoader.class.getSimpleName();

    @Override
    public void displayImage(Activity activity,
                             Context context,
                             String path,
                             ImageView galleryImageView,
                             int width,
                             int height) {

        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.gallery_pick_photo)
                .centerCrop()
                .into(galleryImageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}