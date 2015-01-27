package com.examples.eri.proyecto5menuentrada;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    //Creamos  las preferencias de usuario
    private SharedPreferences preferencias;
    private boolean audioAct;
   public static final String PREFS_NAME = "AmorOdioSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Cpaturamos las preferencias del usuario
        preferencias = getSharedPreferences(PREFS_NAME,0);
        audioAct=preferencias.getBoolean("Audio",true);

        // al pulsar el imageButton Settings abrirá la siguiente ventana con el intent filter
        final ImageButton btnSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arrancar la siguiente activity
                Intent mainIntent;
                mainIntent = new Intent().setClass(
                        MainActivity.this, ActivityMusic.class);
                startActivity(mainIntent);
            }
        });

        // Al pulsar el imageButton Exit se saldrá de la app
        // Cerrar la actividad para que el usuario no pueda volver atrás presionando el btn back
        final ImageButton btnExit = (ImageButton) findViewById(R.id.imageButtonExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void MeGusta(View view) {
//        Toast.makeText(this, "Pulsado Me gusta", Toast.LENGTH_SHORT).show();
//    }
}
