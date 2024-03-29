package com.example.mobileproject_mellivora_capensis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class UserMenu extends AppCompatActivity {
    Button orderButton;
    Button searchButton;

    TextView titleTextView;
    TextView descTextView;
    TextView majorTextView;

    ListView menulist ;
    ListView cartlist ;
    MenuDBHelper helper;
    SQLiteDatabase database;


    //결제페이지에서 필요한 변수
    Bundle bundle;
    Bundle args;
    UserMenuAdapter UMadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermenu);

        menulist = (ListView) findViewById(R.id.usermenulist);
        cartlist = (ListView) findViewById(R.id.cartlist);
        helper = new MenuDBHelper(this, "menu.db", null, 1);

        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                searchButton = (Button)findViewById(R.id.searchBtn);
                searchButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayList();
                        String filterText = edit.toString() ;
                        if (filterText.length() > 0) {
                            menulist.setFilterText(filterText) ;
                        } else {
                            menulist.clearTextFilter() ;
                        }
                    }
                });
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;
        displayList();
        //CartAdapter adapter = new CartAdapter();
        //cartlist.setAdapter(adapter);

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

        args = getIntent().getExtras();
        orderButton = (Button)findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = UMadapter.getCartsize();
                if(size > 0){bundle = new Bundle();
                    ArrayList<Integer> selectMenu = new ArrayList<Integer>();
                    for(int i=0; i<size; i++){
                        if(UMadapter.getCartItemCount(i) > 0){
                            int ID = UMadapter.getCartItemId(i);
                            int quan = UMadapter.getCartItemCount(i);
                            selectMenu.add(ID);
                            bundle.putInt(Integer.toString(ID), quan);
                            //System.out.println(ID+" "+quan);
                        }
                    }
                    bundle.putIntegerArrayList("menu", selectMenu);
                    Intent intent = new Intent(getApplicationContext(), Payment.class);
                    intent.putExtra("order", bundle);
                    intent.putExtra("type", args);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"장바구니가 비었습니다.", Toast.LENGTH_SHORT).show();
                }
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
        String sql = "SELECT * FROM test WHERE _id=";
        String sql2 = sql + QRScan.getId+ ";";
        Cursor cursor = database.rawQuery(sql2,null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        UMadapter = new UserMenuAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            UMadapter.addItem(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        menulist.setAdapter(UMadapter);

    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TypeSelect.class);
        startActivity(intent);
    }
}
