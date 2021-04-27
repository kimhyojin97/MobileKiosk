package com.example.mobileproject_mellivora_capensis;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;

public class SignUpActivity extends AppCompatActivity {

    EditText userId;
    EditText userPassword;
    EditText sName;

    SQLiteDatabase newDB;
    DBHelperTest helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        helper = new DBHelperTest(this, "new", null, 1);

        userId = (EditText) findViewById(R.id.sign_id);
        userPassword = (EditText) findViewById(R.id.sign_password);
        sName = (EditText) findViewById(R.id.sign_sname);


        Button joinButton = (Button) findViewById(R.id.join_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = userId.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                String sname = sName.getText().toString().trim();

                if (id.length() < 5 || password.length() < 5) {
                    Toast.makeText(SignUpActivity.this, "아이디 다섯 글자 이상 \n" +
                            "비밀번호 다섯 글자 이상" +
                            "\n 입력해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    insertData(id, password, sname);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.sign_cancle_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    public void insertData(String id, String password, String sName) {
        newDB = helper.getWritableDatabase();

        String sql = ("insert into test(userId, password, sName) values " +
                "(" + "'" + id + "'" + "," + "'" + password + "'" + sName + ")");

        newDB.execSQL(sql);
    }
}