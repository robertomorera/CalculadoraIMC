package com.roberto.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences("loginUsuario", Context.MODE_PRIVATE);
        boolean estaLogeado=sharedPreferences.getBoolean("usuarioLogeado",false);
        if(estaLogeado){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
        //Obtenemos la referencia del boton de registro;
        Button botonRegistro=(Button)findViewById(R.id.boton_registro);
        //Creamos el listener el boton.
        ListenerBotonesLogin listenerBotonRegistro=new ListenerBotonesLogin(this);
        //Asociamos el listener al boton.
        botonRegistro.setOnClickListener(listenerBotonRegistro);
        //Obtenemos el botón de login a la app.
        Button botonAcceso=(Button)findViewById(R.id.boton_login);
        botonAcceso.setOnClickListener(listenerBotonRegistro);
    }
}
