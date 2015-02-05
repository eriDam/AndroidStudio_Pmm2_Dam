package com.examples.dam.dam2pmm;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * En esta actividad tenemos dos activities, lanzamos primero nueva ventana, para ello lo
 * hemos modificado en AndroidManifest
 *  Otro activity, he cambiado el intent de activity para que se inicie con este llamado nuevaVentana
 *
 <activity
 android:name=".nuevaVentana"
 android:label="@string/title_activity_nueva_ventana" >
  La forma implícita de llamar a 1 actividad : con el inten-filter le indicamos la
 accion que queremos aplicar, que es lo que queremos lanzar, en este caso abrir la ventana
 en la accion principal-->
 <intent-filter>
 La accion, es el main, podría ser visualizar como con maps VIEW, se define
 para saber por donde debemos empezar

 <action android:name="android.intent.action.MAIN" />

 La categoría es de lanzador, tiene que lanzar el main, category se refiere a las categorias que se
 pueden definir -->
 <category android:name="android.intent.category.LAUNCHER" />
 </intent-filter>
 * */
public class nuevaVentana extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_ventana);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_ventana, menu);
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
