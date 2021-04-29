package com.example.mobileproject_mellivora_capensis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CustomerOrder extends AppCompatActivity implements OrderList.OnOrderSelectedListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerorder);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.orderlist_fragment, new OrderList());
        ft.commit();
    }

    public void onOrderSelected(Bundle args){
        OrderSheet newFragment = new OrderSheet();
        newFragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.ordersheet_fragment, newFragment);
        ft.commit();
    }


}