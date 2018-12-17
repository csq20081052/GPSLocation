package com.example.chens.locationtry;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LocationTryActivity extends AppCompatActivity {
    EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_try);

        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        text = (EditText) findViewById(R.id.textShow);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            showLocation(location);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

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
            });

        }
    }


    public void showLocation(Location currentLocation){
        if(currentLocation != null){
            String s = "";
            s += " Current Location: (";
            s += currentLocation.getLongitude();
            s += ",";
            s += currentLocation.getLatitude();
            s += ")\n Speed: ";
            s += currentLocation.getSpeed();
            s += "\n Direction: ";
            s += currentLocation.getBearing();
            text.setText(s);
        }
        else{
            text.setText("");
        }
    }
}
