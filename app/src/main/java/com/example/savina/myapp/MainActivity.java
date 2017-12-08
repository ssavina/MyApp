package com.example.savina.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //public static final String PREFS = "myPrefs";

    String id = "";
    String name = "";
    String lat = "";
    String lon = "";
    //int defaultRadius = 200;

    FloatingActionButton floatingActionButton,fab_add , fab_star , fab_loc;
    boolean anhien= false;

    private class getMarkersBackground extends asyncTaskConnect {
        @Override
        protected void onPostExecute(String result) {

            if (result.equals("Error!")) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Getting Markers", Toast.LENGTH_SHORT).show();

                final Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                intent1.putExtra("result", result);
                startActivity(intent1);

            }

        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
            fab_star = (FloatingActionButton)findViewById(R.id.fab_star);
            fab_add = (FloatingActionButton)findViewById(R.id.fab_add);
            fab_loc = (FloatingActionButton)findViewById(R.id.fab_loc);

            fab_star.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Star fab click. Replace with your action", Toast.LENGTH_SHORT).show();
                    Intent s = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(s);
                }
            });
            fab_add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Add fab click. Replace with your action", Toast.LENGTH_SHORT).show();
                }
            });
            fab_loc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //Toast.makeText(MainActivity.this, "Lets see the map", Toast.LENGTH_SHORT).show();
                    new getMarkersBackground().execute();
                }
            });

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (anhien == false) {
                        Hien();
                        anhien = true;
                    } else {
                        An();
                        anhien = false;
                    }
                }
            });

        //SharedPreferences myPrefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = myPrefs.edit();
        //editor.putInt("Radius", defaultRadius);
        //editor.commit();
        }

    private void Hien(){
        fab_add.show();
        fab_star.show();
        fab_loc.show();
    }

    private void An(){
        fab_add.hide();
        fab_star.hide();
        fab_loc.hide();
    }
}

