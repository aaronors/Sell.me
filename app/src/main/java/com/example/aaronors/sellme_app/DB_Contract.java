package com.example.aaronors.sellme_app;

import android.provider.BaseColumns;

import java.util.HashMap;

/**
 * Created by aaronors.
 */

public final class DB_Contract {

    private DB_Contract(){}

    //String[] header= {DB_Contract.dbEntry._ID,DB_Contract.dbEntry.COL_NAME,DB_Contract.dbEntry.COL_PRICE,DB_Contract.dbEntry.COL_IMG};




    public static class dbEntry implements BaseColumns{

        public static final String TABLE_NAME = "tableResults";
        public static final String COL_NAME = "name";
        public static final String COL_PRICE = "price";
        public static final String COL_IMG = "img";
        public static final String COL_CATEGORY = "category";

    }

    public static final String CREATE_TABLE = "CREATE TABLE " +
            dbEntry.TABLE_NAME + " (" +
            dbEntry._ID + " INTEGER PRIMARY KEY," +
            dbEntry.COL_NAME + " TEXT," +
            dbEntry.COL_PRICE + " TEXT," +
            dbEntry.COL_IMG + " TEXT," +
            dbEntry.COL_CATEGORY + " TEXT" + ")";


    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + dbEntry.TABLE_NAME;

    public static final String QUERY_ALL = "SELECT * FROM " + dbEntry.TABLE_NAME;

    public static final String QUERY_ELECTRONICS = "SELECT * FROM " + dbEntry.TABLE_NAME + " WHERE " + dbEntry.COL_CATEGORY + " = 1";

    public static final String QUERY_APPLIANCES = "SELECT * FROM " + dbEntry.TABLE_NAME + " WHERE " + dbEntry.COL_CATEGORY + " = 2";

    public static final String QUERY_MUSIC = "SELECT * FROM " + dbEntry.TABLE_NAME + " WHERE " + dbEntry.COL_CATEGORY + " = 3";

    public static final String QUERY_MOBILE = "SELECT * FROM " + dbEntry.TABLE_NAME + " WHERE " + dbEntry.COL_CATEGORY + " = 4";

    public static final String QUERY_OUTDOOR = "SELECT * FROM " + dbEntry.TABLE_NAME + " WHERE " + dbEntry.COL_CATEGORY + " = 5";

}

/*        switch(item.getItemId()){

                case R.id.mElectronics: 1

                case R.id.mAppliances: 2

                case R.id.mMusic: 3

                case R.id.mMobile: 4

                case R.id.mOutdoor: 5
                }*/


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

