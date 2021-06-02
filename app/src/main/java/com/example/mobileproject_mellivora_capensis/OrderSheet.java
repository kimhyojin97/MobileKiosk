package com.example.mobileproject_mellivora_capensis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobileproject_mellivora_capensis.R;

import java.util.ArrayList;

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
            String s = args.getString("menus");
            s = s.replace(",", "\n");
            menus.setText(s);

            TextView request = getActivity().findViewById(R.id.request);
            request.setText(args.getString("request"));

            TextView time = getActivity().findViewById(R.id.time);
            time.setText(args.getString("time"));
        }
    }


}
