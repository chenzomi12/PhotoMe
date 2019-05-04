package com.example.photome.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.photome.gallery.adapters.FolderAdapter;
import com.example.photome.gallery.info.FolderInfo;
import com.example.photome.gallery.info.PhotoInfo;
import com.example.photome.R;
import com.example.photome.gallery.uitls.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Show the Albums in the MediaStore.
 * <p>
 * create by zomi. 2019.5.3
 * update by zomi. 2019.5.4: add clickable propriety on the folder.
 */
public class GalleryMainActivity extends BaseActivity {

    private static final String TAG = GalleryMainActivity.class.getSimpleName();


    private RecyclerView mFolderList;
    private Context mContext;
    private Activity mActivity;
    private FolderAdapter mFolderAdapter;
    private Constants mConstants;

    private boolean hasFolderScan = false;
    private List<FolderInfo> folderInfoList = new ArrayList<>(); // save local folder info
    private List<PhotoInfo> photoInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");

        mContext = this;
        mActivity = this;
        mConstants = new Constants();
        setContentView(R.layout.gallery_main_activity);

        initToolbar();
        initView();
        initFolder();
        init();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.gallery_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.gallery_item_album);
    }

    public void initFolder() {
        Cursor cur;
        Uri uri;

        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Show the bucket name.
        // String[] projection = new String[]{"DISTINCT " + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME};
        // cur = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        //
        // StringBuffer list = new StringBuffer();
        // while (cur.moveToNext()) {
        // FolderInfo folderInfo = new FolderInfo();
        // folderInfo.name = cur.getString((cur.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)));
        // folderInfoList.add(folderInfo);
        // }

        cur = getContentResolver().query(uri,
                mConstants.IMAGE_PROJECTION,
                null,
                null,
                mConstants.IMAGE_PROJECTION[2] + " DESC");

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
                    if (!hasFolderScan && showFlag) {
                        File photoFile = new File(path);
                        File folderFile = photoFile.getParentFile();
                        FolderInfo folderInfo = new FolderInfo();

                        folderInfo.name = folderFile.getName();
                        folderInfo.path = folderFile.getAbsolutePath();
                        folderInfo.photoInfo = photoInfo;
                        if (!folderInfoList.contains(folderInfo)) {
                            List<PhotoInfo> photoInfoList = new ArrayList<>();
                            photoInfoList.add(photoInfo);
                            folderInfo.photoInfoList = photoInfoList;
                            folderInfoList.add(folderInfo);
                        } else {
                            FolderInfo f = folderInfoList.get(folderInfoList.indexOf(folderInfo));
                            f.photoInfoList.add(photoInfo);
                        }
                    }
                } while (cur.moveToNext());

                hasFolderScan = true;
            }
        }

        mFolderAdapter = new FolderAdapter(mActivity, mContext, folderInfoList);

    }

    public void initView() {
        mFolderList = (RecyclerView) findViewById(R.id.gallery_folder_recycle_view);
    }

    public void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        mFolderList.setLayoutManager(layoutManager);
        mFolderList.setAdapter(mFolderAdapter);

        // Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT).show();
        mFolderAdapter.setOnClickListener(new FolderAdapter.OnClickListener() {
            @Override
            public void onClick(FolderInfo folderInfo) {
                if (folderInfo == null) {
                    // Toast.makeText(mContext, "FolderInfo null", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, GalleryPhotosActivity.class);
                    intent.putExtra("EXTRA_NAME", getString(R.string.gallery_all_images));
                    intent.putExtra("EXTRA_PATH", "null");
                    startActivity(intent);
                } else {
                    // Toast.makeText(mContext, "FolderInfo: " + folderInfo.name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, GalleryPhotosActivity.class);
                    intent.putExtra("EXTRA_NAME", folderInfo.name);
                    intent.putExtra("EXTRA_PATH", folderInfo.path);
                    startActivity(intent);
                }
            }
        });
    }

    private void exit() {
        finish();
    }
}
