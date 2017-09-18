package com.thatzit.changhyun.fragment_ex;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-09-14.
 */

public class Fragment1 extends Fragment{
    View view;
    Button btn_fragment;
    Button btn_service;
    private Fragment1 self;
    private ClickListener mylistener;
    private GpsControl gpsControl;
    Context context;
    private boolean isGps;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = inflater.getContext();
        view = inflater.inflate(R.layout.fragment1, container, false);
        init();
        return view;
    }

    void init(){
        self = this;
        isGps = false;
        btn_fragment = view.findViewById(R.id.btn_flagment);
        btn_service = view.findViewById(R.id.btn_service);
        btn_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(self.mylistener !=null){
                    self.mylistener.onText("클릭했어요");
                }else{
                    Log.e("Fragment1","mylistener is null");
                }

            }
        });

        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("test", "서비스 버튼 누름");
                if (self.gpsControl != null){
                    Log.e("test", "지피에스 컨트롤이 널이아님");
                    if (false == isGps){
                        isGps = !isGps;
                        self.gpsControl.isGPS(true);
                    }else {
                        isGps = !isGps;
                        self.gpsControl.isGPS(false);
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mylistener = (ClickListener) context;
        gpsControl = (GpsControl) context;
    }
}
