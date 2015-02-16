package com.examples.eri.proyecto_6_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by erika_000 on 16/02/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //Datos de la tabla
    final private static String NAME = "artistas_db";
    final static String TABLE_NAME = "artistas";

    final static String ID = "_id";
    final static String ARTIST_NAME = "nombre";

    //Comandos
    final static String[] columns = { ID, ARTIST_NAME };
    final private static String CREATE_CMD =

            "CREATE TABLE "+ TABLE_NAME +" (" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ARTIST_NAME + " TEXT NOT NULL)";


    final private static Integer VERSION = 1;
    final private Context mContext;


    //Constructor
    public DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la base de datos
        Log.i(this.getClass().toString(), "Tabla ARTISTAS creada");
        db.execSQL(CREATE_CMD);
        //La rellenamos
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ARTIST_NAME, "Vetusta Morla");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DataBaseHelper.ARTIST_NAME, "ColdPlay");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DataBaseHelper.ARTIST_NAME, "All India Radio");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        Log.i(this.getClass().toString(), "Datos insertados");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
