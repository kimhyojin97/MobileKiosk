package com.example.mobileproject_mellivora_capensis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.Fragment;

public class OrderList extends Fragment {
    com.example.mobileproject_mellivora_capensis.DBHelperTest dbHelperTest;
    ListView listView;
    SQLiteDatabase db;
    Bundle args;

    final static String dbName = "order.db";
    final static int dbVersion = 1;
    public interface OnOrderSelectedListener{
        public void onOrderSelected(Bundle args);
    }

    OnOrderSelectedListener mOrderSelectedListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderlist_fragment, container, false);
        inflater.inflate(R.layout.orderlist_fragment, container, false);
        listView = view.findViewById(R.id.order_list);
        dbHelperTest = new com.example.mobileproject_mellivora_capensis.DBHelperTest(getActivity(), dbName, null, dbVersion);

        db = dbHelperTest.getWritableDatabase();
        String sql = "SELECT * FROM test;";
        Cursor cursor = db.rawQuery(sql, null);
        String[] strs = new String[] {"_id"};
        int[] ints = new int[] {R.id.order_num};

        if(cursor.getCount() > 0){
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(view.getContext(), R.layout.list_block, cursor, strs, ints, 0);
            listView.setAdapter(adapter);
        }
        mOrderSelectedListener = (com.example.mobileproject_mellivora_capensis.CustomerOrder)getActivity();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                readData(position);
                mOrderSelectedListener.onOrderSelected(args);
            }
        });
        return view;
    }

    public void readData(int index){
        db = dbHelperTest.getWritableDatabase();
        String sql = "SELECT * FROM test ;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToPosition(index);

        args = new Bundle();
        args.putString("type", cursor.getString(1));
        args.putString("menus", cursor.getString(2));
        args.putString("request", cursor.getString(3));
        args.putString("time", cursor.getString(4));

        cursor.close();
    }

}
