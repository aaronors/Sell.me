package com.example.aaronors.sellme_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recView;
    private RecyclerView.Adapter recAdapter;
    private RecyclerView.LayoutManager recManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = (RecyclerView) findViewById(R.id.rec_view);

        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        // create cursor from database

        DB_Adapter db = new DB_Adapter(this);

        db.dbOpen();
        if(sharedPreferences.getBoolean("first",true)){

            db.add("bike1","8560");
            db.add("bike2","6");
            db.add("bike3","58754");
            db.add("bike4","70");
            db.add("bike5","54");
            db.add("bike6","7648");
            db.add("bike7","576");
            db.add("bike8","570");
            db.add("bike9","58654");

            sharedPreferences.edit().putBoolean("first",false).commit();
        }

        //db.dbDelete();

        //long lng = db.add("bike1","50");

        //System.out.println(lng);

        // add to DB here

        Cursor cursor = db.getAll();

        recAdapter = new Recycler_Adapter(cursor);


        recManager = new GridLayoutManager(this,2);
        recView.setLayoutManager(recManager);
        recView.setAdapter(recAdapter);



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