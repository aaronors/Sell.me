package com.example.aaronors.sellme_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recView;
    private Recycler_Adapter recAdapter;
    private RecyclerView.LayoutManager recManager;
    DB_Adapter db;

/*        switch(item.getItemId()){

                case R.id.mElectronics: 1

                case R.id.mAppliances: 2

                case R.id.mMusic: 3

                case R.id.mMobile: 4

                case R.id.mOutdoor: 5
                }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = (RecyclerView) findViewById(R.id.rec_view);

        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        // create cursor from database

        db = new DB_Adapter(this);

        db.dbOpen();
        if(sharedPreferences.getBoolean("first",true)){

            db.add("bike1","$8560","android.resource://com.example.aaronors.sellme_app/drawable/bike1","5");
            db.add("bike2","$453","android.resource://com.example.aaronors.sellme_app/drawable/bike2","5");
            db.add("espresso","$453","android.resource://com.example.aaronors.sellme_app/drawable/espresso","2");
            db.add("galaxy","$45","android.resource://com.example.aaronors.sellme_app/drawable/galaxy","4");
            db.add("guitar","$4654","android.resource://com.example.aaronors.sellme_app/drawable/guitar","3");
            db.add("lawnmower","$4654","android.resource://com.example.aaronors.sellme_app/drawable/lawnmower","2");
            db.add("lenovo","$4654","android.resource://com.example.aaronors.sellme_app/drawable/lenovo","1");
            db.add("macbook","$4654","android.resource://com.example.aaronors.sellme_app/drawable/macbook","1");
            db.add("ps3","$4654","android.resource://com.example.aaronors.sellme_app/drawable/ps3","1");
            db.add("xbox","$4654","android.resource://com.example.aaronors.sellme_app/drawable/xbox","1");

            sharedPreferences.edit().putBoolean("first",false).commit();
        }

        //db.dbDelete();

        //long lng = db.add("bike1","50");

        //System.out.println(lng);

        // add to DB here

        Cursor cursor = db.getAll();

        recAdapter = new Recycler_Adapter(this,cursor);
        recManager = new GridLayoutManager(this,2);
        recView.setLayoutManager(recManager);
        recView.setAdapter(recAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_categories, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Cursor cursor = null;

        switch(item.getItemId()){
            case R.id.mAll:
                cursor = db.getAll();
                break;
            case R.id.mElectronics:
                cursor = db.getElectronics();
                break;
            case R.id.mAppliances:
                cursor = db.getAppliances();
                break;
            case R.id.mMusic:
                cursor = db.getMusic();
                break;
            case R.id.mMobile:
                cursor = db.getMobile();
                break;
            case R.id.mOutdoor:
                cursor = db.getOutdoor();
                break;
        }

        recAdapter = new Recycler_Adapter(this,cursor);
        recManager = new GridLayoutManager(this,2);
        recView.setLayoutManager(recManager);
        recView.setAdapter(recAdapter);
/*        cursor.moveToFirst();
        recAdapter.swapCursor(cursor);
        recView.setAdapter(recAdapter);*/

        return super.onOptionsItemSelected(item);
    }

    private void gotoPrompt(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }
}


/****
 *
 * set up new activity view
 * add images to DB
 * add functionality to add pictures
 * floating button bot right
 * floating bar on click
 *
 * prioritize UI, have filler/just animation for backend, "likes", other functionality
 *
 * add actions to action bar
 *
 * ****/


/*****
 *
 * do db separate then add together, create separate db java class
 *
 *
 * do plain db, then add categories, search function
 *
 *
 * use number as key for 'category' column
******/