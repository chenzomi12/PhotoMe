package com.example.photome.Gallery;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.photome.Gallery.adapters.FolderAdapter;
import com.example.photome.Gallery.utils.FolderInfo;
import com.example.photome.Gallery.utils.PhotoInfo;
import com.example.photome.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GalleryMainActivity extends BaseActivity {

    private static final String TAG = GalleryMainActivity.class.getSimpleName();


    private RecyclerView mFolderList;
    private Context mContext;
    private Activity mActivity;
    private FolderAdapter mFolderAdapter;

    private boolean hasFolderScan = false;
    private List<FolderInfo> folderInfoList = new ArrayList<>(); // save local folder info
    private List<PhotoInfo> photoInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");

        mContext = this;
        mActivity = this;
        setContentView(R.layout.activity_gallery_main);

        initToolbar();
        initView();
        initFolder();
        init();
    }

    public void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.gallery_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.gallery_item_album);
    }

    public void initFolder() {
        Cursor cur;
        Uri uri;
        final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.SIZE,
        };

        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

// Show the bucket name.
//        String[] projection = new String[]{"DISTINCT " + MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME};
//        cur = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
//
//        StringBuffer list = new StringBuffer();
//        while (cur.moveToNext()) {
//            FolderInfo folderInfo = new FolderInfo();
//            folderInfo.name = cur.getString((cur.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)));
//            folderInfoList.add(folderInfo);
//        }

        cur = getContentResolver().query(uri,
                IMAGE_PROJECTION,
                null,
                null,
                IMAGE_PROJECTION[2] + " DESC");

        if (cur != null) {
            int count = cur.getCount();
            if (count > 0) {
                List<PhotoInfo> tempPhotoList = new ArrayList<>();
                cur.moveToFirst();
                do{
                    String path = cur.getString(cur.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                    String name = cur.getString(cur.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                    long dateTime = cur.getLong(cur.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                    int size = cur.getInt(cur.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                    boolean showFlag = size > 1024 * 5;
                    PhotoInfo photoInfo = new PhotoInfo(path, name, dateTime);
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
                        }else{
                            FolderInfo f = folderInfoList.get(folderInfoList.indexOf(folderInfo));
                            f.photoInfoList.add(photoInfo);
                        }
                    }
                }while(cur.moveToNext());

                photoInfoList.clear();
                photoInfoList.addAll(tempPhotoList);

                List<String> tempPhotoPathList = new ArrayList<>();
                for (PhotoInfo photoInfo : photoInfoList) {
                    tempPhotoPathList.add(photoInfo.path);
                }

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
    }
}
