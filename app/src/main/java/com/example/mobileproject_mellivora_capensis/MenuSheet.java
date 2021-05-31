package com.example.mobileproject_mellivora_capensis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MenuSheet extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.usermenu_item, container, false);
    }

    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if(args != null){

            //메뉴이미지

            TextView menuname = getActivity().findViewById(R.id.menuname);
            menuname.setText(args.getString("menutitle"));

            TextView price = getActivity().findViewById(R.id.price);
            price.setText(args.getString("menudesc"));

            TextView major = getActivity().findViewById(R.id.major);
            major.setText(args.getString("menumajor"));
        }
    }
}
