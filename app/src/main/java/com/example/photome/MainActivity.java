package com.example.photome;

import android.content.res.Resources;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.photome.UI.FilterRecyclerViewAdapter;
import com.example.photome.UI.EffectRecyclerViewAdapter;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    Toolbar toolbar;
    BottomNavigationView navigationBar1;

    // just for text the filter recycle view
    private ArrayList<String> mImgNames = new ArrayList<>();
    private ArrayList<String> mImgPaths = new ArrayList<>();
    private ArrayList<Integer> mIconPaths = new ArrayList<>();
    private ArrayList<String> mIconNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("PhotoMe");

        // Navigation bar
        navigationBar1 = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        View fileItem = (View) findViewById(R.id.action_filter);
        fileItem.setOnClickListener(this);

        initImageBitmap();
        initEffctIcon();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "fileter_click", Toast.LENGTH_SHORT).show();

    }

    //Add menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    //menu Click events
    // TODO: change the Toast to its signal Activity.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_tutorial:
                Toast.makeText(this, "tutorial", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_help:
                Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void initImageBitmap(){
        Log.d(TAG, "initImageBitmap for testing the filter recycle view.");

        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");
        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");
        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");
        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");
        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");
        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");
        mImgPaths.add(String.valueOf(R.drawable.filter_sample));
        mImgNames.add("B1");

        initRecycleView();
    }

    private void initRecycleView(){
    Log.d(TAG, "initRecycleView");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recycleView = findViewById(R.id.filter_recycle_view);
        recycleView.setLayoutManager(layoutManager);
        FilterRecyclerViewAdapter adapter = new FilterRecyclerViewAdapter(mImgNames, mImgPaths, this);
        recycleView.setAdapter(adapter);
    }


    private void initEffctIcon(){
        Log.d(TAG, "initImageBitmap for testing the filter recycle view.");

        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");
        mIconPaths.add(R.drawable.ic_burst_mode);
        mIconNames.add("色调对比度");

        initGridRecycleView();
    }

    private void initGridRecycleView(){
        Log.d(TAG, "initGridRecycleView");

        Resources res = getResources();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        RecyclerView recycleView = findViewById(R.id.effect_recycle_view);
        recycleView.setLayoutManager(layoutManager);
        EffectRecyclerViewAdapter adapter = new EffectRecyclerViewAdapter(mIconNames, mIconPaths, res, this);
        recycleView.setAdapter(adapter);
    }

}