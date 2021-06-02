package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.ACTION_GET_CONTENT;
import static androidx.core.app.ActivityCompat.startActivityForResult;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private int nListCnt = 0;
    MenuDBHelper helper;
    SQLiteDatabase database;
    ImageView iconImageView;

    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    public void updateReceiptsList(ArrayList<ListViewItem> mlistViewItemList) {
        listViewItemList = mlistViewItemList;
        nListCnt = listViewItemList.size(); // 배열 사이즈 다시 확인
        this.notifyDataSetChanged(); // 그냥 여기서 하자
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        helper = new MenuDBHelper(context, "menu.db", null, 1);

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        iconImageView = (ImageView) convertView.findViewById(R.id.menuimage) ;
        EditText titleEditView = (EditText)convertView.findViewById(R.id.menuname);
        EditText descEditView = (EditText)convertView.findViewById(R.id.price);
        EditText majorEditView = (EditText)convertView.findViewById(R.id.major);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageResource(listViewItem.getIcon());
        titleEditView.setText(listViewItem.getTitle());
        descEditView.setText(listViewItem.getDesc());
        majorEditView.setText(listViewItem.getMajor());

        Button modifyBtn = (Button)convertView.findViewById(R.id.modifybtn);
        modifyBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "수정합니다.", Toast.LENGTH_SHORT).show();
                titleEditView.setEnabled(true);
                titleEditView.setClickable(true);
                descEditView.setEnabled(true);
                descEditView.setClickable(true);
                majorEditView.setEnabled(true);
                majorEditView.setClickable(true);
            }
        });

        Button delBtn = (Button)convertView.findViewById(R.id.delbtn);
        delBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "삭제합니다.", Toast.LENGTH_SHORT).show();
                if(pos != ListView.INVALID_POSITION){
                    listViewItemList.remove(pos);
                    deleteMenu(titleEditView.getText().toString().trim());
                }
            }
        });

        Button saveBtn = (Button)convertView.findViewById(R.id.savebtn);
        saveBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "저장합니다.", Toast.LENGTH_SHORT).show();
                titleEditView.setEnabled(false);
                titleEditView.setClickable(false);
                descEditView.setEnabled(false);
                descEditView.setClickable(false);
                majorEditView.setEnabled(false);
                majorEditView.setClickable(false);
                listViewItemList.get(listViewItemList.size()-1).setIcon(R.drawable.image_gallery);
                listViewItemList.get(listViewItemList.size()-1).setTitle(titleEditView.getText().toString().trim());
                listViewItemList.get(listViewItemList.size()-1).setDesc(descEditView.getText().toString().trim());
                listViewItemList.get(listViewItemList.size()-1).setMajor(majorEditView.getText().toString().trim());
                updateMenu(pos, 0, titleEditView.getText().toString().trim(), descEditView.getText().toString().trim(), majorEditView.getText().toString().trim());
            }
        });

        iconImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(ACTION_GET_CONTENT);
                //startActivityForResult(MainActivity.class,intent);
            }
        });

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(int icon, String title, String desc, String major) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        item.setMajor(major);
        listViewItemList.add(item);
    }

    void deleteMenu(String menuname){
        //Dbhelper의 쓰기모드 객체를 가져옴
        database = helper.getReadableDatabase();

        String sql = "DELETE FROM test where menutitle="+"'"+ menuname + "'";
        database.execSQL(sql); //만들어준 쿼리문 실행

        updateReceiptsList(listViewItemList);    }

    void updateMenu(int pos, int image, String menuname, String price, String major){
        //Dbhelper의 쓰기모드 객체를 가져옴
        database = helper.getReadableDatabase();

        String sql = "UPDATE test SET menuicon='" + image + "', menutitle='" + menuname + "', menudesc='" + price + "', menumajor='" + major + "' WHERE _id='" + pos + "'";
        database.execSQL(sql); //만들어준 쿼리문 실행

        updateReceiptsList(listViewItemList);    }

}
