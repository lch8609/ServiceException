package com.thatzit.changhyun.fragment_ex;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ClickListener, GpsControl{
    private TextListener mylistner;
    private Context mainContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION},1000);
            }
        }
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

    @Override
    public boolean isGPS(boolean gps) {
        if (true == gps){
            Intent intent = new Intent(mainContext, GpsService.class);
            startService(intent);
            Log.e("test", "서바스 시작");
        }else{
            Intent intent = new Intent(mainContext, GpsService.class);
            stopService(intent);
            Log.e("test", "서비스 종료");
        }
        return gps;
    }

    public void setOnGps(Fragment2 fragment2) {
        fragment2.tv_lng.setText(Double.toString(GpsService.lng));
        fragment2.tv_lat.setText(Double.toString(GpsService.lat));
        Log.e("test", "지피에스 텍스트 변경");
    }
}
