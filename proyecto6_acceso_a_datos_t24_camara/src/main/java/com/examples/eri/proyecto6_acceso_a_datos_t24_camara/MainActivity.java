package com.examples.eri.proyecto6_acceso_a_datos_t24_camara;
/**
 * @author eriDam
 *
 * http://developer.android.com/guide/topics/media/camera.html
 * La clase de la cámara se utiliza para configurar los ajustes de captura de
 * imagen, iniciar / detener la vista previa, tomar fotos, y recuperar los marcos
 * para la codificación de vídeo. Esta clase es un cliente para el servicio de la
 * cámara, que gestiona el hardware actual de la cámara.

Para acceder a la cámara del dispositivo dentro de nuestro intent, debe pediro/declarar
el  permiso de CAMERA  en el Manifiesto Android, como yo la utilizaré fuera solo tendré
que pedir permiso de lamacenamiento.
 * */
import android.content.Intent;
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
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    // Types defined Constants
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
   //File stored (for save)
    private Uri fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Launch camera
        //Take  button and textView from layout
        final Button btn_camera = (Button) findViewById(R.id.btn_camera);
        final TextView tv_Archivo = (TextView) findViewById(R.id.textViewArchivo);

        //Make Event Button
        btn_camera.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
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

        }
        );

    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
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
