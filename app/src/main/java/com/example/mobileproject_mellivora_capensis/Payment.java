package com.example.mobileproject_mellivora_capensis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Payment extends AppCompatActivity {
    MenuDBHelper menuHelper;
    OrderDBHelper orderHelper;
    SQLiteDatabase menuDB;
    SQLiteDatabase orderDB;
    ArrayList<Integer> orderMenuArray; //주문한 메뉴의 db속 id number
    String menus;
    String request;
    Bundle bundle;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        menuHelper = new MenuDBHelper(this, "menu.db", null, 1);
        orderHelper = new OrderDBHelper(this, "order.db", null, 1);

        //주문 activity에서 arraylist 받아오기 -UserMenu와 연계
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("order");
        orderMenuArray = bundle.getIntegerArrayList("menu");

        //주문목록 채우기 및 totalPrice 계산
        setOrderlist();

        //totalPrice를 TextView에 setting
        TextView total = (TextView)findViewById(R.id.total_price);
        total.setText(Integer.toString(totalPrice));

    }


    public void setOrderlist(){
        //주문목록 리스트뷰 어댑터 세팅
        OrderListAdapter customAdapter = new OrderListAdapter();
        ListView orderlist = (ListView)findViewById(R.id.orderlist);
        orderlist.setAdapter(customAdapter);

        //주문한 메뉴,가격 불러오기 & 리스트뷰 생성
        menuDB = menuHelper.getWritableDatabase();
        String sql = "SELECT * FROM test ;";
        Cursor cursor = menuDB.rawQuery(sql, null);

        for(int i=0; i<orderMenuArray.size(); i++){
            int menu_id = orderMenuArray.get(i);
            cursor.moveToPosition(menu_id);
            String menuName = cursor.getString(2);
            int cost = Integer.parseInt(cursor.getString(3));
            int quan = bundle.getInt(Integer.toString(menu_id));

            totalPrice += cost * quan;
            menus += menuName + " " + quan;

            if(i+1 != orderMenuArray.size())
                menus += ",";

            customAdapter.addItem(menuName, Integer.toString(quan)+"개", Integer.toString(cost * quan)+"원");
        }
        cursor.close();
    }

    //'결제' 버튼 누르면 실행
    public void paymentStart(View view){
        //현재시간
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
        String time = simpleDate.format(date);

        //요청사항
        EditText mRequest = (EditText)findViewById(R.id.request_edit);
        request = mRequest.getText().toString();
        if (request.length() == 0 )
            request = " ";

        //주문타입
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("type");
        String type = bundle.getString("type");

        //Order DB에 주문 등록
        orderDB = orderHelper.getWritableDatabase();
        orderDB.execSQL("INSERT INTO TEST VALUES(NULL, '" + type + "', '" + menus + "', '" + request + "', '" + time + "');");
        String sql = "SELECT * FROM test ;";
        Cursor c = orderDB.rawQuery(sql, null);

        //주문완료 activity 연결
        Intent newIntent = new Intent(getApplicationContext(), OrderCompleted.class);
        Bundle args = new Bundle();
        args.putInt("ID", c.getCount());
        newIntent.putExtras(args);
        startActivity(newIntent);
        finish();
    }
}

