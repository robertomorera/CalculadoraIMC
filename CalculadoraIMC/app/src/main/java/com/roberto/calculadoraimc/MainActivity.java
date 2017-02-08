package com.roberto.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    /**
     * Método que recoge la acción del usuario de pulsar la tecla atras.
     */
    @Override
    public void onBackPressed() {
        Log.d(getClass().getCanonicalName(),"El usuario ha pulsado el boton atras se le pregunta si quiere cerrar la sesión");
        //Se muestra el alertDialog para preguntar si queremos cerrar la sesion.
        AlertDialogApp alertDialogApp= new AlertDialogApp(this);
        alertDialogApp.getDialogSalirMenuIMC();

    }

    /**
     * Método que crea el menu de opciones de la actividad del menu principal
     * de la aplicación IMC.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Creamos la referencia del menu a partir del layout definido.
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        //Obtenemos el MenuItem asociado a el.
        MenuItem itemRangos=menu.getItem(0);
        //Creamos el listener
        ListenerMenuItem listenerMenuItem=new ListenerMenuItem(this);
        //Asociamos el listener al menuItem.
        itemRangos.setOnMenuItemClickListener(listenerMenuItem);
        return true;
        }

    }

