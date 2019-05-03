package com.example.photome.Gallery;

import android.os.Bundle;
import android.util.Log;

import com.example.photome.R;

/**
 *
 */
public class GalleryMainActivity extends BaseActivity {
    private static final String TAG = FileBrowsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");

        setContentView(R.layout.activity_gallery_main);
    }

}
