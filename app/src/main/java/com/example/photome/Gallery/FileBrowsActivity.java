package com.example.photome.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.photome.MainActivity;
import com.example.photome.R;

public class FileBrowsActivity extends AppCompatActivity {
    private static final String TAG = FileBrowsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create.");

        setContentView(R.layout.activity_gallery_main);

    }

}
