package com.roberto.calculadoraimc;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;


/**
 * Created by PCCasa on 18/01/2017.
 */

public class ListenerRangosBoton implements View.OnClickListener {

    /**
     * Atributo contexto de la app.
     */

    private Context context;

    /**
     * Constructor de la clase ListenerRangosBoton
     * @param context Contexto de la app
     */

    public ListenerRangosBoton(Context context) {
        this.context = context;
    }

    /**
     *  Evento asociado al boton de consulta de rangos de IMC.
     *  Prepara el despliegue del List View.
     * @param v
     */

    @Override
    public void onClick(View v) {

        //Creamos el Intent para pasar a la actividad de listar rangos.
        Intent intent=new Intent(context,ListadoRangosActivity.class);
        Log.d(getClass().getCanonicalName(),"Se inicia la actividad del Intent de listado de rangos");
        //Lanzamos la actividad configurada en el Intent.
        context.startActivity(intent);

    }
}
