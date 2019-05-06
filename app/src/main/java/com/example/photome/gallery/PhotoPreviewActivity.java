package com.example.photome.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.photome.R;
import com.example.photome.editor.EditorMainActivity;
import com.example.photome.gallery.adapters.ImagePageAdapter;

import java.util.ArrayList;

public class PhotoPreviewActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static String TAG = PhotoPreviewActivity.class.getSimpleName();

    private Context mContext;
    private ViewPager mViewPager;
    private ImagePageAdapter mAdapter;
    private BottomNavigationView mBtmView;

    private int mMenuId;
    private int mPosition;
    private ArrayList<String> mPhotoPathList = new ArrayList<String>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");
        overridePendingTransition(0, 0);

        setContentView(R.layout.gallery_photo_preview);

        mContext = this;

        mPosition = getIntent().getIntExtra("EXTRA_POSITION", 0);
        mPhotoPathList = getIntent().getStringArrayListExtra("EXTRA_PATHS");

        Log.d(TAG, "Get photo info from intent, position:" + mPosition);
        Log.d(TAG, "Get photo info from intent, paths num:" + mPhotoPathList.size());

        // PagerView
        mViewPager = (ViewPager) findViewById(R.id.gallery_vp_pager);
        mAdapter = new ImagePageAdapter(this, mPhotoPathList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPosition);

        // ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.gallery_photo_preview_toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // back button pressed
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // NavigationBar
        mBtmView = (BottomNavigationView) findViewById(R.id.gallery_photo_preview_navigation);
        mBtmView.setOnNavigationItemSelectedListener(this);
        mBtmView.getMenu().findItem(R.id.item_gallery_editor).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // uncheck the other items.
        mMenuId = item.getItemId();
        for (int i = 0; i < mBtmView.getMenu().size(); i++) {
            MenuItem menuItem = mBtmView.getMenu().getItem(i);
            boolean isChecked = menuItem.getItemId() == item.getItemId();
            menuItem.setChecked(isChecked);
        }

        switch (item.getItemId()) {
            case R.id.item_gallery_editor: {
                Log.d(TAG, "onNavigationItemSelected");
                Log.d(TAG, "EXTRA_PATH" + mPhotoPathList.get(mPosition));

                Intent intent = new Intent(mContext, EditorMainActivity.class);
                intent.putExtra("EXTRA_PATH", mPhotoPathList.get(mPosition));
                startActivity(intent);
            }
            break;
        }
        return true;
    }
}
