package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuDBHelper extends SQLiteOpenHelper {
    String sql;

    public MenuDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        sql = "CREATE TABLE test (" + " _id INTEGER, menuicon INTEGER, menutitle TEXT, menudesc TEXT, menumajor TEXT);";
        db.execSQL(sql);
        db.execSQL("INSERT INTO TEST VALUES(1, 2131165324, '매운떡볶이', '3500', '고추장, 쌀떡, 오뎅');");
        db.execSQL("INSERT INTO TEST VALUES(2, 2131165324, '로제떡볶이', '4000', '우유, 치즈, 누들떡, 차돌');");
        db.execSQL("INSERT INTO TEST VALUES(3, 2131165324, '짜장떡볶이', '3000', '춘장, 밀떡, 계란');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
