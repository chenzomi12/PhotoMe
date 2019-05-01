package com.example.photome;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navigationBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("PhotoMe");
        toolbar.setSubtitle("photo editor.");

        // Navigation bar
        navigationBar1 = (BottomNavigationView) findViewById(R.id.bottom_navigation);
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
}