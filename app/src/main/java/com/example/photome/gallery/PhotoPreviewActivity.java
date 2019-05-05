package com.example.photome.gallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.photome.R;
import com.example.photome.gallery.adapters.ImagePageAdapter;

import java.util.ArrayList;

public class PhotoPreviewActivity extends BaseActivity {

    private static String TAG = PhotoPreviewActivity.class.getSimpleName();

    private ViewPager mViewPager;
    private ImagePageAdapter mAdapter;

    private int mPosition;
    private ArrayList<String> mPhotoPathList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");
        overridePendingTransition(0, 0);
        setContentView(R.layout.gallery_photo_preview);

        mPosition = (int) getIntent().getIntExtra("EXTRA_POSITION", 0);
        mPhotoPathList = getIntent().getStringArrayListExtra("EXTRA_PATHS");

        Log.d(TAG, "Get photo info from intent, position:" + mPosition);
        Log.d(TAG, "Get photo info from intent, paths num:" + mPhotoPathList.size());

        mViewPager = (ViewPager) findViewById(R.id.gallery_vp_pager);
        mAdapter = new ImagePageAdapter(this, mPhotoPathList, mPosition);
        mViewPager.setAdapter(mAdapter);
    }
}
