package com.example.photome;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.photome.Editor.Constants;
import com.example.photome.Others.AboutUsActivity;
import com.example.photome.Others.FeedbackActivity;
import com.example.photome.Others.SettingActivity;
import com.example.photome.Others.TutorialActivity;
import com.example.photome.UI.FilterRecyclerViewAdapter;
import com.example.photome.UI.EffectRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    Toolbar toolbar;
    BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        // Bottom bar
        bottomBar = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomBar.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomBar.getMenu().setGroupEnabled(0, false);

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
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.item_setting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_tutorial:
                intent = new Intent(this, TutorialActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_about:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}