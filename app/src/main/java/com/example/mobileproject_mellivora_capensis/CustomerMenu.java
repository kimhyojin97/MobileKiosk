package com.example.mobileproject_mellivora_capensis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.ACTION_GET_CONTENT;

public class CustomerMenu extends AppCompatActivity {
    Button add;
    ListView listview ;
    MenuDBHelper helper;
    SQLiteDatabase database;
    ImageView iconImageView;
    private final int GET_GALLERY_IMAGE =200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customermenu);
        listview = (ListView) findViewById(R.id.listview1);
        helper = new MenuDBHelper(this, "menu.db", null, 1);

        displayList();

        add =(Button)findViewById(R.id.add);
        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerMenu.this,"추가되었습니다.", Toast.LENGTH_SHORT).show();
                insertMenu(0,"메뉴명 입력","가격 입력","주성분 입력");
            }
        });

/*        iconImageView = (ImageView)findViewById(R.id.menuimage) ;
        iconImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });*/

    }

    private void displayList() {
        database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        String sql = "SELECT * FROM test;";
        Cursor cursor = database.rawQuery(sql,null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter adapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItem(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        listview.setAdapter(adapter);
    }

    void insertMenu(int image, String menuname, String price, String major){
        //Dbhelper의 쓰기모드 객체를 가져옴
        database = helper.getReadableDatabase();
        String sql = "INSERT INTO test(menuicon, menutitle, menudesc, menumajor) VALUES('"+image+ "', '" + menuname + "', '" + price + "', '" + major + "')";
        database.execSQL(sql); //만들어준 쿼리문 실행
        displayList();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), CustomerMain.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            iconImageView.setImageURI(selectedImageUri);
        }
    }

}