package com.examples.eri.proyecto_6_sqlite;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ListActivity {
    private DataBaseHelper mDbHelper;
    private SQLiteDatabase db;
    private SimpleCursorAdapter mAdapter;
    private Cursor c;

    private static final String TAG = "Datos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos una nueva DataBase
        mDbHelper = new DataBaseHelper(this);
        db = mDbHelper.getWritableDatabase();//creamos el sqlite através de getWritableDatabase, Sin getWritableDatabase no se genera
        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_SHORT).show();

        //Leemos la base de datos y mostramos la informacion a través del método creado en
        // DataBaseHelper readArtistas
        Cursor c = mDbHelper.readArtistas(db);
        /*Devuelve un cursor, que se puede introducir en
        un adaptador, ya que para meter los datos en un list view usamos un adaptador ya generado
         por android, este necesita que le pasemos una serie de parámetros,
        *como es el cursor, el layout, el nombre de las columnas(FROM desde donde),
        * este seria el (TO hacia donde va a ir la informacion) new int[] { R.id._id, R.id.nombre }*/
        mAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, c,
                DataBaseHelper.columns, new int[] { R.id._id, R.id.nombre },
                0);
        //El mAdapter (los datos), Se lo pasamos al setListAdapter (que sería el visor)
        setListAdapter(mAdapter);

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
