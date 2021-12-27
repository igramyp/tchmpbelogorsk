package com.example.tchmp_belogorsk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Regimka extends AppCompatActivity {

    TextView tvEnabledGPS;
    TextView tvLocationGPS;
  //  TextView tvEnabledNet;
    TextView tvLocationNet;
    TextView textLan;
    TextView textLog;

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regimka);
        tvEnabledGPS = findViewById(R.id.tvEnabledGPS);
        tvLocationGPS = findViewById(R.id.tvLocationGPS);
      //  tvEnabledNet = findViewById(R.id.tvEnabledNet);
        tvLocationNet = findViewById(R.id.tvLocationNet);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        textLan = findViewById(R.id.textLan);
        textLog = findViewById(R.id.textLog);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(this, "Permission failed", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100, 1, locationListener);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1,locationListener);
        checkEnabled();
    }

    @Override
    protected void onPause() { super.onPause(); }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderDisabled(String provider) { checkEnabled(); }
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onProviderEnabled(String provider) {
            checkEnabled();
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {return;}
            showLocation(locationManager.getLastKnownLocation(provider));
        }
    };

    private String showLocation(Location location) {
        if (location == null)
            return null;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            tvLocationGPS.setText(formatLocation(location));
            double ln = location.getLatitude();
            double lg = location.getLongitude();

            //тут карта километров





            if (ln <= 50.9475578309 && ln > 50.93866896370001 && lg >= 128.4492446017 && lg < 128.4508803962   ) {
                textLan.setText("7863 км");
                Location l2 = new Location("7863" );
                double ln2 = 50.9475578309;
                double lg2 = 128.4492446017;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.93866896370001 && ln > 50.9297376976 &&  lg >= 128.4508803962 && lg < 128.4591435936 ) {
                textLan.setText("7864 км");
                Location l2 = new Location("7864");
                double ln2 = 50.93866896370001;
                double lg2 = 128.4508803962;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.9297376976 && ln > 50.92213282880001 &&  lg >= 128.4591435936 && lg < 128.4591435936 ) {
                textLan.setText("7865 км");
                Location l2 = new Location("7865");
                double ln2 = 50.9297376976;
                double lg2 = 128.45255286;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.92213282880001 && ln > 50.9149794507 &&  lg >= 128.4591435936 && lg < 128.4690758169 ) {
                textLan.setText("7866 км");
                Location l2 = new Location("7866");
                double ln2 = 50.92213282880001;
                double lg2 = 128.4591435936;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.9149794507 && ln > 50.90836407999999 &&  lg >= 128.4690758169 && lg < 128.4784912324 ) {
                textLan.setText("7867 км");
                Location l2 = new Location("7867");
                double ln2 = 50.9149794507;
                double lg2 = 128.4690758169;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.90836407999999 && ln > 50.9015848434 &&  lg >= 128.4784912324 && lg < 128.4880412929 ) {
                textLan.setText("7868 км");
                Location l2 = new Location("7868");
                double ln2 = 50.90836407999999;
                double lg2 = 128.4784912324;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.9015848434 && ln > 50.8947431543 &&  lg >= 128.4880412929 && lg < 128.4973827968 ) {
                textLan.setText("7869 км");
                Location l2 = new Location("7869");
                double ln2 = 50.9015848434;
                double lg2 = 128.4880412929;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }
            if (ln <= 50.8947431543 && ln > 50.8879839712 &&  lg >= 128.4973827968 && lg < 128.5066515351 ) {
                textLan.setText("7870 км");
                Location l2 = new Location("7870");
                double ln2 = 50.8947431543;
                double lg2 = 128.4973827968;
                l2.setLatitude(Double.parseDouble(String.valueOf(ln2)));
                l2.setLongitude(Double.parseDouble(String.valueOf(lg2)));
                float distance_to_km_stolb=location.distanceTo(l2);
                textLog.setText("" + (int)Math.ceil(distance_to_km_stolb/100)+ " пк " + distance_to_km_stolb + " м");
            }







        }
       else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
            tvLocationNet.setText(formatLocation(location));
        }
        return null;
    }

    private String formatLocation(Location location) {
        double speedmc = location.getSpeed();
        double speedkmch = (speedmc*3600)/1000;
        if (location == null)
            return "";
        return String.format("Время " + " %3$tT" + " %3$tF\n" + " Координаты:\n" + " lat = %1$.5f\n lon = %2$.5f\n" + " Точность(м):"  + " %4$.1f\n" + " V(км/ч): " + " %5$.1f",
                location.getLatitude(), location.getLongitude(), new Date(location.getTime()),  location.getAccuracy(), speedkmch);
    }

    private void checkEnabled() {
        tvEnabledGPS.setText("Enabled: "+ locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
       // tvEnabledNet.setText("Enabled: "+ locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }
}

