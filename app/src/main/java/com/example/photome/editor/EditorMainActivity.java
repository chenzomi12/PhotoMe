package com.example.photome.editor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.photome.R;
import com.example.photome.about.AboutUsActivity;
import com.example.photome.about.FeedbackActivity;
import com.example.photome.about.SettingActivity;
import com.example.photome.about.TutorialActivity;
import com.example.photome.editor.adapter.EffectRecyclerViewAdapter;
import com.example.photome.editor.adapter.FilterRecyclerViewAdapter;
import com.example.photome.editor.filter.FilterRecycleViewData;
import com.example.photome.editor.view.BackWindow;
import com.example.photome.editor.utils.Constants;
import com.example.photome.editor.view.MateInfoWindow;
import com.example.photome.gallery.GalleryMainActivity;
import com.example.photome.utils.FileUtils;
import com.example.photome.utils.ScreenUtils;

import java.util.ArrayList;


/**
 * TODO: set animation while the layout change height and recycler view gone and visible.
 */
public class EditorMainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private final static String TAG = EditorMainActivity.class.getSimpleName();

    private Context mContext;

    private Activity mActivity;

    private String mImagePath;

    private Window mWindow;

    private Toolbar mToolbar;

    private BottomNavigationView mNavigationView;

    int mHeightFilterT, mHeightBaseT, mHeightAdvanceT;

    private LinearLayout mEditorCenterLayout, mImagePreviewLayout;

    private RelativeLayout mFilterLayout, mBaseLayout, mAdvanceLayout;

    private RecyclerView mFilterRecycleView, mBaseToolRecycleView, mAdvanceToolRecycleView;

    private ArrayList<String> mBaseIconNames = new ArrayList<>();

    private ArrayList<Integer> mBaseIconPaths = new ArrayList<>();

    private ArrayList<String> mAdvanceIconNames = new ArrayList<>();

    private ArrayList<Integer> mAdvanceIconPaths = new ArrayList<>();

    private BackWindow mBackWindow;

    private MateInfoWindow mMateInfoWindow;

    private Constants mEditorConstants;

    private Resources mRes;

    private int mSrcPreviewHeight;

    private ScreenUtils mScreenUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_main_activity);

        this.mActivity = this;
        this.mContext = this;

        mImagePath = getIntent().getStringExtra("EXTRA_PATH");

        initView();
        initImageBitmap(mImagePath);
        initFilterRecycleView();
        initBaseToolRecyclerView();
        initAdvanceToolRecyclerView();

        getPreviewSrcHeight();

        ZoomInLayout(mHeightFilterT);
        mFilterLayout.setVisibility(View.VISIBLE);
    }

    private void initView() {
        mEditorConstants = new Constants();
        mScreenUtils = new ScreenUtils();
        mRes = getResources();
        mWindow = getWindow();

        mEditorCenterLayout = (LinearLayout) findViewById(R.id.editorCenterLayout);
        mImagePreviewLayout = (LinearLayout) findViewById(R.id.editorImagePreviewLayout);
        mFilterLayout = (RelativeLayout) findViewById(R.id.editorFilterRelative);
        mBaseLayout = (RelativeLayout) findViewById(R.id.editorBaseToolRelative);
        mAdvanceLayout = (RelativeLayout) findViewById(R.id.editorAdvanceToolRelative);

        mFilterRecycleView = (RecyclerView) findViewById(R.id.editorFilterRecycleView);
        mBaseToolRecycleView = (RecyclerView) findViewById(R.id.editorBaseToolRecycleView);
        mAdvanceToolRecycleView = (RecyclerView) findViewById(R.id.editorAdvanceToolRecycleView);

        // ToolBar
        mToolbar = (Toolbar) findViewById(R.id.editorToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        // NavigationBar
        mNavigationView = (BottomNavigationView) findViewById(R.id.editor_main_navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);

        // Windows
        mBackWindow = new BackWindow(this.mContext, findViewById(R.id.editorMain), mWindow);
        mMateInfoWindow = new MateInfoWindow(this.mContext, findViewById(R.id.editorMain), mWindow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_toolbar, menu);
        return true;
    }

    //menu Click events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editorItemBatch:
                startActivity(new Intent(this, GalleryMainActivity.class));
                return true;
            case R.id.editorItemInfo:
                // show pop up windows image mate info.
                mMateInfoWindow.showAtLocation(Gravity.BOTTOM, 0, 0);
                mMateInfoWindow.Dismiss();
                return true;
            case R.id.editorItemBackAll:
                // show pop up windows back info.
                mBackWindow.showAtLocation(Gravity.BOTTOM, 0, 0);
                mBackWindow.Dismiss();
                return true;
            case R.id.item_setting:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.item_tutorial:
                startActivity(new Intent(this, TutorialActivity.class));
                return true;
            case R.id.item_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                return true;
            case R.id.item_about:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // uncheck the other items.
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = mNavigationView.getMenu().getItem(i);
            boolean isChecked = menuItem.getItemId() == item.getItemId();
            menuItem.setChecked(isChecked);
        }

        switch (item.getItemId()) {
            case R.id.editorNavFilter: {
                Log.d(TAG, "editorNavFilter");

                mBaseLayout.setVisibility(View.GONE);
                mAdvanceLayout.setVisibility(View.GONE);
                if (mFilterLayout.getVisibility() == View.VISIBLE) {
                    ZoomOutLayout();
                    mFilterLayout.setVisibility(View.GONE);
                } else {
                    ZoomInLayout(mHeightFilterT);
                    mFilterLayout.setVisibility(View.VISIBLE);
                }
            }
            break;
            case R.id.editorNavBaseTool: {
                Log.d(TAG, "editorNavBaseTool");

                mFilterLayout.setVisibility(View.GONE);
                mAdvanceLayout.setVisibility(View.GONE);
                if (mBaseLayout.getVisibility() == View.VISIBLE) {
                    ZoomOutLayout();
                    mBaseLayout.setVisibility(View.GONE);
                } else {
                    ZoomInLayout(mHeightBaseT);
                    mBaseLayout.setVisibility(View.VISIBLE);
                }
            }
            break;
            case R.id.editorNavAdvanceTool: {
                Log.d(TAG, "editorNavAdvanceTool");

                mFilterLayout.setVisibility(View.GONE);
                mBaseLayout.setVisibility(View.GONE);
                if (mAdvanceLayout.getVisibility() == View.VISIBLE) {
                    ZoomOutLayout();
                    mAdvanceLayout.setVisibility(View.GONE);
                } else {
                    ZoomInLayout(mHeightAdvanceT);
                    mAdvanceLayout.setVisibility(View.VISIBLE);
                }
            }
            break;
            case R.id.editorNavSave: {
                Log.d(TAG, "editorNavSave");

                mFilterLayout.setVisibility(View.GONE);
                mBaseLayout.setVisibility(View.GONE);
                mAdvanceLayout.setVisibility(View.GONE);
            }
            break;
        }
        return true;
    }

    /**
     * Init Filter tool RecyclerView
     */
    private void initFilterRecycleView() {
        Log.d(TAG, "initFilterRecycleView");

        // Load filter small image data
        FilterRecycleViewData data = new FilterRecycleViewData();

        // init the view
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        mFilterRecycleView.setLayoutManager(layoutManager);
        FilterRecyclerViewAdapter adapter = new FilterRecyclerViewAdapter(
                data.mImgNames, data.mImgPaths, this);
        mFilterRecycleView.setAdapter(adapter);
    }

    /**
     * Init Base Tool RecyclerView and hide other Recycler
     */
    private void initBaseToolRecyclerView() {
        // Load base Tool icon and name
        mBaseIconNames = mEditorConstants.getBasicEffectToolsName();
        mBaseIconPaths = mEditorConstants.getBasicEffectToolArray();

        // init the view
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mBaseToolRecycleView.setLayoutManager(layoutManager);
        EffectRecyclerViewAdapter adapter = new EffectRecyclerViewAdapter(
                mBaseIconNames, mBaseIconPaths, mRes, this);
        mBaseToolRecycleView.setAdapter(adapter);
    }


    /**
     * Init Advance Tool RecyclerView and hide other Recycler
     */
    private void initAdvanceToolRecyclerView() {
        // Load Advance Tool icon and name
        mAdvanceIconNames = mEditorConstants.getAdvanceEffectToolsName();
        mAdvanceIconPaths = mEditorConstants.getAdvanceEffectToolArray();

        // init the view
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mAdvanceToolRecycleView.setLayoutManager(layoutManager);
        EffectRecyclerViewAdapter adapter = new EffectRecyclerViewAdapter(
                mAdvanceIconNames, mAdvanceIconPaths, mRes, this);
        mAdvanceToolRecycleView.setAdapter(adapter);
    }

    /**
     * Init Bitmap
     */
    private void initImageBitmap(String imagePath) {
        if (!FileUtils.isFileExist(imagePath)) {
            Log.e(TAG, "cannot find the image_file: " + imagePath);
            finish();
            return;
        }

        ImageView imageView = (ImageView) findViewById(R.id.editorImageView);
        Glide.with(mContext).load(imagePath).into(imageView);
    }

    private void getPreviewSrcHeight() {
        this.mHeightFilterT = mScreenUtils.getHeightOfView(mFilterLayout);
        this.mHeightBaseT = mScreenUtils.getHeightOfView(mBaseLayout);
        this.mHeightAdvanceT = mScreenUtils.getHeightOfView(mAdvanceLayout);

        int n1_ = mScreenUtils.getHeightOfView(findViewById(R.id.editor_main_navigation));
        int n2_ = mScreenUtils.getHeightOfView(findViewById(R.id.editorToolbar));
        this.mSrcPreviewHeight = mScreenUtils.getScreenHeight(mContext) - n1_ - n2_;

        Log.d(TAG, "mSrcPreviewHeight:" + String.valueOf(this.mSrcPreviewHeight));
        Log.d(TAG, "mHeightFilterT:" + String.valueOf(this.mHeightFilterT));
        Log.d(TAG, "mHeightBaseT:" + String.valueOf(this.mHeightBaseT));
        Log.d(TAG, "mHeightAdvanceT:" + String.valueOf(this.mHeightAdvanceT));
    }

    private void ZoomInLayout(int height) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImagePreviewLayout.getLayoutParams();

        params.height = this.mSrcPreviewHeight - height;
        mImagePreviewLayout.setLayoutParams(params);
    }

    private void ZoomOutLayout() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImagePreviewLayout.getLayoutParams();

        params.height = this.mSrcPreviewHeight;
        mImagePreviewLayout.setLayoutParams(params);
    }


}
