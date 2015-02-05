package com.examples.eri.proyecto5menuentrada;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;


public class MainActivity extends  Activity {
    /**
     * Guardar preferencias de usuario: Para que cuando se inicie de nuevo, recuerde estos datos
     * Capturamos un bolean introducidos por el usuario al activar el boton
     * Creamos  las preferencias de usuario
     * */
    private SharedPreferences pref;
    private boolean audioActivado;
    public static final String PREFS_NAME = "AmorOdioSettings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        /**
         * Capturamos las preferencias de usuario
         * Con la clase SharedPreferences realizaremos 2 partes, lectura y escritura
         *
         * Lectura: cogeremos las preferencias de este activity en
         * modo privado MODE_PRIVATE(solo serán utilizadas para esta app) creando un objeto de tipo
         * SharedPreferences
         *
         */

        pref = getSharedPreferences(PREFS_NAME, 0);
        audioActivado = pref.getBoolean("Audio", true);//Si no hay preferencias será true por defecto
        Log.d("AUDIO SET", String.valueOf(audioActivado));

        //Directamente pondremos la musica de fondo para que se inicie y se repita con el loop
        //Dejo comentado es lo que no puedo parar

       /* MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(this, R.raw.miraclelong);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(50,50);
        mediaPlayer.start();*/


        // Al pulsar el imageButton Settings abrirá la siguiente ventana Music_activity con el intent filter
        final ImageButton btnSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arrancar la siguiente activity
                Intent mainIntent;
                mainIntent = new Intent().setClass(
                        MainActivity.this, Music_activity.class);
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
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            final ToggleButton toggleButtonP = (ToggleButton)findViewById(R.id.toggleButtonMusica);
            if ( requestCode == 1 ){
                if ( resultCode == Activity.RESULT_OK ){

                    //t.setText(data.getExtras().get("text").toString());
                }
            }

        }
}
