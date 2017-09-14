package com.thatzit.changhyun.fragment_ex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-09-14.
 */

public class Fragment2 extends Fragment implements TextListener{
    public TextView tv;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        tv = view.findViewById(R.id.tv_fragment2);
        ((MainActivity)getActivity()).setOnTextListener(this);
        return view;
    }

    @Override
    public void OnText(String text) {
        tv.setText(text);
    }
}
