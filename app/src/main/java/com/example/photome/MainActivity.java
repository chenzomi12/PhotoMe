package com.example.photome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.photome.Editor.Constants;
import com.example.photome.Gallery.FileBrowsActivity;
import com.example.photome.Gallery.GalleryMainActivity;
import com.example.photome.Others.AboutUsActivity;
import com.example.photome.Others.FeedbackActivity;
import com.example.photome.Others.SettingActivity;
import com.example.photome.Others.TutorialActivity;
import com.example.photome.UI.FilterRecyclerViewAdapter;
import com.example.photome.UI.EffectRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.Map;

/**
 * 2019.5.2 create by zomi.
 *
 * THis activity contain the basic UI which including the SETTING, ABOUT US, FEEDBACK, TUITION.
 * And if the user click the logo will goto GALLERY.
 *
 * TODO: add GIF LOGO animation at the beginning of PhotoMe.
 * TODO: change the emoji face to PhotoMe logo.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    Toolbar mToolbar;
    BottomNavigationView mBottomBar;
    ImageView mImageView;
    Intent mIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        // Bottom bar
        mBottomBar = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomBar.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomBar.getMenu().setGroupEnabled(0, false);

        isStoragePermissionGranted();

        // GOTO Gallery
        mImageView = (ImageView) findViewById(R.id.open_gallery_icon);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent = new Intent(MainActivity.this, GalleryMainActivity.class);
                startActivity(mIntent);
                MainActivity.this.finish();
            }
        });
    }

    public void ImageViewListener(View view) {

    }

    //Add menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    //menu Click events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.item_setting:
                mIntent = new Intent(this, SettingActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.item_tutorial:
                mIntent = new Intent(this, TutorialActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.item_feedback:
                mIntent = new Intent(this, FeedbackActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.item_about:
                mIntent = new Intent(this, AboutUsActivity.class);
                startActivity(mIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
}