package com.roberto.calculadoraimc;

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
        //Obtenemos la referencia del botón.
        Button botonCalculaIMC=(Button)findViewById(R.id.boton_calcula_IMC);
        EscuchaBoton escuchaBoton=new EscuchaBoton(this);
        //Asignamos el listener al botón de calcular IMC
        botonCalculaIMC.setOnClickListener(escuchaBoton);
    }
}
