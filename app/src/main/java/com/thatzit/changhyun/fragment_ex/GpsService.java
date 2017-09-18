package com.thatzit.changhyun.fragment_ex;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by leechanghyeon on 2017. 9. 16..
 */

public class GpsService extends Service implements LocationListener {
    public static double lat;
    public static double lng;

    private LocationManager locationManager;
    private String provider;

    private boolean isGPS = false;

    void permissionAnswer(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }
    void GPSinit() {
        Log.e("test", "GPSinit()");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);


        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
        }

        if (false == isGPS) {
            isGPS = !isGPS;
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //액티비티간 통신할때 쓰임
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("test", "GPS: onCreate()");
        super.onCreate();
        lat = lng = 0.0;

        GPSinit();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test", "GPS: onStartCommand()");
        //서비스 호출될때마다 실행
        if (false == isGPS) {
            isGPS = !isGPS;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionAnswer();
            }
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("test", "GPS: onDestroy()");
        super.onDestroy();
        if(true == isGPS)
            isGPS = !isGPS;
        locationManager.removeUpdates(this);
    }



    @Override
    public void onLocationChanged(Location location) {
        Log.e("test", "GPS: onLocationChanged()");
        this.lat = (location.getLatitude());
        this.lng = (location.getLongitude());
        Log.e("test", "lat : " + this.lat +", lng : "+this.lng);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.e("test", "onStatusChanged");
        if (false == isGPS) {
            isGPS = !isGPS;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionAnswer();
            }
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.e("test", "onProviderEnabled");
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
        if (false == isGPS) {
            isGPS = !isGPS;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionAnswer();
            }
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.e("test", "onProviderDisabled");
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
        if(true == isGPS)
            isGPS = !isGPS;
        locationManager.removeUpdates(this);
    }
}
