package com.example.mobileproject_mellivora_capensis;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderCompleted extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_completed);
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        TextView orderNum = (TextView)findViewById(R.id.orderId);
        orderNum.setText(Integer.toString(args.getInt("ID")));
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TypeSelect.class);
        startActivity(intent);
    }
}
