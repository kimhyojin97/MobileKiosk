package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    Button button;
//
//    SQLiteDatabase newDB;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (newDB == null) {
//            String dbName = "new";
//            openDatabase(dbName);
//        } else {
//            Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
//        }
//
//        button = (Button) findViewById(R.id.main_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivityForResult(intent, 101);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 101 && resultCode == RESULT_OK) {
//            String userId = data.getStringExtra("userId");
//
//            Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
//            intent.putExtra("userId", userId);
//            startActivityForResult(intent, 102);
//        } else {
//            button.setText("로그인에 실패했습니다.\n 다시 로그인 해주세요");
//        }
//    }
//
//    public void openDatabase(String dbName) {
//        DBHelperTest helper = new DBHelperTest(this, dbName, null, 1);
//        newDB = helper.getWritableDatabase();
//    }
}