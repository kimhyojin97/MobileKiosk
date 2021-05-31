package com.example.mobileproject_mellivora_capensis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StartSelect extends AppCompatActivity {
    ImageButton customerSelect;
    ImageButton userSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_select);

        customerSelect = (ImageButton)findViewById(R.id.customerButton);
        userSelect = (ImageButton)findViewById(R.id.userButton);

        customerSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        userSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TypeSelect.class);//수정
                startActivity(intent);
            }
        });
    }
}