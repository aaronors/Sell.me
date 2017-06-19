package com.example.aaronors.sellme_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aaronors.
 */

public class DB_Helper extends SQLiteOpenHelper {

    public static final int DB_VER = 1;
    public static final String DB_NAME = "sol.db";


    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_Contract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


/****
 *
 *
 *  DB_Helper is used to create the database only?
 *
 *
 *  creates database each time? change so that it creates it once
 * ****/