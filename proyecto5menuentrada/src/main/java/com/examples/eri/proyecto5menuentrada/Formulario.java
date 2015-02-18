package com.examples.eri.proyecto5menuentrada;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Formulario extends Activity {
    private TextView labelId;
    private EditText nombre;

    //Identificador de la base de datos
    private DataBaseHelper mDbHelper;
    private SQLiteDatabase db;
    private long id ;

    // Types defined Constants
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    //File stored (for save)
    private Uri fileUri;

    //
    // Modo del formulario
    //
    private int modo ;

    //Botones y tv
    private Button boton_guardar;
    private Button boton_cancelar;
    private Button btn_img;
    private TextView tv_Archivo;
    private Button btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //CApturamos los datos enviados
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra == null) return;

        // Consultamos la base de datos
        mDbHelper = new DataBaseHelper(this);
        db = mDbHelper.getWritableDatabase();

        //
        // Obtenemos los elementos de la vista
        //
        labelId = (TextView) findViewById(R.id.label_id);
        nombre  = (EditText) findViewById(R.id.nombre);

        //
        // Obtenemos el identificador del registro si viene indicado
        //
        if (extra.containsKey(DataBaseHelper.ID))
        {
            id = extra.getLong(DataBaseHelper.ID);
            consultar(id);
        }
        //Botones de guardado y cancelar
        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);

        //
        // Establecemos el modo del formulario
        //
        establecerModo(extra.getInt(mDbHelper.C_MODO));

        //
        // Definimos las acciones para los   botones
        //
        boton_guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                guardar();
            }
        });

        boton_cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                cancelar();
            }
        });
        //Boton imagen activará la cámara
        //Take  button and textView from layout
        btn_img = (Button) findViewById(R.id.btn_img);
        tv_Archivo = (TextView) findViewById(R.id.textViewUri);
        //Make Event Button
        btn_img.setOnClickListener(new Button.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           activarCamara();
                                       }

                                   }
        );



    }

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    private void consultar(long id)
    {
        //
        // Consultamos el centro por el identificador
        //
        Cursor cursor = mDbHelper.getRegistro(id);
        labelId.setText(labelId.getText()+cursor.getString(cursor.getColumnIndex(DataBaseHelper.ID)));
        nombre.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.CASE_NAME)));
    }

    private void establecerModo(int m)
    {
        this.modo = m ;

        if (modo == mDbHelper.C_VISUALIZAR)
        {
            this.nombre.setEnabled(false);
            this.boton_guardar.setEnabled(false);
        }else if ((modo == mDbHelper.C_CREAR)||(modo == mDbHelper.C_EDITAR)){
            this.setTitle(R.string.hipoteca_crear_item);
            this.nombre.setEnabled(true);
            this.boton_guardar.setEnabled(true);
        }
    }
    private void guardar()
    {
        //
        // Obtenemos los datos del formulario
        //
        ContentValues reg = new ContentValues();
        if (modo == mDbHelper.C_EDITAR) reg.put(mDbHelper.ID, id);
        reg.put(mDbHelper.CASE_NAME, nombre.getText().toString());

        if (modo == mDbHelper.C_CREAR)
        {
            mDbHelper.insert(reg);
            Toast.makeText(Formulario.this, "Item creado", Toast.LENGTH_SHORT).show();
        }   else if (modo == mDbHelper.C_EDITAR){
            Toast.makeText(Formulario.this, "Item modificado", Toast.LENGTH_SHORT).show();
            mDbHelper.update(reg);
        }

        //
        // Devolvemos el control
        //
        setResult(RESULT_OK);
        finish();
    }
    private void cancelar()
    {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }
    //Activar camara
    private void activarCamara(){
        // create Intent to take a picture and return control to the calling application
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        //Say at intent where save , if we do not specify this value, the camera palication save
        //the request picture in default location with a default name
        camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file/uri name at intent

        // start the image capture Intent
        startActivityForResult(camera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        //set the file name
        tv_Archivo.setText(fileUri.getPath());
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
