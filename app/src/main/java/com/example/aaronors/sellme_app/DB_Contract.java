package com.example.aaronors.sellme_app;

import android.provider.BaseColumns;

/**
 * Created by aaronors.
 */

public final class DB_Contract {

    private DB_Contract(){}



    public static class dbEntry implements BaseColumns{

        public static final String TABLE_NAME = "tableResults";
        public static final String COL_NAME = "name";
        public static final String COL_PRICE = "price";
        // public static final String COL_IMG = "img";

    }

    public static final String CREATE_TABLE = "CREATE TABLE " +
            dbEntry.TABLE_NAME + " (" +
            dbEntry._ID + " INTEGER PRIMARY KEY," +
            dbEntry.COL_NAME + " TEXT," +
            dbEntry.COL_PRICE + " TEXT," + ")";
            // dbEntry.COL_IMG + " BLOB" + ")";


    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + dbEntry.TABLE_NAME;

}


/***
 *
 * create table from database and iterate over table with cursor
 *
 *
 * insert into db once, pass db into final file
 *
 * get pics from internet, use glide
 *
 ***/