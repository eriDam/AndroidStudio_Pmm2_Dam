package com.examples.eri.proyecto5menuentrada;
/**
 * @autor Érika
 *
 * Ejemplo de utilización de MediaPlayer y SoundPool
 * MediaPlayer consume mas recursos y tambien reproduce archivos mas grandes
 * ogg o mp3 no hay problema, otros no he probado
 *
 * Los recursos de audio deben incluirse en una carpeta que tenemos que crear dentro del
 * directorio “res” .Esta carpeta debe llamarse “raw”. Incluimos allí nuestros archivos de audio
 * */
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 1 PASO:
 *  Implementamos la interfaz OnClickListener
 *  */


public class Reproductor_activity extends Activity implements View.OnClickListener {
    /**
     * 2 PASO:
     *  Referenciar cada botón a un objeto de la clase “Button”, a la vez nos creamos un objeto
     *  de la clase MediaPlayer y otro objeto de la clase SoundPool y un entero al que llamaremos
     *  flujodemusica.
     * */
    public Button bplay,bstop,bsoundPool;
    public ImageButton  androidBack;
    public MediaPlayer mp;
    public SoundPool sp;
    public int flujodemusica=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        /**
         * 4 PASO:
         * Los referenciamos x su Id
         * */
        bplay = (Button)findViewById(R.id.playMp);
        bstop = (Button)findViewById(R.id.stopMp);
        bsoundPool = (Button)findViewById(R.id.playSp);
        androidBack= (ImageButton)findViewById(R.id.imageButtonAndro);
        /**
         * 5 PASO:
         * Utilizamos el método onClick,para todos los objetos
         * Indicamos qué objetos son escuchados en el método onCreate.
         * Le pasamos como parametro this
         * para decirle que será en este activity
         * */

        bplay.setOnClickListener(this);
        bstop.setOnClickListener(this);
        bsoundPool.setOnClickListener(this);

        /**
         * 7 PASO:
         * Crearemos un objeto SoundPool, la estructura de un objeto sound pool es
         * SoundPool(int maxStreams , int streamType , int srcQuality)
         *
         *int maxStreams: indica el número de veces que podemos reproducir el sonido a la vez.
         *int streamType: indica el tipo de flujo que usaremos .
         *int srcQuality: indica la calidad. Este atributo actualmente no esta en uso.
         *
         * */

        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        /**para poder utilizar los botones de audio físicos*/
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        /**
         * 7 PASO:
         * Para poder usar un objeto SoundPool, primero debemos cargar el archivo, esta
         * carga es un número entero con un flujo de información.
         * Estructura:
         * [objeto_Spoundpool].load (Context context, int resId, int priority);
         * int resid es la Id de nuestro archivo de música.
         *
         * La estructura del objeto mediaplayer viene dada por:
         * Mediaplayer.create( Context , int Id_Sound);

         * La estructura de la función sp.play() tendrá la siguiente forma:
         * sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
         */
        flujodemusica= sp.load(this,R.raw.menubit,1);

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
    /**
     * 6 PASO:
     * Implementamos el método requerido de OnClickListener
     * Escuchamos los eventos de los botones, y creamos tres métodos
     * para cada uno de nuestros casos
     * */
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.playMp){//Play media player
            play_mp();

            androidBack.setImageResource(R.drawable.rockdroid);

        }
        if(v.getId()==R.id.stopMp){//stop  media player
            stop_mp();
            androidBack.setImageResource(R.drawable.demondroid);
        }
        if(v.getId()==R.id.playSp){//Play SoundPool
            play_sp();
            androidBack.setImageResource(R.drawable.rockdroid);
        }
    }
    private void play_sp() {
        mp= MediaPlayer.create(this, R.raw.theecstasyofgold);
        mp.start();
    }
    private void stop_mp() {

        mp.stop();
    }
    private void play_mp() {
        sp.play(flujodemusica, 1, 1, 0, 0, 1);

    }
}
