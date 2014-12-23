package com.examples.dam.preferencias;

import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Capturamos los 2 editText del layout
        final EditText TextNombre = (EditText) findViewById(R.id.editTextN);
        final EditText TextEdad = (EditText) findViewById(R.id.editTextE);

        //Capturamos las preferencias de usuario
        final SharedPreferences pref = getPreferences(MODE_PRIVATE);
        TextEdad.setText(String.valueOf(pref.getInt("age", 0)));
        TextNombre.setText(pref.getString("name", ""));

        //Programar Botón
        final Button guardar = (Button) findViewById(R.id.buttonG);

        guardar.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor prefEd = pref.edit();

                    prefEd.putInt("age", Integer.parseInt(TextEdad.getText().toString()));
                    prefEd.putString("name", TextNombre.getText().toString());

                    prefEd.commit();
                    MessageBox("Guardado");
                                     }
            }

        ) ;

    }
     //Metodo para usar el messaje Box
     public void MessageBox(String message){
         Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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
