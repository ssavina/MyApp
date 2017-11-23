package com.example.savina.myapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Fab extends AppCompatActivity {


    FloatingActionButton floatingActionButton,fab_add , fab_star , fab_loc;

    boolean anhien= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        final Intent intent = new Intent(Fab.this, MainActivity.class);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab_star = (FloatingActionButton)findViewById(R.id.fab_star);
        fab_add = (FloatingActionButton)findViewById(R.id.fab_add);
        fab_loc = (FloatingActionButton)findViewById(R.id.fab_loc);

        fab_star.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(Fab.this, "Camera fab click. Replace with your action", Toast.LENGTH_SHORT).show();
            }
        });
        fab_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(Fab.this, "Camera fab click. Replace with your action", Toast.LENGTH_SHORT).show();
            }
        });
        fab_loc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(Fab.this, "Camera fab click. Replace with your action", Toast.LENGTH_SHORT).show();
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