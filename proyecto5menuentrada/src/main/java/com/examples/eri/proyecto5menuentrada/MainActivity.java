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
import android.widget.Toast;


public class MainActivity extends Activity {
    /**
     * Guardar preferencias de usuario: Para que cuando se inicie de nuevo, recuerde estos datos
     * Capturamos un bolean introducidos por el usuario al activar el boton
     * Creamos  las preferencias de usuario
     */
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

        // Al pulsar el imageButton me gusta se mostará una lista con dos fragments, de momento sale un mensaje
        // Cerrar la actividad para que el usuario no pueda volver atrás presionando el btn back
        final ImageButton imageButtonLike = (ImageButton) findViewById(R.id.imageButtonLike);
        imageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageBox("Pulsado Me gusta");
                //arrancar la siguiente activity
                Intent abreListaLike;
                abreListaLike = new Intent().setClass(
                        MainActivity.this, LikeActivity.class);
                startActivity(abreListaLike);
            }
        });

        // Al pulsar el imageButton No me gusta se mostará una lista con dos fragments, de momento sale un mensaje
        // Cerrar la actividad para que el usuario no pueda volver atrás presionando el btn back
        final ImageButton imageButtonNotLike = (ImageButton) findViewById(R.id.imageButtonNotLike);
        imageButtonNotLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageBox("Pulsado No me gusta");
            }
        });

        // Al pulsar el imageButton Musica abrirá la siguiente ventana media con el intent filter
        final ImageButton imageButtonReprod = (ImageButton) findViewById(R.id.imageButtonReprod);
        imageButtonReprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arrancar la siguiente activity
                Intent mainIntentRep;
                mainIntentRep = new Intent().setClass(
                        MainActivity.this, Reproductor_activity.class);
                startActivity(mainIntentRep);
            }
        });
        // Al pulsar el imageButton Camara abrirá la siguiente ventana media con el intent filter
        final ImageButton imageButtonCam = (ImageButton) findViewById(R.id.imageButtonCam);
        imageButtonCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arrancar la siguiente activity
                Intent mainIntentCam;
                mainIntentCam = new Intent().setClass(
                        MainActivity.this, Media_activity.class);
                startActivity(mainIntentCam);
            }
        });

        // Al pulsar el imageButton Settings abrirá la siguiente ventana Music_activity con el intent filter
        final ImageButton btnSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arrancar la siguiente activity
                Intent mainIntentSt;
                mainIntentSt = new Intent().setClass(
                        MainActivity.this, Music_activity.class);
                startActivity(mainIntentSt);
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

    //Metodo para usar el messaje Box
    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //Metodo para coger coger el boton de musica de otro activity
     /*   @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            final ToggleButton toggleButtonP = (ToggleButton)findViewById(R.id.toggleButtonMusica);
            if ( requestCode == 1 ){
                if ( resultCode == Activity.RESULT_OK ){

                    //t.setText(data.getExtras().get("text").toString());
                }
            }*/

}


