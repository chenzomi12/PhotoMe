package com.example.photome.editor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.photome.R;

public class EditorMainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private final static String TAG = EditorMainActivity.class.getSimpleName();

    private String mImagePath;
    private TextView mTextMessage;

    private int mMenuId;
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_main_activity);

        mImagePath = getIntent().getStringExtra("EXTRA_PATH");

        // For TESTING ImagePath: EXTRA_PATH/storage/emulated/0/DCIM/Camera/IMG_20190504_213522.jpg
        mImagePath = "EXTRA_PATH/storage/emulated/0/DCIM/Camera/IMG_20190504_213522.jpg";

        // NavigationBar
        mNavigationView = (BottomNavigationView) findViewById(R.id.editor_main_navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // uncheck the other items.
        mMenuId = item.getItemId();
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = mNavigationView.getMenu().getItem(i);
            boolean isChecked = menuItem.getItemId() == item.getItemId();
            menuItem.setChecked(isChecked);
        }

        switch (item.getItemId()) {
            case R.id.editorNavFilter: {
                Log.d(TAG, "editorNavFilter");
            }
            break;
            case R.id.editorNavBaseTool: {
                Log.d(TAG, "editorNavBaseTool");
            }
            break;
            case R.id.editorNavAdvanceTool: {
                Log.d(TAG, "editorNavAdvanceTool");
            }
            break;
            case R.id.editorNavSave: {
                Log.d(TAG, "editorNavSave");
            }
            break;
        }
        return true;
    }
}
