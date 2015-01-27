package com.examples.eri.proyecto5menuentrada;


import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.widget.ToggleButton;

import static android.media.MediaPlayer.OnPreparedListener;

/**
        * @author erika_000
        *
        * PROYECTO 5 - MENÚ DE ENTRADA - Splash Screen - Reproducciendo música
        * con MediaPlayer - TableLayout - ToggleB desactivar sonido - Exit
        *
        *
        * 2.	Modificaremos las líneas necesarias para sustituir SoundPool por MediaPlayer
        *
        * La principal diferencia entre SoundPool y mediaPlayer es que MediaPlayer consume muchos mas recursos
        * que SoundPool, MediaPlayer también reproduce archivos mas grandes e incluso de video
        * */

public class ActivityMusic extends Activity implements CompoundButton.OnCheckedChangeListener{
    //Control de volumen
    private int mVolume = 6, mVolumeMax = 10, mVolumeMin = 0;
    //Control sonando música
    private int sonando=0; //0=cancion no comenzada; 1=comenzada; 2=pause;

    // /colocamos lo mismo para compartir las preferencias con el layout del menu
    private SharedPreferences preferencias;
    private boolean audioAct;
    public static final String PREFS_NAME = "AmorOdioSettings";

    //Definimos nuestras variables
    ToggleButton tButton1;
    //También vamos amanipular el text view
    TextView tView;

    //Creamos el MediaPlayer
    private MediaPlayer mPlayer;
    private int mSoundId;
    private AudioManager mAudioManager;
    private boolean mCanPlayAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music);
        //Los localizamos mediante su id
        tButton1=(ToggleButton)findViewById(R.id.toggleButton);
        tView=(TextView)findViewById(R.id.tView);

        /**Asignarle a togleButton1 el setOnCheckedChangeListener,
         * le decimos que escuche a traves de esta interfaz y ya se puede
         * manipular dentro del método onCheckedChanged línea 58*/

        tButton1.setOnCheckedChangeListener(this);

        preferencias = getSharedPreferences(PREFS_NAME, 0);
        audioAct = preferencias.getBoolean("Audio", true);
        Log.d("AUDIO SET", String.valueOf(audioAct));




        // Capturamos/llamamos a el AudioManager, es 1 servicio de android que nos proporciona
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
                mAudioManager.playSoundEffect(AudioManager.FX_KEY_CLICK, mVolume);

                if (mVolume < mVolumeMax) {
                    mVolume += 2;
                    tv.setText(String.valueOf(mVolume));
                    //Cuando le demos a subir volumen, Le pasamos el volumen izqd y dcho, 2 canales y cada uno con un volumen
                    mPlayer.setVolume((float) mVolume / mVolumeMax, (float) mVolume / mVolumeMax);
                }
            }
        });

        // Bajar Volumen de la canción
        final Button downButton = (Button) findViewById(R.id.buttonB);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hacer sonar el efecto de click
                mAudioManager.playSoundEffect(AudioManager.FX_KEY_CLICK, mVolume);

                if (mVolume > mVolumeMin) {
                    mVolume -= 2;
                    tv.setText(String.valueOf(mVolume));
                }
                mPlayer.setVolume((float) mVolume / mVolumeMax,
                        (float) mVolume / mVolumeMax);
            }
        });

        // Desactivamos el boton del play
        final Button playButton = (Button) findViewById(R.id.buttonPlay);
        playButton.setEnabled(false);
         /*CARGAMOS LA CANCIÓN NUEVA FORMA - NO HACE FALTA GENERAR UN NUEVO SOUNDPOOL
        * Con el método estático MediaPlayer create, le pasamos this  para indicarle este activity
        * y dónde está el Id de la canción. Una vez que lo cree me devolverá un indentificador mPlayer como objeto MediaPlayer*/
        mPlayer = MediaPlayer.create(this, R.raw.miraclelong);
/**Hemos sustituido el setOnLoadCompleteListener por setOnPreparedListener, que lo que implementa
 * es un Listener de tipo onPrepare*/

        mPlayer.setOnPreparedListener(new OnPreparedListener() {
                                          @Override
                                          public void onPrepared(MediaPlayer mp) {
                                              Log.d("AUDIO", "Cargada la cancion");
                                              //ponemos el playButton activo
                                              playButton.setEnabled(true);
                                          }
                                      }
        );
        // Suena la cancion
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonando == 0) {
                    playButton.setText("||");
                    sonando = 1;
                    if (mCanPlayAudio)
                        mPlayer.setVolume((float) mVolume / mVolumeMax,
                                (float) mVolume / mVolumeMax);
                    mPlayer.start();
                    //mSoundPool.play(mSoundId, (float) mVolume / mVolumeMax,
                    //        (float) mVolume / mVolumeMax, 1, 0, 1.0f);
                } else if (sonando == 1) {
                    playButton.setText("Play");
                    sonando = 2;
                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);
                    mPlayer.pause();
                    //mSoundPool.pause(mSoundId);
                } else {
                    playButton.setText("||");
                    sonando = 1;
                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);
                    mPlayer.start();
                    //mSoundPool.resume(mSoundId);
                }
            }

        });
////ToggleB desactivar sonido
//
//        final ToggleButton togBtnSound = (ToggleButton) findViewById(R.id.toggleButton);
//        togBtnSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Toggle the Background color between a light and dark color
//                if (togBtnSound.isChecked())
//
//                {
//                    mPlayer.start();
//                } else{
//                    mPlayer.stop();
//                }
//            }
//        });

        // Request audio focus
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

    // Es importante hacer un release, si en algún momento nos vamos de la aplicación
    //liberará los recursos del sistema, ya que consume bastante
    @Override
    protected void onPause() {
        Log.d("AUDIO", "EN PAUSA");
        if (null != mAudioManager) {
            mPlayer.release();
            //mSoundPool.unload(mSoundId);
            //mSoundPool.release();
            //mSoundPool = null;
        }
        mAudioManager.setSpeakerphoneOn(false);
        mAudioManager.unloadSoundEffects();
        super.onPause();
    }

    // Listen for Audio focus changes
    OnAudioFocusChangeListener afChangeListener = new OnAudioFocusChangeListener() {
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
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
    //Al implementar la clase OnCheckedChangeListener tenemos que implementar este metodo
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //Con el conficional le decimos que si está checked (si es true) iv1 inserte una imagen
        if (tButton1.isChecked()){
            tView.setText("Sonido Activado");
            mPlayer.start();

        }else if(sonando==1){
            //Si no está check sacará otra texto
            tView.setText("Sonido Desactivado");
            mPlayer.stop();
           // mPlayer.release();//Libero el recurso
        }else{
            mPlayer.start();
        }
    }
}
