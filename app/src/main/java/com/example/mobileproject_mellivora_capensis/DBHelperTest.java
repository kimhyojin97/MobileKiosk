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
        db.execSQL("INSERT INTO TEST VALUES(NULL, '포장', '메뉴1, 메뉴2, 메뉴3', '요청사항1', '01:00 PM');");
        db.execSQL("INSERT INTO TEST VALUES(NULL, '매장', '메뉴1, 메뉴2', ' ', '01:20 PM');");
        db.execSQL("INSERT INTO TEST VALUES(NULL, '포장', '메뉴1, 메뉴2, 메뉴3, 메뉴4', '요청사항2', '01:40 PM');");


        String customerTable = "test";
        db.execSQL("create table if not exists " + customerTable + " (" + " _id integer PRIMARY KEY autoincrement, "
                + " userId text, "
                + " password text, "
                + " sName text);");

        String userSubjectTable = "test2";
        db.execSQL("create table if not exists " + userSubjectTable + " ("
                + " _id integer PRIMARY KEY autoincrement, "
                + " userId text, "
                + " sub text, "
                + " day text, "
                + " start_time text, "
                + " finish_time text, "
                + " FOREIGN KEY (userId) REFERENCES test (userId));");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
