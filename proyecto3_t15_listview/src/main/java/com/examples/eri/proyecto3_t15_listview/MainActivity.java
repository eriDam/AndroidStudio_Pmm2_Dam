package com.examples.eri.proyecto3_t15_listview;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

//Extendemos de ListActivity para usar todas las funciones que nos da este activity para los list views
public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); Esta linea se debe quitar, no es necesaria en ListActivity
        /**
         * Utilizamos el setListAdapter para colocar los datos que queremos dentro
         * del listView.
         * Se utiliza un new arrayAdapter para introducir objetos dentro de
         * listView, será de strings que es el tipo que hemos definido, como parámetros,
         * coloco this, para colocarlo en este contexto en el
         * MainActivity, después el Id del activity y por ultimo el conjunto de arrays que queremos colocar,
         * lo cojemos de resources, convertimos de r.array color en un string array y se lo devuelve
         * al arrayAdapter.
         * */
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,
                getResources().getStringArray(R.array.colors)));
    }
}
