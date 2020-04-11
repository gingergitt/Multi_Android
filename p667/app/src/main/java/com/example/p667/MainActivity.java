package com.example.p667;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, 101);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocation myLocation = new MyLocation();
        long minTime = 1;
        float minDistance = 0;
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                myLocation
        );



    }

    public void ckbt(View v) { //위치정보 줘 => startservice

        startLocationService();

    }
    //실내에선 NETWORK, 밖에서 돌아다니면서 할 땐 GPS

    //(1)요청 할 때마다 위치를 보내준다.
    private void startLocationService() {
        Log.d("---","startLocationService");
        //context.Location_Service를 안드로이드에게 요청
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = null;
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
               location =  manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                Log.d("---","checkSelfPermission");
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                textView.setText(lat + "" + lon); //위도 경도 한번에 표시

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //클래스 안에 클래스를 만든다(이너클래스)
    //(2)위치가 변할 때마다 계속 알려준다.
    //위 두가지를 나눠서 생각해보아야 한다. (1),(2) 두가지 경우
    class MyLocation implements LocationListener {


        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            String loc = lat + " " + lon;

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
