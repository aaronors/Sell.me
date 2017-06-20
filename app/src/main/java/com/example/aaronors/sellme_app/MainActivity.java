package com.example.aaronors.sellme_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by aaronors.
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView recView;
    private Recycler_Adapter recAdapter;
    private RecyclerView.LayoutManager recManager;
    DB_Adapter db;
    int picNum = 0;


    /**
     * This method sets up the db and RecyclerView.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        db = new DB_Adapter(this);

        db.dbOpen();
        if(sharedPreferences.getBoolean("first",true)){

            db.add("bike1","$ 8560","android.resource://com.example.aaronors.sellme_app/drawable/bike1","5");
            db.add("bike2","$ 453","android.resource://com.example.aaronors.sellme_app/drawable/bike2","5");
            db.add("espresso","$ 953","android.resource://com.example.aaronors.sellme_app/drawable/espresso","2");
            db.add("galaxy","$ 45","android.resource://com.example.aaronors.sellme_app/drawable/galaxy","4");
            db.add("guitar","$ 134","android.resource://com.example.aaronors.sellme_app/drawable/guitar","3");
            db.add("lawnmower","$ 46524","android.resource://com.example.aaronors.sellme_app/drawable/lawnmower","2");
            db.add("lenovo","$ 467","android.resource://com.example.aaronors.sellme_app/drawable/lenovo","1");
            db.add("macbook","$ 48","android.resource://com.example.aaronors.sellme_app/drawable/macbook","1");
            db.add("ps3","$ 35","android.resource://com.example.aaronors.sellme_app/drawable/ps3","1");
            db.add("xbox","$ 786","android.resource://com.example.aaronors.sellme_app/drawable/xbox","1");

            sharedPreferences.edit().putBoolean("first",false).commit();
        }

        Cursor cursor = db.getAll();
        recView = (RecyclerView) findViewById(R.id.rec_view);
        recAdapter = new Recycler_Adapter(this,cursor);
        recManager = new GridLayoutManager(this,2);
        recView.setLayoutManager(recManager);
        recView.setAdapter(recAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.picFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPrompt();
            }
        });

    }

    /**
     * This method checks for a bundle from PromptActivity. If a bundle is received add data to
     * db, and reset the RecyclerView.
     */
    @Override
    protected void onResume() {
        super.onResume();

        if(getIntent().getExtras() != null && getIntent().getExtras().getString("pName") != null) {

            String itemName = getIntent().getExtras().getString("pName");
            String itemPrice = getIntent().getExtras().getString("pPrice");
            String itemImage = getIntent().getExtras().getString("pImg");

            db.add(itemName,itemPrice,itemImage,"1");

            Cursor cursor = db.getAll();
            recAdapter = new Recycler_Adapter(this,cursor);
            recView.setAdapter(recAdapter);

            getIntent().removeExtra("pName");
            getIntent().removeExtra("pPrice");
            getIntent().removeExtra("pImg");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_categories, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Sets cursor to new query. Resets RecyclerView to reflect changes.
     */
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
        recView.setAdapter(recAdapter);

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called after gotoPrompt() is completed. Takes bitmap and passes it on to
     * the next intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentData) {
        super.onActivityResult(requestCode, resultCode, intentData);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) intentData.getExtras().get("data");
            String filename = getFilesDir() + "/" + "pic" + String.valueOf(new Integer(picNum++)) +".png";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filename);
                photo.compress(Bitmap.CompressFormat.PNG,100,fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally{
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent(this,PromptActivity.class);
            intent.putExtra("img",filename);

            startActivity(intent);
        }
    }


    /**
     * Takes a photo with the Camera App.
     */
    private void gotoPrompt(){
        Intent promptIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (promptIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(promptIntent, 1);
        }
    }
}

