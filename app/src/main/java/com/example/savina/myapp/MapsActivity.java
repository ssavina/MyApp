package com.example.savina.myapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final String PREFS = "myPrefs";
    private static final int MY_PERM_FINE_LOCATION = 101;
    Location l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        GPS_Service g = new GPS_Service(getApplicationContext());
        l = g.getLocation();
        if (l != null){
            double lat = l.getLatitude();
            double lon = l.getLongitude();
            Toast.makeText(getApplicationContext(), "Lat:" + lat + " | Lon:" + lon, Toast.LENGTH_LONG).show();
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * f
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();

        SharedPreferences myPrefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int Radius = myPrefs.getInt("Radius", 200);
        Toast.makeText(getApplicationContext(), Integer.toString(Radius), Toast.LENGTH_SHORT).show();

        String jsonString = intent.getStringExtra("result");
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);
            JSONArray array = (JSONArray) obj;

            for (int x = 0; x < array.size(); x = x + 1) {
                JSONObject marker = (JSONObject) array.get(x);
                Double latd, lond;
                String name;
                name = (String) marker.get("name");
                latd = Double.parseDouble((String) marker.get("lat"));
                lond = Double.parseDouble((String) marker.get("lon"));
                LatLng ant = new LatLng(latd, lond);
                mMap.addMarker(new MarkerOptions().position(ant).title(name));
            }

            Toast.makeText(getApplicationContext(), "Markers Created", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
            return;
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERM_FINE_LOCATION);
            }
        }

        //LatLng myLocation = new LatLng(l.getLatitude(), l.getLongitude());
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

    }
}