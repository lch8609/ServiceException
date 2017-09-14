package com.thatzit.changhyun.fragment_ex;

import android.app.FragmentTransaction;
import android.content.Context;
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
    private Fragment1 self;
    private ClickListener mylistener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        init();
        return view;
    }

    void init(){
        self = this;
        btn_fragment = view.findViewById(R.id.btn_flagment);
        btn_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(self.mylistener !=null){
                    self.mylistener.onText("asdvbadss");
                }else{
                    Log.e("Fragment1","mylistener is null");
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mylistener = (ClickListener) context;
    }
}
