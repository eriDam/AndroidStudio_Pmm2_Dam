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
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);}


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
