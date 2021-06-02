package com.example.mobileproject_mellivora_capensis;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserMenuAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> umenuList = new ArrayList<ListViewItem>() ;
    private ArrayList<ListViewItem> cartList = new ArrayList<ListViewItem>() ;
    private int nListCnt = 0;

    //private int count = 0;
    private ArrayList<Integer> selectMenu = new ArrayList<Integer>();

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
        //cartview = (ListView) convertView.findViewById(R.id.cartlist);

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

        TextView tvCount=convertView.findViewById(R.id.tv_count);

        Button addCartButton = (Button)convertView.findViewById(R.id.addcartbutton);
        addCartButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();
                //CartAdapter adapter = new CartAdapter();
                if (cartList.size() > 0){
                    for (int i=0; i < cartList.size(); i++) {
                        if (cartList.get(i).getTitle().equals(titleTextView.getText().toString().trim())) {
                            int reCount = Integer.parseInt(tvCount.getText().toString());
                            cartList.get(i).setCount( cartList.get(i).getCount()+reCount);
                        }
                        else  {
                            addCart(pos, R.drawable.image_gallery2, titleTextView.getText().toString().trim(), descTextView.getText().toString().trim(), Integer.parseInt(tvCount.getText().toString()));
                        }
                    }
                }else  {
                    addCart(pos, R.drawable.image_gallery2, titleTextView.getText().toString().trim(), descTextView.getText().toString().trim(), Integer.parseInt(tvCount.getText().toString()));

                }
                //cartview.setAdapter(adapter);
            }
        });

        Button addCountButton = (Button)convertView.findViewById(R.id.btn_add);
        addCountButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(tvCount.getText().toString());
                tvCount.setText(String.valueOf(++count));
            }
        });

        Button minuConutButton = (Button)convertView.findViewById(R.id.btn_minus);
        minuConutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(tvCount.getText().toString());
                if(count>0) {
                    tvCount.setText(String.valueOf(--count));
                }
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

    public void addCart(int pos, int icon, String title, String desc, int n) {
        ListViewItem item = new ListViewItem();
        item.setPos(pos);
        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        item.setCount(n);
        cartList.add(item);
    }

    //카트 아이템 수량 넘기기
    public int getCartItemCount(int position) {
        return cartList.get(position).getCount();
    }
    //카트 아이템 id 넘기기
    public int getCartItemId(int position){
        return cartList.get(position).getPos();
    }
    public int getCartsize(){ return cartList.size();}

}
