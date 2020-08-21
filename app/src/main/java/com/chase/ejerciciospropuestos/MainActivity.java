package com.chase.ejerciciospropuestos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //cadena para el pintado en la pantalla de operaciones
    private String strCadena;
    //es un posicionador
    private int intPosicion;
    private boolean bolPunto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializar();
        setContentView(R.layout.activity_main);
    }

    private void inicializar(){
        intPosicion = 0;   // indicara el tamaño de la cadena a procesar
        bolPunto = false;  // indicará si se puede utilizar el punto, para no ingresar dos puntos
        strCadena = new String("");
    }

    public void procesar(View view) {
        String strTag = view.getTag().toString();
        EditText txtOperaciones = findViewById(R.id.txtOperaciones);
        ///txtOperaciones.setText(strTag + "----" + getString(R.string.Sumar));
        ///Toast.makeText(this,"llamada", Toast.LENGTH_SHORT);

        if (strTag.equals(getString(R.string.Ejecutar))) {
            if(intPosicion > 0) bolPunto = false;
        }

        if (strTag.equals(getString(R.string.Borrar))) {
            if(intPosicion > 0) {
                inicializar();
                bolPunto = false;
            }
            strTag = "";
        }

        if (strTag.equals(getString(R.string.QuitarUltimo))) {
            if(intPosicion > 0)
                strTag = "";
        }

        if (strTag.equals(getString(R.string.Credito))) {
            strTag = "";
        }

        if (strTag.equals(getString(R.string.PorMil))) {
            if(intPosicion > 0)
                strTag = "";
        }

        if (strTag.equals(getString(R.string.Punto))) {
            if(intPosicion > 0 && bolPunto == false) {
                bolPunto = true;
                strTag = ".";
            }else{
                strTag = "";
            }
        }

        if (strTag.equals(getString(R.string.Sumar))) {
            if(intPosicion > 0) {
                strTag = " + ";
                bolPunto = false;
            }else{
                strTag = "";
            }
        }

        if (strTag.equals(getString(R.string.Restar))) {
            if(intPosicion > 0){
                strTag = " - ";
                bolPunto = false;
            } else {
                strTag = "";
            }
        }

        if (strTag.equals(getString(R.string.Multiplicar))) {
            if(intPosicion > 0){
                strTag = " * ";
                bolPunto = false;
            } else {
                strTag = "";
            }
        }

        if (strTag.equals(getString(R.string.Dividir))) {
            if(intPosicion > 0){
                strTag = " / ";
                bolPunto = false;
            } else {
                strTag = "";
            }
        }

        intPosicion++;
        strCadena = strCadena + strTag;
        txtOperaciones.setText(strCadena);
    }

}