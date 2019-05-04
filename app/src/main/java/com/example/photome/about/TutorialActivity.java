package com.example.photome.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.photome.R;

public class TutorialActivity extends AppCompatActivity {
    private static final String TAG = TutorialActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");

        setContentView(R.layout.activity_tutorial);

        // Load the toolbar add back button.
        Toolbar toolbar = (Toolbar) findViewById(R.id.tutorial_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            Log.d(TAG, "could not find actionBar.");
        }
    }
}