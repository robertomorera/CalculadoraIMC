package com.roberto.calculadoraimc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.roberto.calculadoraimc.entidades.CalculadoraIMC;
import com.roberto.calculadoraimc.excepciones.IMCException;
import com.roberto.calculadoraimc.listeners.ListenerMenuItem;
import com.roberto.calculadoraimc.R;

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
        float estaturaFormateada=0;
        float pesoFormateado=0;
        //Obtenemos la estatura y el peso almacenados en el Intent.
        String peso=intent.getStringExtra("peso");
        Log.d(getClass().getCanonicalName(),"Peso recuperado: "+peso);
        String estatura=intent.getStringExtra("estatura");
        Log.d(getClass().getCanonicalName(),"Estatura recuperada: "+estatura);

        //Transformamos a float los Stings.
        try {
            estaturaFormateada = Float.parseFloat(estatura);
            pesoFormateado = Float.parseFloat(peso);
        }catch(NumberFormatException e){
            Log.d(getClass().getCanonicalName(),e.getMessage());
        }
        //Creamos el objeto CalculadoraIMC
        CalculadoraIMC calculadoraIMC= new CalculadoraIMC(estaturaFormateada,pesoFormateado);

        //Calcualamos el IMC
        float IMC=0;
        try {
            IMC = calculadoraIMC.calcularIMC();
        }catch(IMCException e){
            Log.d(getClass().getCanonicalName(),e.getMessage());
        }
        //Valoramos el IMC para obtener el rango del usuario
        String textoRango=analizaIMC(IMC);

        //Obtenemos el editText.
        EditText caja_IMC=(EditText)findViewById(R.id.caja_IMC);

        //Insertamos el texto en el EditText.
        String textoIMC=Float.toString(IMC);
        caja_IMC.setText(textoIMC);

        //Obtenemos el EditText del rango.
        EditText caja_rango=(EditText)findViewById(R.id.caja_Rango);

        //Insertamos el rango del usuario.
        caja_rango.setText(textoRango);

        //Asociamos un color al rango obtenido.
        asociaColorRango(IMC,caja_rango);
    }

    /**
     * Construye el menu de opciones de la barra de la activity
     * se añade la opción de registrar los resultados  de IMC en la base de datos.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //Inflamos con el layout de registro de IMC.
        getMenuInflater().inflate(R.menu.menu_resultado_imc,menu);
        //Obtenemos el menu item asociado al menu
        MenuItem menuItem=menu.getItem(0);
        //Creamos el listener
        ListenerMenuItem listenerMenuItem=new ListenerMenuItem(this);
        //Asociamos el listener al menuItem.
        menuItem.setOnMenuItemClickListener(listenerMenuItem);
        return true;

    }

    @Override
    public void onBackPressed() {
        //Creamos una sharedPreferences del usuario;
        SharedPreferences sharedPreferences=getSharedPreferences("loginUsuario", Context.MODE_PRIVATE);
        sharedPreferences.getString("nombreUsuarioLogeado","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombreUsuarioLogeado",getIntent().getStringExtra("nombreUsuarioLogeado"));
        editor.commit();
        super.onBackPressed();
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

    public void asociaColorRango(float IMC,EditText editText){
        if(IMC<16) {
            editText.setTextColor(Color.RED);
        }else if((IMC>=16)&&(IMC<18)){
            editText.setTextColor(Color.YELLOW);
        }else if((IMC>=18)&&(IMC<25)){
            editText.setTextColor(Color.GREEN);
        }else if((IMC>=25)&&(IMC<31)){
            editText.setTextColor(Color.YELLOW);
        }else if(IMC>=31){
            editText.setTextColor(Color.RED);
        }
    }


}
