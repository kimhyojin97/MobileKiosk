package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserMenuAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> umenuList = new ArrayList<ListViewItem>() ;
    private ArrayList<ListViewItem> cartList = new ArrayList<ListViewItem>() ;
    private int nListCnt = 0;

    public UserMenuAdapter() {

    }

    @Override
    public int getCount() {
        return umenuList.size() ;
    }

    public void updateReceiptsList(ArrayList<ListViewItem> mlistViewItemList) {
        umenuList = mlistViewItemList;
        nListCnt = umenuList.size(); // 배열 사이즈 다시 확인
        this.notifyDataSetChanged(); // 그냥 여기서 하자
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.usermenu_item, parent, false);
        }

        ImageView iconImageView_u = (ImageView) convertView.findViewById(R.id.u_menuimage) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.u_menuname);
        TextView descTextView = (TextView)convertView.findViewById(R.id.u_price);
        TextView majorTextView = (TextView)convertView.findViewById(R.id.u_major);

        ListViewItem cartViewItem = umenuList.get(position);

        iconImageView_u.setImageResource(cartViewItem.getIcon());
        titleTextView.setText(cartViewItem.getTitle());
        descTextView.setText(cartViewItem.getDesc());
        majorTextView.setText(cartViewItem.getMajor());

        Button addCartButton = (Button)convertView.findViewById(R.id.addcartbutton);
        addCartButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();
                addCart(R.drawable.image_gallery2, titleTextView.getText().toString().trim(), descTextView.getText().toString().trim());

            }
        });

        return convertView;
    }

    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return umenuList.get(position) ;
    }

    public void addItem(int icon, String title, String desc, String major) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        item.setMajor(major);
        umenuList.add(item);
    }

    public void addCart(int icon, String title, String desc) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        cartList.add(item);
    }
}
