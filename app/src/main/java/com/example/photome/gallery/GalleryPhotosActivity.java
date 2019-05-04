package com.example.photome.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.photome.editor.EditorMainActivity;
import com.example.photome.gallery.adapters.PhotoAdapter;
import com.example.photome.gallery.info.FolderInfo;
import com.example.photome.gallery.info.PhotoInfo;
import com.example.photome.R;
import com.example.photome.gallery.uitls.Constants;

import java.util.ArrayList;
import java.util.List;

public class GalleryPhotosActivity extends BaseActivity {

    private static String TAG = GalleryPhotosActivity.class.getSimpleName();

    private Context mContext;
    private Activity mActivity;
    private FolderInfo mFolderInfo;
    private PhotoAdapter mPhotoAdapter;
    private RecyclerView mGalleryImageRecycler;
    private Constants mConstants;

    private List<PhotoInfo> photoInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");
        setContentView(R.layout.gallery_photos_recycler);

        mContext = this;
        mActivity = this;
        mConstants = new Constants();

        Intent intent = getIntent();
        String folderName = intent.getStringExtra("EXTRA_NAME");
        String folderPath = intent.getStringExtra("EXTRA_PATH");

        initToolBar(folderName);
        initView();
        initPhotos(folderPath);
        init();
    }

    private void initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.gallery_photos_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            Log.d(TAG, "could not find actionBar.");
        }
    }

    private void initView() {
        mGalleryImageRecycler = (RecyclerView) super.findViewById(R.id.gallery_images_recycler);
    }

    private void init() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        mGalleryImageRecycler.setLayoutManager(gridLayoutManager);

        mGalleryImageRecycler.setAdapter(mPhotoAdapter);

        mPhotoAdapter.setOnClickListener(new PhotoAdapter.OnClickListener() {
            @Override
            public void onClick(PhotoInfo photoInfo) {
                if (photoInfo == null) {
                    Toast.makeText(mContext, "PhotoInfo not found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Going to Editor ...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, EditorMainActivity.class);
                    intent.putExtra("EXTRA_NAME", photoInfo.name);
                    intent.putExtra("EXTRA_PATH", photoInfo.path);
                    startActivity(intent);
                }
            }
        });
    }

    private void initPhotos(String folderPath) {
        Cursor cur;
        Uri uri;

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        if (folderPath.equals("null")) {
            Log.d(TAG, "Cursor all images.");
            cur = getContentResolver().query(uri,
                    mConstants.IMAGE_PROJECTION,
                    mConstants.IMAGE_PROJECTION[0] + " like '%" + "%'",
                    null,
                    mConstants.IMAGE_PROJECTION[2] + " DESC");
        } else {
            cur = getContentResolver().query(uri,
                    mConstants.IMAGE_PROJECTION,
                    mConstants.IMAGE_PROJECTION[0] + " like '%" + folderPath + "%'",
                    null,
                    mConstants.IMAGE_PROJECTION[2] + " DESC");
        }

        Log.d(TAG, "Cursor images: " + String.valueOf(cur.getCount()));


        if (cur != null) {
            int count = cur.getCount();

            if (count > 0) {
                List<PhotoInfo> tempPhotoList = new ArrayList<>();
                cur.moveToFirst();
                do {

                    String path = cur.getString(cur.getColumnIndexOrThrow(mConstants.IMAGE_PROJECTION[0]));
                    String name = cur.getString(cur.getColumnIndexOrThrow(mConstants.IMAGE_PROJECTION[1]));
                    long dateTime = cur.getLong(cur.getColumnIndexOrThrow(mConstants.IMAGE_PROJECTION[2]));
                    int size = cur.getInt(cur.getColumnIndexOrThrow(mConstants.IMAGE_PROJECTION[4]));
                    PhotoInfo photoInfo = new PhotoInfo(path, name, dateTime);

                    // filter size to show.
                    boolean showFlag = size > mConstants.showPhotoSize && size < mConstants.showMaxPhotoSize;
                    if (showFlag) {
                        tempPhotoList.add(photoInfo);
                    }
                } while (cur.moveToNext());

                photoInfoList.clear();
                photoInfoList.addAll(tempPhotoList);

                // give the photoInfoList to tempPhotoPathList
                List<String> tempPhotoPathList = new ArrayList<>();
                for (PhotoInfo photoInfo : photoInfoList) {
                    tempPhotoPathList.add(photoInfo.path);
                }
            }
        }

        mPhotoAdapter = new PhotoAdapter(mContext, photoInfoList);
    }

    private void exit() {
        finish();
    }
}
