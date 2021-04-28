package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperTest extends SQLiteOpenHelper {

    String sql;

    public DBHelperTest(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    public void onCreate(SQLiteDatabase db){
        sql = "CREATE TABLE test (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+ " type TEXT, menus TEXT, request TEXT, " + "time TEXT);";
        db.execSQL(sql);
        db.execSQL("INSERT INTO TEST VALUES(NULL, '포장', '메뉴1, 메뉴2, 메뉴3', '요청사항1', '2021년 1월 1일 오후 01:00');");
        db.execSQL("INSERT INTO TEST VALUES(NULL, '매장', '메뉴1, 메뉴2', ' ', '2021년 1월 1일 오후 01:20');");
        db.execSQL("INSERT INTO TEST VALUES(NULL, '포장', '메뉴1, 메뉴2, 메뉴3, 메뉴4', '요청사항2', '2021년 1월 1일 오후 01:40');");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
