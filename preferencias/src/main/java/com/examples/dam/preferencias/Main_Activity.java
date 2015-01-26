package com.examples.dam.preferencias;
/**
 * Ejemplo Guardar preferencias de usuario
 * Capturamos un String y un int introducidos por el usuario y almacenamos
 * el contenido, de forma que cuando se inicie de nuevo, recuerda estos datos
 * */
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main_Activity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Capturamos los 2 editText del layout
        final EditText TextNombre = (EditText) findViewById(R.id.editTextN);
        final EditText TextEdad = (EditText) findViewById(R.id.editTextE);

        /**
         * Capturamos las preferencias de usuario
         * Con la clase SharedPreferences realizaremos 2 partes, lectura y escritura
         *
         * Lectura: cogeremos las preferencias de este activity en
         * modo privado(solo serán utilizadas para esta app) creando un objeto de tipo
         * SharedPreferences
         *
        */
        final SharedPreferences pref = getPreferences(MODE_PRIVATE);
        /**con nuestro objeto pref y getInt o getString, cogeremos los
         * valores introducidos por el usuario*/
        TextEdad.setText(String.valueOf(pref.getInt("age", 0)));
        TextNombre.setText(pref.getString("name", ""));

        //Programar Botón
        final Button guardar = (Button) findViewById(R.id.buttonG);


        guardar.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**En el evento del botón:
                     * Para escribir
                     *  Cogeremos el Editor y obtendremos los datos de las cajas de texto directamente*/
                    SharedPreferences.Editor prefEd = pref.edit();

                    prefEd.putInt("age", Integer.parseInt(TextEdad.getText().toString()));
                    prefEd.putString("name", TextNombre.getText().toString());

                    /**
                     * Una vez que lo tenemos definido, realizaremos un commit y con esto queda guardado,
                     * mostramos un mensaje emergente informando al usuario.
                     * */
                    prefEd.commit();
                    MessageBox("Guardado");
                                     }
            }

        ) ;

    }
     //Metodo para usar el messaje Box
     public void MessageBox(String message){
         Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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
}
