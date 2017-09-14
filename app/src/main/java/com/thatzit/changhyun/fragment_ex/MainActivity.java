package com.thatzit.changhyun.fragment_ex;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ClickListener{
    public Fragment2 fragment2;
    private TextListener mylistner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragment1= new Fragment1();
//        fragment2 = new Fragment2();
//        fragmentTransaction.add(R.id.frag1,fragment1);
//        fragmentTransaction.add(R.id.frag2, fragment2);
//        fragmentTransaction.commit();
    }
    public void setText(String s){
        fragment2.tv.setText(s);
    }

    @Override
    public String onText(String text) {
        Log.e("MainActivity",text);
        mylistner.OnText(text);
        return text;
    }
    public void setOnTextListener(TextListener listener){
        this.mylistner = listener;
    }
}
