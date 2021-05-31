package com.example.mobileproject_mellivora_capensis;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TypeSelect extends AppCompatActivity {
    Button hereSelect;
    Button takeoutSelect;
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_select);
        hereSelect = (Button)findViewById(R.id.hereButton);
        takeoutSelect = (Button)findViewById(R.id.takeoutButton);

        hereSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserMenu.class);
                startActivity(intent);
                saveData("매장");
            }
        });

        takeoutSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserMenu.class);
                startActivity(intent);
                saveData("포장");
            }
        });

    }

    public void saveData(String type){
        args = new Bundle();
        args.putString("type", type);

    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), StartSelect.class);
        startActivity(intent);
    }

}
