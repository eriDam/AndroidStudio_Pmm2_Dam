package com.examples.dam.ventanatimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    /**
     * Crearemos una constante para establecer un DELAY un retardo, en este caso para la
     * ejecución de 3000 milisegundos
     */
    private static final long RETARDO = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creo una tarea para lanzar después del DELAY(RETARDO)
        //Un timerTask es una tarea, una clase runable que se ejecuta en paralelo con el resto de tareas
        //y para que esto suceda tenemos que implementar el metodo run, lo que hará run es
        //lanzar el mainActivity2
        TimerTask task = new TimerTask() {
            @Override
            public void run() {


                //arrancar la siguiente activity
                Intent mainIntent = new Intent().setClass(
                        MainActivity.this, MainActivity2.class);
                startActivity(mainIntent);

                //Cerrar la actividad y el usuario puede volver atrás presionando el btn back
                finish();
            }
        };



    //Lo vamos a programar después de 3000 milisegundos
    Timer timer = new Timer();
    timer.schedule(task,RETARDO);

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
