package com.example.photome.gallery.loader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * basic android original Image loader.
 */
public interface ImageLoader extends Serializable {

    void displayImage(Activity activity,
                      Context context,
                      String path,
                      ImageView galleryImageView,
                      int width,
                      int height);

    void clearMemoryCache();
}
