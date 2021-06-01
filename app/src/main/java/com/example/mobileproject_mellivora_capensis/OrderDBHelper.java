package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDBHelper extends SQLiteOpenHelper {

    String sql;

    public OrderDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    public void onCreate(SQLiteDatabase db){
        sql = "CREATE TABLE test (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+ " type TEXT, menus TEXT, request TEXT, " + "time TEXT);";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
