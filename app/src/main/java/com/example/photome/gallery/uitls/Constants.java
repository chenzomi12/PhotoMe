package com.example.photome.gallery.uitls;

import android.provider.MediaStore;


public class Constants {

    public int showPhotoSize = 1024 * 5;// 5K

    public int showMaxPhotoSize = 1024 * 2048; // 20480K

    public final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.SIZE,
    };
}
