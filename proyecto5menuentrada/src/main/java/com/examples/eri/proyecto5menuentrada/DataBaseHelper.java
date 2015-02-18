package com.examples.eri.proyecto5menuentrada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by erika_000 on 16/02/2015.
 */

/*Creamos esta clase propia extendida de SQLiteOpenHelper, es una clase especifica, un ayudante para
* manejar las BDS dentro de SQLite, debemos sobreescribir tanto oncreate como onUpgrade,
 * estas deben estar definidads como minimo*/
public class DataBaseHelper extends SQLiteOpenHelper {

    //Datos de la tabla - Defino Atributos
    //Ponemos el nombre de la Base de datos
    final private static String NAME = "megusta_nomegusta_db";
    //Indicamos el nombre de la única tabla que tendremos
    final static String TABLE_NAME = "tabla_gustos";

    /*Indicamos las columnas, el _id, se define de esta forma, por que para
     *posteriores lecturas y adaptadores (adapters), se necesita q este este campo,
     *no se le puede quitar el guión*/
    final static String ID = "_id";
    //final static String ICON = "icon";
    final static String CASE_NAME = "nombre";

    //Comandos
    /**Dentro de un array d Strings, almaceno las columnas*/
    //final static String[] columns = { ID,ICON, CASE_NAME };
    final static String[] columns = { ID, CASE_NAME };
    final private static String CREATE_CMD =

            "CREATE TABLE "+ TABLE_NAME +" (" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CASE_NAME + " TEXT NOT NULL)";


    final private static Integer VERSION = 1;
    final private Context mContext;
    //Modos edicion
    public static final String C_MODO  = "modo" ;
    public static final int C_VISUALIZAR = 551 ;
    public static final int C_CREAR = 552 ;
    public static final int C_EDITAR = 553 ;


    //Constructor
    public DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    //Creación de la base de datos, el onCreate lo utilizaremos tanto para generar la tabla
    //como para crear/rellenar los campos dentro de la misma, para ello utilizaremos la clase ContentValues
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la base de datos
        Log.i(this.getClass().toString(), "Tabla GUSTOS creada");
        //Generamos la tabla, CREATE_CMD es un string que contiene la sentencia SQL para la creación de la tabla
        db.execSQL(CREATE_CMD);
        /*Rellenamos la tabla,  utilizamos la Clase ContentValues, que permite de una forma fácil
        * con el método put indicamos el campo o campos y el valor y con insert, le idnicamos la tabla */
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.CASE_NAME, "Si Deporte");
       // values.put(DataBaseHelper.ICON,findViewById(R.id.icon));
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DataBaseHelper.CASE_NAME, "Si ciencia");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DataBaseHelper.CASE_NAME, "Si tecnología");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        Log.i(this.getClass().toString(), "Datos insertados");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Método para Borrado de la base de datos
    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }

    //Método accesible desde fuera para la LECTURA de la Base de datos
    //Cursor es una clase de List, new String [] {} es por si queremos pasar mas parametros, en este caso no
    //Cursor Es como una lista dinámica de los elementos que contiene la consulta realizada
    public Cursor readBd(SQLiteDatabase db) {
        return db.query(TABLE_NAME,
                columns,null,new String [] {}, null,null,
                null);

    }
    /**
     * Devuelve cursor con todos las columnas del registro
     */
    public Cursor getRegistro(long id) throws SQLException
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.query( true, TABLE_NAME, columns, ID + "=" + id, null, null, null, null, null);

        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Inserta los valores en un registro de la tabla
     */
    public long insert(ContentValues reg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, reg);
    }
    /**
     * Inserta los valores en un registro de la tabla
     */
    public long update(ContentValues reg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        if (reg.containsKey(ID))
        {
            //
            // Obtenemos el id y lo borramos de los valores
            //
            long id = reg.getAsLong(ID);

            reg.remove(ID);

            //
            // Actualizamos el registro con el identificador que hemos extraido
            //
            return db.update(TABLE_NAME, reg, "_id=" + id, null);
        }
        return db.insert(TABLE_NAME, null, reg);
    }
}
