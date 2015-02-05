package com.examples.eri.proyecto5menuentrada;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Settings_activity extends Activity {

    /**
     * Guardar preferencias de usuario:
     * Capturamos un bolean introducidos por el usuario al activar el boton
     * , de forma que cuando se inicie de nuevo, recuerda estos datos
     *
     * Creamos  las preferencias de usuario
     * */
    private SharedPreferences pref;
    private boolean audioActivado;
    public static final String PREFS_NAME = "AmorOdioSettings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);

        //Capturamos el toggleButton del layout dentro de onCreate
        final ToggleButton  toggleButtonSounds;
        toggleButtonSounds = (ToggleButton)findViewById(R.id.toggleButtonSounds);

        /**
         * Capturamos las preferencias de usuario
         * Con la clase SharedPreferences realizaremos 2 partes, lectura y escritura
         *
         * Lectura: cogeremos las preferencias de este activity en
         * modo privado MODE_PRIVATE(solo serán utilizadas para esta app) creando un objeto de tipo
         * SharedPreferences
         *
         */
            pref = getPreferences(MODE_PRIVATE);
        /**con nuestro objeto pref y getInt o getString, cogeremos los
         * valores introducidos por el usuario*/
        toggleButtonSounds.setChecked(pref.getBoolean("activado",true));

    }

    //Evento para toggle cuando este activado
    public void onToggleClicked(View v) {
        // Perform action on clicks
        if (((ToggleButton) v).isChecked()) {
            Toast.makeText(Settings_activity.this, "Toggle on", Toast.LENGTH_SHORT).show();
            /**En el evento del botón:
             * Para escribir
             *  Cogeremos el Editor y obtendremos los datos de las cajas de texto directamente*/
            SharedPreferences.Editor prefEd = pref.edit();

            prefEd.putBoolean("activado", true);


            /**
             * Una vez que lo tenemos definido, realizaremos un commit y con esto queda guardado,
             * mostramos un mensaje emergente informando al usuario.
             * */
            prefEd.commit();
            //MessageBox("Guardadas preferencias");

        } else {
            Toast.makeText(Settings_activity.this, "Toggle off", Toast.LENGTH_SHORT).show();
            //SharedPreferences.Editor prefEd = pref.edit();

            //prefEd.putBoolean("Desactivado", false);


            /**
             * Una vez que lo tenemos definido, realizaremos un commit y con esto queda guardado,
             * mostramos un mensaje emergente informando al usuario.
             * */
           // prefEd.commit();
        }
    }

    //Metodo para usar el messaje Box
    public void MessageBox(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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
