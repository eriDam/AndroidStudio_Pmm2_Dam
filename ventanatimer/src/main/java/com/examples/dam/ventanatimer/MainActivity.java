package com.examples.dam.ventanatimer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    /**
     * Crearemos una constante para establecer un DELAY un retardo (la duración del
     * splashScreen, en este caso para la
     * ejecución de 3000 milisegundos
     */
    private static final long RETARDO = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Definimos la orientacion a vertical - Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Escondemos la barra de Titulo - Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Creo una tarea para lanzar después del DELAY(RETARDO)
        //Un timerTask es una tarea, una clase runable que se ejecuta en paralelo con el resto de tareas
        //y para que esto suceda tenemos que implementar el metodo run, lo que hará run es

        TimerTask task = new TimerTask() {
            @Override
            public void run() {//lanzar el mainActivity2


                //arrancar la siguiente activity
                Intent mainIntent = new Intent().setClass(
                        MainActivity.this, MainActivity2.class);
                startActivity(mainIntent);

                //Cerrar la actividad para que el usuario no pueda volver atrás presionando el btn back
                finish();
            }
        };



    //Lo vamos a programar después de 3000 milisegundos
    Timer timer = new Timer();
    timer.schedule(task,RETARDO);

}

}
