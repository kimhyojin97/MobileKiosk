package com.example.mobileproject_mellivora_capensis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class UserMenu extends AppCompatActivity {
    Button orderButton;
    Button cancelButton;
    Button addCartButton;

    TextView titleTextView;
    TextView descTextView;
    TextView majorTextView;

    ListView menulist ;
    ListView cartlist ;
    MenuDBHelper helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermenu);

        menulist = (ListView) findViewById(R.id.usermenulist);
        cartlist = (ListView) findViewById(R.id.cartlist);
        helper = new MenuDBHelper(this, "menu.db", null, 1);

        displayList();

        //UserMenuAdapter adapter = new UserMenuAdapter();
        //adapter.addCart(R.drawable.image_gallery, "매운떡볶이", "3500원");

        cartlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String menuname = item.getTitle() ;
                String price = item.getDesc() ;
                int iconDrawable = item.getIcon();
            }
        });

        orderButton = (Button)findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), UserMenu.class);
                //startActivity(intent);
            }
        });

        /*addCartButton = (Button)findViewById(R.id.addcartbutton);
        addCartButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserMenu.this,"장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();
                addCart(R.drawable.image_gallery2, titleTextView.getText().toString().trim(), descTextView.getText().toString().trim());

            }
        });

        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserMenu.this,"취소되었습니다.", Toast.LENGTH_SHORT).show();
                cartLists.remove(titleTextView.getText().toString().trim());
            }
        });*/
    }

    private void displayList() {
        database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        String sql = "SELECT * FROM test;";
        Cursor cursor = database.rawQuery(sql,null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        UserMenuAdapter adapter = new UserMenuAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItem(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        menulist.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TypeSelect.class);
        startActivity(intent);
    }
}
