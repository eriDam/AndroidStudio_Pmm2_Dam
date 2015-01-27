package com.examples.eri.proyecto5menuentrada;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Settings_activity extends Activity {
    //colocamos lo mismo para compartir las preferencias con el layout del menu
    private SharedPreferences preferencias;
    private boolean audioAct;
   public static final String PREFS_NAME = "AmorOdioSettings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);


    }
    public void onToggleClicked(View v) {
        // Perform action on clicks
        if (((ToggleButton) v).isChecked()) {
            Toast.makeText(Settings_activity.this, "Toggle on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Settings_activity.this, "Toggle off", Toast.LENGTH_SHORT).show();
        }
    }

//    final ToggleButton togBtnSound = (ToggleButton) findViewById(R.id.toggleButton);
//    togBtnSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            // Toggle the Background color between a light and dark color
//            if (togBtnSound.isChecked())
//
//            {
//                Toast.makeText(this, "Pulsado Me gusta", Toast.LENGTH_SHORT).show();
//            } else
//
//            {
//                Toast.makeText(this, "Pulsado Me gusta", Toast.LENGTH_SHORT).show();
//            }
//        }
//    });

}
