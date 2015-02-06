package com.examples.eri.proyecto5menuentrada;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;


public class Splash extends Activity {
    /**
     * Crearemos una constante para establecer un DELAY un retardo (la duración del
     * splashScreen, en este caso para la
     * ejecución de 5000 milisegundos
     */
    private static final long RETARDO = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Definimos la orientacion a vertical - Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Escondemos la barra de Titulo - Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash);

        //Definimos la progress bar, en este caso no vamos a indicar que se rellene
        //en función del tiempo que queda para acabar el splash pero es mejor opción
        final ProgressBar spinner;
        spinner = (ProgressBar) findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        //Creo una tarea para lanzar después del DELAY(RETARDO)
        //Un timerTask es una tarea, una clase runable (que se ejecuta en paralelo con el resto de tareas)
        //y para que esto suceda tenemos que implementar el metodo run, lo que hará run es lanzar el mainActivity2

        TimerTask task = new TimerTask() {
            @Override
            public void run() {//lanzar el mainActivity2


                //arrancar la siguiente activity
                Intent mainIntent = new Intent().setClass(
                        Splash.this, MainActivity.class);
                startActivity(mainIntent);

                //Cerrar la actividad para que el usuario no pueda volver atrás presionando el btn back
                finish();
            }
        };


        //Lo vamos a programar después de 3000 milisegundos
        //Timer planifica una tarea
        Timer timer = new Timer();
        timer.schedule(task, RETARDO);//el metodo schedule (planificar),
        // lanza 1 timerTask en un delay(retardo) en sus parametros de constructor

    }

}
