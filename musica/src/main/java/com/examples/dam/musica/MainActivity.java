package com.examples.dam.musica;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
        // Capturamos el servicio que nos proporciona el control del volumen
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
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        // Cargamos la cancion
        mSoundId = mSoundPool.load(this, R.raw.miracle, 1);

        // Esperamos a que se cargue la cancion completa
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