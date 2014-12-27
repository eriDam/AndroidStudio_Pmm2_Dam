package com.examples.dam.musica;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioManager.OnAudioFocusChangeListener;


public class MainActivity extends Activity {

    //Control de volumen
    private int mVolume = 6, mVolumeMax = 10, mVolumeMin = 0;
    //Control sonando
    private int sonando=0; //0=cancion no comenzada; 1=comenzada; 2=pause;
    private SoundPool mSoundPool;
    private int mSoundId;
    private AudioManager mAudioManager;
    private boolean mCanPlayAudio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Capturamos/llamamos a el AudioManager,es 1 servicio de android que nos proporciona
        // el control del volumen y los tonos del telefono
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Volumen actual programado
        final TextView tv = (TextView) findViewById(R.id.textViewCount);
        tv.setText(String.valueOf(mVolume));

        // Subir volumen
        final Button upButton = (Button) findViewById(R.id.buttonS);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // El AudioManager puede realizar efectos, como hacer sonar el efecto del click
                mAudioManager.playSoundEffect(AudioManager.FX_KEY_CLICK,mVolume);

                if (mVolume < mVolumeMax) {
                    mVolume += 2;
                    tv.setText(String.valueOf(mVolume));
                }
            }
        });

        // Bajar Volumne
        final Button downButton = (Button) findViewById(R.id.buttonB);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hacer sonar el efecto de click
                mAudioManager.playSoundEffect(AudioManager.FX_KEY_CLICK,mVolume);

                if (mVolume > mVolumeMin) {
                    mVolume -= 2;
                    tv.setText(String.valueOf(mVolume));
                }

            }
        });

        // Desactivamos el boton del play
        final Button playButton = (Button) findViewById(R.id.buttonPlay);
        playButton.setEnabled(false);

        // Creamos el manejador del sonido
        /**SoundPool es 1 clase para reproducir pequeñas pistas de audio. Con esta clase podemos repetir
         *  la reproducción de sonidos y hasta reproducir múltiples sonidos de manera simultánea.
         *  Recordar que los archivos no deben superar 1MB
         *  El primer parametro es cunatos strings vamos a manejar en paralelo, en el 2 le indicamos que es
         *  de tipo MUSIC y el 3 es la calidad, pero no tiene efecto ahora*/

          mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        /** Cargamos la cancion, le pasamos 3 parametros en el constructor, this para indicar el contexto sobre donde
         * lo vamos a lanzar (sobre el activity), el 2 param es el recurso id(la cancion), 3 param la prioridad*/
        mSoundId = mSoundPool.load(this, R.raw.miracle, 1);

        // Esperamos a que se cargue la cancion completa
        //comprobar si la carga se ha completado a través de un objeto OnLoadCompleteListener.
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                Log.d("AUDIO", "Cargada la cancion");
                if (0 == status) {
                    playButton.setEnabled(true);
                    Log.d("AUDIO", "Cargada correctamente");
                }
            }
        });

        // Suena la cancion
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sonando==0){
                    playButton.setText("||");
                    sonando=1;
                    if (mCanPlayAudio)
                        mSoundPool.play(mSoundId, (float) mVolume / mVolumeMax,
                                (float) mVolume / mVolumeMax, 1, 0, 1.0f);
                }else if(sonando==1){
                    playButton.setText("Play");
                    sonando=2;
                    mSoundPool.pause(mSoundId);
                }else{
                    playButton.setText("||");
                    sonando=1;
                    mSoundPool.resume(mSoundId);
                }
            }

        });

        // Request audio focus - solicitamos el foco de sonido de nuestro aplicativo
        int result = mAudioManager.requestAudioFocus(afChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        mCanPlayAudio = AudioManager.AUDIOFOCUS_REQUEST_GRANTED == result;
    }

    // Get ready to play sound effects
    @Override
    protected void onResume() {
        Log.d("AUDIO", "VOLVIENDO A TOCAR");
        super.onResume();
        mAudioManager.setSpeakerphoneOn(true);
        mAudioManager.loadSoundEffects();
    }

    // Release resources & clean up
    @Override
    protected void onPause() {
        Log.d("AUDIO", "EN PAUSA");
        if (null != mSoundPool) {
            mSoundPool.unload(mSoundId);
            mSoundPool.release();
            mSoundPool = null;
        }
        mAudioManager.setSpeakerphoneOn(false);
        mAudioManager.unloadSoundEffects();
        super.onPause();
    }

    // Listen for Audio focus changes
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mAudioManager.abandonAudioFocus(afChangeListener);
                mCanPlayAudio = false;
            }
        }
    };


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