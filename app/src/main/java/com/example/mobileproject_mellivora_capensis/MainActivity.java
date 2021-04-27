package com.example.mobileproject_mellivora_capensis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobileproject_mellivora_capensis.R;

public class MainActivity extends AppCompatActivity {
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add =(Button)findViewById(R.id.add);
        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.image_gallery),
                "매운떡볶이", "3500원", "고추장, 쌀떡, 오뎅") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.image_gallery2),
                "로제떡볶이", "4000원", "우유, 치즈, 누들떡, 차돌") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.image_gallery3),
                "짜장떡볶이", "3000원", "춘장, 밀떡, 계란") ;


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                String majorStr = item.getMajor() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.
            }
        }) ;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"추가되었습니다.", Toast.LENGTH_SHORT).show();
                adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.image_gallery),"","", "");
                adapter.notifyDataSetChanged();
            }
        });


    }


}