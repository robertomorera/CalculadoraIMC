package com.roberto.calculadoraimc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class ResultadoIMCActivity extends AppCompatActivity {

    @Override
    /*
     Se calcula el IMC y se prepara la vista para presentar
     los resultados de IMC obtenidos.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_imc);
        //Obtenemos el Intent padre.
        Intent intent=getIntent();

        //Obtenemos la estatura y el peso almacenados en el Intent.
        String peso=intent.getStringExtra("peso");
        Log.d(getClass().getCanonicalName(),"Peso recuperado: "+peso);
        String estatura=intent.getStringExtra("estatura");
        Log.d(getClass().getCanonicalName(),"Estatura recuperada: "+estatura);

        //Transformamos a float los Stings.
        float estaturaFormateada=Float.parseFloat(estatura);
        float pesoFormateado=Float.parseFloat(peso);

        //Creamos el objeto CalculadoraIMC
        CalculadoraIMC calculadoraIMC= new CalculadoraIMC(estaturaFormateada,pesoFormateado);

        //Calcualamos el IMC
        float IMC=0;
        try {
            IMC = calculadoraIMC.calcularIMC();
        }catch(IMCException e){
            Log.d(getClass().getCanonicalName(),e.getMessage());
        }
        //Valoramos el IMC
        String textoIMC=analizaIMC(IMC);

        //Obtenemos el editText.
        EditText caja_IMC=(EditText)findViewById(R.id.caja_IMC);

        //Insertamos el texto en el EditText.
        caja_IMC.setText(textoIMC);

    }


    /*
     Método encargado de asociar el IMC calculado con su valoracion
     @float IMC calculado.
     @return String IMCTexto: mensaje del IMC.
     */

    public String analizaIMC(float IMC){
        String IMCTexto=null;
        //Valoramos el IMC.
        if(IMC<16) {
            IMCTexto = getResources().getString(R.string.Desnutrido);
        }else if((IMC>=16)&&(IMC<18)){
            IMCTexto = getResources().getString(R.string.BajoPeso);
        }else if((IMC>=18)&&(IMC<25)){
            IMCTexto = getResources().getString(R.string.Normal);
        }else if((IMC>=25)&&(IMC<31)){
            IMCTexto = getResources().getString(R.string.SobrePeso);
        }else if(IMC>=31){
            IMCTexto=getResources().getString(R.string.Obeso);
        }

        Log.d(getClass().getCanonicalName(),"La valoracion del IMC es: "+IMCTexto);
        return IMCTexto;
    }


}
