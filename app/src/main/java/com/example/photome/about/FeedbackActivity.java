package com.example.photome.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.photome.R;

public class FeedbackActivity extends AppCompatActivity {
    private static final String TAG = FeedbackActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");

        setContentView(R.layout.activity_feedback);

        // Load the toolbar add back button.
        Toolbar toolbar = (Toolbar) findViewById(R.id.feedback_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            Log.d(TAG, "could not find actionBar.");
        }
    }
}
