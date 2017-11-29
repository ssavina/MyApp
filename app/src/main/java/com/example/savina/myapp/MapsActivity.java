package com.example.savina.myapp;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        String jsonString = intent.getStringExtra("result");
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);
            JSONArray array = (JSONArray) obj;
            JSONObject marker = (JSONObject)array.get(1);
            Double latd, lond;
            JSONObject obj2 = (JSONObject)array.get(1);
            latd = Double.parseDouble((String) obj2.get("lat"));
            lond = Double.parseDouble((String) obj2.get("lon"));
            LatLng ant = new LatLng(latd,lond);
            mMap.addMarker(new MarkerOptions().position(ant).title("dsa")); // TODO: add var for name like latd...

            //String my = (String) array.get(1);
            //Toast.makeText(getApplicationContext(), my, Toast.LENGTH_SHORT).show();
            //System.out.println(array.get(1));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        LatLng MyCountry = new LatLng(0, 0);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(MyCountry));
        //mMap.setMyLocationEnabled(true);

        // Add a marker from DB and move the camera
        //LatLng ant = new LatLng(latd,lond);
        //mMap.addMarker(new MarkerOptions().position(ant).title(name));

    }
}