package com.chase.ejerciciospropuestos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String strResultado;
        EditText txtOperaciones = findViewById(R.id.txtOperaciones);
        ///txtOperaciones.setText(strTag + "----" + getString(R.string.Sumar));
        ///Toast.makeText(this,"llamada", Toast.LENGTH_SHORT);

        if (strTag.equals(getString(R.string.Ejecutar))) {
            if(intPosicion > 0) {
                // añadimos un salto de linea para mostrar el resultado
                strResultado = resultado();
                txtOperaciones.setText(strCadena + " =\n"  + strResultado);
                //la nueva cadena sera el resultado por si quieren calcular algo despues
                strCadena = strResultado;
                bolPunto = false;
            }
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
                strTag = "";  // si ya uso el punto ya no lo puede volver a hacer
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

        if (!strTag.equals(getString(R.string.Ejecutar))) {
            intPosicion++;
            strCadena = strCadena + strTag;
            txtOperaciones.setText(strCadena);
        }
    }

    private String resultado() {
        String strResultado = new String();
        String strCadenaAux = new String(strCadena);
        String strOperacion =new String(),strNumero1 =new String(),strNumero2 =new String();
        Pattern patPatron = Pattern.compile("(\\d+\\.?\\d*)\\s([\\*]){1}\\s(\\d+\\.?\\d*)");
        Matcher matMatcher = patPatron.matcher(strCadenaAux);
        //primero procesamos las multiplicaciones y las divisiones
        /*while(matMatcher.find()){
            strNumero1 = matMatcher.group(1).toString();
            strOperacion = matMatcher.group(2).toString();
            strNumero2 = matMatcher.group(3).toString();

            if(strOperacion.trim().equals("*")){
                strResultado = String.valueOf(Float.parseFloat(strNumero1)*Float.parseFloat(strNumero2));
            }else{
                strResultado = String.valueOf(Float.parseFloat(strNumero1)/Float.parseFloat(strNumero2));
            }
            //reemplazamos el resultado en la cadena original y seguimos operando
            strCadenaAux = strCadenaAux.replaceFirst(strNumero1 + strOperacion + strNumero2, strResultado);
        }*/

        //luego procesamos suma y restas
        patPatron = Pattern.compile("(\\d+\\.?\\d*)\\s([\\+]){1}\\s(\\d+\\.?\\d*)");
        matMatcher = patPatron.matcher(strCadenaAux);
        //primero procesamos las multiplicaciones y las divisiones
        while(matMatcher.find()==true){
            strNumero1 = matMatcher.group(1).toString();
            strOperacion = matMatcher.group(2).toString();
            strNumero2 = matMatcher.group(3).toString();

            if("+".equals(strOperacion.trim())){
                strResultado = String.valueOf(Float.parseFloat(strNumero1)+Float.parseFloat(strNumero2));
            }else{
                strResultado = String.valueOf(Float.parseFloat(strNumero1)-Float.parseFloat(strNumero2));
            }
            //reemplazamos el resultado en la cadena original y seguimos operando
            strCadenaAux = strCadenaAux.replace(strNumero1 + " " + strOperacion + " " + strNumero2, strResultado);
        }

        return strCadenaAux;
    }
}