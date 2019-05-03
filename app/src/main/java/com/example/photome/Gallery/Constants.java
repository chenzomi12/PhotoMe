package com.example.photome.Gallery;

import android.provider.MediaStore;

public class Constants {

    public int showPhotoSize = 1024 * 5;

    final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.SIZE,
    };
}
