package com.example.aaronors.sellme_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

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


    public long add(String name, String price, String image, String category){
        try{
            ContentValues cv = new ContentValues();
            cv.put(DB_Contract.dbEntry.COL_NAME, name);
            cv.put(DB_Contract.dbEntry.COL_PRICE, price);
            cv.put(DB_Contract.dbEntry.COL_IMG, image);
            cv.put(DB_Contract.dbEntry.COL_CATEGORY, category);
            return db.insert(DB_Contract.dbEntry.TABLE_NAME,DB_Contract.dbEntry._ID,cv);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;

    }

    public Cursor getAll(){
        return db.rawQuery(DB_Contract.QUERY_ALL,null);
    }

    public Cursor getElectronics(){
        return db.rawQuery(DB_Contract.QUERY_ELECTRONICS,null);
    }

    public Cursor getAppliances(){
        return db.rawQuery(DB_Contract.QUERY_APPLIANCES,null);
    }

    public Cursor getMusic(){
        return db.rawQuery(DB_Contract.QUERY_MUSIC,null);
    }

    public Cursor getMobile(){
        return db.rawQuery(DB_Contract.QUERY_MOBILE,null);
    }

    public Cursor getOutdoor(){
        return db.rawQuery(DB_Contract.QUERY_OUTDOOR,null);
    }

}



/*        switch(item.getItemId()){

                case R.id.mElectronics: 1

                case R.id.mAppliances: 2

                case R.id.mMusic: 3

                case R.id.mMobile: 4

                case R.id.mOutdoor: 5
                }*/


/****
 *
 *
 *  DB_Adapter is used to interface the database?
 *
 *
 * ****/