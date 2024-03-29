package com.example.mobileproject_mellivora_capensis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.app.Activity;
import android.database.Cursor;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Button memberButton;

    EditText id;
    EditText password;
    
    String userId;
    static public int u_id;

    boolean userExist;

    SQLiteDatabase newDB;
    LoginDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new LoginDBHelper(this, "new", null, 1);


        loginButton = (Button) findViewById(R.id.login_button);
        memberButton = (Button) findViewById(R.id.login_sign_button);

        id = (EditText) findViewById(R.id.login_id);
        password = (EditText) findViewById(R.id.login_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userExist = false;

                String isId = id.getText().toString().trim();
                String isPass = password.getText().toString().trim();
                if (isId.length() > 4 && isPass.length() > 4)
                    searchData(isId, isPass);
                else
                    Toast.makeText(LoginActivity.this, "입력이 잘못되었습니다.", Toast.LENGTH_SHORT);

                if (userExist) {
                    Intent intent = new Intent(getApplicationContext(), CustomerMain.class);
                    intent.putExtra("userId", userId);
                    setResult(Activity.RESULT_OK, intent);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가" +
                            "없거나 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, 203);
            }
        });
    }

    public void searchData(String isId, String isPass) {
        newDB = helper.getReadableDatabase();
        String sql = ("select _id, userId, password, sName from test");
        Cursor cursor = newDB.rawQuery(sql, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            int num = cursor.getInt(0);
            String id = cursor.getString(1);
            String password = cursor.getString(2);
            if (id.equals(isId) && password.equals(isPass)) {
                u_id = num;
                userId = id;
                userExist = true;
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), StartSelect.class);
        startActivity(intent);
    }
}

