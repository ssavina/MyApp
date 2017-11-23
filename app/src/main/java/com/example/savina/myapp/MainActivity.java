package com.example.savina.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
        final Intent intent = new Intent(MainActivity.this, Fab.class);

        Button button =  findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent1);
            }
        });


        Button buttonfloat =  findViewById(R.id.floatingActionButton);
        {
                startActivity(intent);
            }

}}
