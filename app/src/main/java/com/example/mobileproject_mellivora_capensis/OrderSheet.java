package com.example.mobileproject_mellivora_capensis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class OrderSheet extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.ordersheet_fragment, container, false);

    }

    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if(args != null){
            TextView type;

            if(args.getString("type").equals("매장")){
                type = getActivity().findViewById(R.id.maejang);
                type.setTextColor(Color.RED);
            }
            else if(args.getString("type").equals("포장")){
                type = getActivity().findViewById(R.id.pojang);
                type.setTextColor(Color.RED);
            }

            TextView menus = getActivity().findViewById(R.id.menus);
            menus.setText(args.getString("menus"));

            TextView request = getActivity().findViewById(R.id.request);
            menus.setText(args.getString("request"));

            TextView time = getActivity().findViewById(R.id.time);
            menus.setText(args.getString("time"));

        }
    }


}
