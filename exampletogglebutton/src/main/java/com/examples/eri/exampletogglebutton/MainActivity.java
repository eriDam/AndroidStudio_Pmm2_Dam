package com.examples.eri.exampletogglebutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

//El togglebutton tiene su clase especial por eso implementa

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
    //Definimos nuestras variables
    ToggleButton tButton1;
    //También vamos amanipular el image view
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Los localizamos mediante su id
        tButton1=(ToggleButton)findViewById(R.id.tButton1);
        iv1=(ImageView)findViewById(R.id.iv1);

        //Asignarle a togleButton1 el setOnCheckedChangeListener,
        // le decimos que escuche a traves de esta interfaz y ya se puede
        //manipular dentro del método onCheckedChanged línea 58
        tButton1.setOnCheckedChangeListener(this);
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
    //Al implementar la clase OnCheckedChangeListener tenemos que implementar este metodo
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //Con el conficional le decimos que si está checked (si es true) iv1 inserte una imagen
          if (tButton1.isChecked()){
            iv1.setImageResource(R.drawable.android_pat_sec);
        }else{
              //Si no está check sacará otra imagen
              iv1.setImageResource(R.drawable.android_pat_first);
          }
    }
}
