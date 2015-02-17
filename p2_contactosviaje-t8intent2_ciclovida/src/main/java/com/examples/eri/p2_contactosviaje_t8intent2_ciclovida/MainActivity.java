package com.examples.eri.p2_contactosviaje_t8intent2_ciclovida;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    //Declaramos la etiqueta y el texto a mostrar para el Log
    private static final String TAG = "Primera Ventana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo los elementos de la pantalla gráfica
        //Cojemos del recurso boton1 y lo almacenamos en la variable de tipo button
        final Button comprobar = (Button) findViewById(R.id.button1);
        final EditText editText1User=(EditText)findViewById(R.id.editText1);
        final EditText editText2Passw=(EditText)findViewById(R.id.editText2);



    //Añado un nuevo listener
    comprobar.setOnClickListener(new Button.OnClickListener(){


        @Override
        public void onClick(View v) {
            //Para intentar lanzar un nuevo activity, se utiliza la clase Intent
            //Intent espera como primer parámetro un contexto de quien esta llamando a la segunda parte,
            //
            Intent abre2 = new Intent(
                    MainActivity.this, //Utilizamos this como en java para pasarle como primer parametro quien esta llamando(First activity que hereda de activity)
                    SecondActivity.class//Y en  la segunda le pasamos a quien está llamando
            );
            //En el intent se intenta enviar la información de la primera ventana
            //Se utilizan pares de nombre y valor, el nombre será usuario y contras y cogeremos el texto que se haya introducido
            //A través del método putExtra podemos poner información básica no muy extensa
            abre2.putExtra("usuario", editText1User.getText().toString());
            abre2.putExtra("pass", editText2Passw.getText().toString());
            startActivity(abre2);
        }
    }
    );
    //El Log es la herramienta para poder controlar o hacer debug de nuestra app
    //no son mas que la lectura de lo que va pasando en nuestra aplicación.
    //Incorporamos el Log al final de cada método de la actividad de android
    Log.i(TAG, "on create");
    Log.d(TAG, "Debug");
    Log.e(TAG, "Error");
    Log.v(TAG, "Mensaje de Registro - Verbose Log");
    Log.w(TAG, "Mensaje de Advertencia - Warn");
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Generamos e incorporamos los metodos de Log, automaticamente desde botón dcho source-Override/Implement methodes
    //Override, en realidad ni sobrecarga ni sustituye, le dice al compilador que haga caso a este método y no al del padre
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.i(TAG, "onStart");
        Log.d(TAG, "Debug");
        Log.e(TAG, "Error");
        Log.v(TAG, "Mensaje de Registro - Verbose Log");
        Log.w(TAG, "Mensaje de Advertencia - Warn");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Log.i(TAG, "onResume");
        Log.d(TAG, "Debug");
        Log.e(TAG, "Error");
        Log.v(TAG, "Mensaje de Registro - Verbose Log");
        Log.w(TAG, "Mensaje de Advertencia - Warn");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i(TAG, "onPause");
        Log.d(TAG, "Debug");
        Log.e(TAG, "Error");
        Log.v(TAG, "Mensaje de Registro - Verbose Log");
        Log.w(TAG, "Mensaje de Advertencia - Warn");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.i(TAG, "onStop");
        Log.d(TAG, "Debug");
        Log.e(TAG, "Error");
        Log.v(TAG, "Mensaje de Registro - Verbose Log");
        Log.w(TAG, "Mensaje de Advertencia - Warn");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.i(TAG, "onRestart");
        Log.d(TAG, "Debug");
        Log.e(TAG, "Error");
        Log.v(TAG, "Mensaje de Registro - Verbose Log");
        Log.w(TAG, "Mensaje de Advertencia - Warn");
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        Log.d(TAG, "Debug");
        Log.e(TAG, "Error");
        Log.v(TAG, "Mensaje de Registro - Verbose Log");
        Log.w(TAG, "Mensaje de Advertencia - Warn");
    }



}