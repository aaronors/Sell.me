package com.example.aaronors.sellme_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aaronors.
 */

public class DB_Adapter {

    Context c;
    SQLiteDatabase db;
    DB_Helper dbHelper;


    public DB_Adapter(Context c){
        this.c = c;
        dbHelper = new DB_Helper(this.c);
    }


    public DB_Adapter dbOpen(){
        try
        {
            db=dbHelper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    public void dbClose(){
        try
        {
            dbHelper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void dbDelete(){
        db.execSQL(DB_Contract.DELETE_TABLE);
    }

    public Cursor getAll(){

        String[] header= {DB_Contract.dbEntry._ID,DB_Contract.dbEntry.COL_NAME,DB_Contract.dbEntry.COL_PRICE};

        return db.query(DB_Contract.dbEntry.TABLE_NAME,header,null,null,null,null,null);
    }

    public long add(String name, String price){
        try{
            ContentValues cv = new ContentValues();
            cv.put(DB_Contract.dbEntry.COL_NAME, name);
            cv.put(DB_Contract.dbEntry.COL_PRICE, price);
            return db.insert(DB_Contract.dbEntry.TABLE_NAME,DB_Contract.dbEntry._ID,cv);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;

    }

}





/****
 *
 *
 *  DB_Adapter is used to interface the database?
 *
 *
 * ****/