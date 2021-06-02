package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> cartList = new ArrayList<ListViewItem>() ;
    private int nListCnt = 0;
    Bundle args;

    public CartAdapter() {

    }

    @Override
    public int getCount() {
        return cartList.size() ;
    }

    public void updateReceiptsList(ArrayList<ListViewItem> mlistViewItemList) {
        cartList = mlistViewItemList;
        nListCnt = cartList.size(); // 배열 사이즈 다시 확인
        this.notifyDataSetChanged(); // 그냥 여기서 하자
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.usercart_item, parent, false);
        }

        ImageView iconImageView_c = (ImageView) convertView.findViewById(R.id.c_icon) ;
        TextView titleTextView_c = (TextView) convertView.findViewById(R.id.c_menuname);
        TextView CountTextView_c = (TextView) convertView.findViewById(R.id.c_count);

        ListViewItem cartViewItem = cartList.get(position);

        iconImageView_c.setImageResource(cartViewItem.getIcon());
        titleTextView_c.setText(cartViewItem.getTitle());
        CountTextView_c.setText(cartViewItem.getCount());

        Button cancelButton = (Button)convertView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"취소되었습니다.", Toast.LENGTH_SHORT).show();
                if(pos != ListView.INVALID_POSITION){
                    cartList.remove(pos);
                }
            }
        });
        return convertView;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

}
