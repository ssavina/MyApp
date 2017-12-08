package com.example.savina.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFS = "myPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences myPrefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        final int Radius = myPrefs.getInt("Radius", 200);

        final SeekBar sb = (SeekBar)findViewById(R.id.seekBar);
        sb.setProgress(Radius);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekBarValue = sb.getProgress();
                SharedPreferences myPrefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putInt("Radius", seekBarValue);
                editor.commit();
                Toast.makeText(getApplicationContext(), Integer.toString(seekBarValue), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
