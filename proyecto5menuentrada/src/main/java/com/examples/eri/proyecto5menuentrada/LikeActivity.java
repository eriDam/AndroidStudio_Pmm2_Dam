package com.examples.eri.proyecto5menuentrada;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class LikeActivity extends ListActivity {
    private DataBaseHelper mDbHelper;
    private SQLiteDatabase db;
    private SimpleCursorAdapter mAdapter;
    private Cursor c;
    private static final String TAG = "Datos";

    public static final String C_MODO  = "modo" ;
    public static final int C_VISUALIZAR = 551 ;
    public static final int C_CREAR = 552 ;
    public static final int C_EDITAR = 553 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        // Creamos una nueva DataBase
        mDbHelper = new DataBaseHelper(this);
        //creamos el sqlite através del método getWritableDatabase del dataBaseHelper que es a la vez
        // un comando de SQLiteOpenHelper, Sin el uso de getWritableDatabase no se genera
        db = mDbHelper.getWritableDatabase();

        //Lanzamos un Pop up con aviso
        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_SHORT).show();

        //Leemos la base de datos y mostramos la informacion a través del método creado en
        // DataBaseHelper readBd
        Cursor c = mDbHelper.readBd(db);
        /*mDbHelper.readBd(db)  Devuelve un cursor c, que se puede introducir en
        un adaptador, ya que para meter los datos en un list view usamos un adaptador ya generado
         por android, este necesita que le pasemos una serie de parámetros,
        *como el cursor, el layout,  (FROM desde donde)el nombre de las columnas,
        * este seria el TO (hacia donde va a ir la informacion) new int[] { R.id._id, R.id.nombre }*/
        mAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, c,
                DataBaseHelper.columns, new int[] { R.id._id, R.id.nombre },
                0);
        //El mAdapter (los datos), Se lo pasamos al setListAdapter (que sería el visor), una vez que
        //se lo pasamos ya nos muestra la lista
        setListAdapter(mAdapter);


        //Añadimos el listener del boton
        final Button boton=(Button) findViewById(R.id.addBtn);
        boton.setOnClickListener(new Button.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent i = new Intent(LikeActivity.this, Formulario.class);
                                         i.putExtra(C_MODO, C_CREAR);
                                         startActivityForResult(i, C_CREAR);
                                     }
                                 }
        );

    }
    public void editHandler(View v) {
        //get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        TextView id =(TextView) vwParentRow.findViewById(R.id._id);
        Intent i = new Intent(LikeActivity.this, Formulario.class);

        i.putExtra(C_MODO, C_EDITAR);
        i.putExtra(mDbHelper.ID, Long.valueOf((String)id.getText()));


        this.startActivityForResult(i, C_EDITAR);
    }

    public void viewHandler(View v) {
        //get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        TextView id =(TextView) vwParentRow.findViewById(R.id._id);
        Intent i = new Intent(LikeActivity.this, Formulario.class);

        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(mDbHelper.ID, Long.valueOf((String)id.getText()));


        this.startActivityForResult(i, C_VISUALIZAR);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_like, menu);
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

    // Close database
    @Override
    protected void onDestroy() {

        mDbHelper.deleteDatabase();
        super.onDestroy();

    }

    //Visualizar un dato en Formulario
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        // Llamamos a la Actividad Formulario indicando el modo visualización y el identificador del registro
        Intent i = new Intent(LikeActivity.this, Formulario.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(mDbHelper.ID, id);

        startActivityForResult(i, C_VISUALIZAR);
    }

    //CApturamos la respuesta a la creación de registro
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //
        // Nos aseguramos que es la petición que hemos realizado
        //
        switch(requestCode)
        {
            case C_CREAR:
                if (resultCode == RESULT_OK)
                    //Leemos la base de datos y mostramos la informacion
                    c=mAdapter.getCursor();
                c=mDbHelper.readBd(db);
                mAdapter.changeCursor(c);
                mAdapter.notifyDataSetChanged();
            case C_EDITAR:
                if (resultCode == RESULT_OK)
                    //Leemos la base de datos y mostramos la informacion
                    c=mAdapter.getCursor();
                c=mDbHelper.readBd(db);
                mAdapter.changeCursor(c);
                mAdapter.notifyDataSetChanged();
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
