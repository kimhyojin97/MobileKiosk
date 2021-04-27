package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private int nListCnt = 0;

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

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.manuimage) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.menuname) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.price) ;
        TextView majorTextView = (TextView) convertView.findViewById(R.id.major) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        descTextView.setText(listViewItem.getDesc());
        majorTextView.setText(listViewItem.getMajor());

        Button modifyBtn = (Button)convertView.findViewById(R.id.modifybtn);
        modifyBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "수정합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Button delBtn = (Button)convertView.findViewById(R.id.delbtn);
        delBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "삭제합니다.", Toast.LENGTH_SHORT).show();
                if(pos != ListView.INVALID_POSITION){

                    listViewItemList.remove(pos);
                    updateReceiptsList(listViewItemList);
                }
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
    public void addItem(Drawable icon, String title, String desc, String major) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        item.setMajor(major);

        listViewItemList.add(item);
    }


}
