package com.roberto.calculadoraimc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /*
     Prepara la vista para mostrar el formulario
     donde el usuario debe introducir peso y altura.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenemos la referencia del botón de cálculo de IMC.
        Button botonCalculaIMC=(Button)findViewById(R.id.boton_calcula_IMC);
        EscuchaBoton escuchaBoton=new EscuchaBoton(this);
        //Asignamos el listener al botón de calcular IMC
        botonCalculaIMC.setOnClickListener(escuchaBoton);
        //Obtenemos la referencia del bóton de consultar rangos.
        Button botonConsultaRangos=(Button)findViewById(R.id.boton_consulta_rangos);
        //Creamos el listener asociado al botón.
        ListenerRangosBoton listenerRangosBoton=new ListenerRangosBoton(this);
        //Asignamos el Listener al botón de consulta de rangos.
        botonConsultaRangos.setOnClickListener(listenerRangosBoton);
    }

    @Override
    public void onBackPressed() {
        //Creamos una sharedPreferences del usuario;
        //Al volver atras quitamos el logeo del usuario.
        SharedPreferences sharedPreferences=getSharedPreferences("loginUsuario", Context.MODE_PRIVATE);
        //Creamos una shared preferences para indicar que el usuario ya se ha logeado.
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("usuarioLogeado",false);
        editor.commit();
        super.onBackPressed();
    }
}
