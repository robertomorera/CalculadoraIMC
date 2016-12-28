package com.roberto.calculadoraimc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by PCCasa on 21/12/2016.
 */

public class EscuchaBoton implements View.OnClickListener {


/* Contexto de la app.
 */
    private Context context;

    /*
    Constructor de la clase.
     */
    public EscuchaBoton(Context context) {
        this.context = context;
    }

    /*
     Evento asociado a la accion del botón boton_calcula_IMC.
     Recupera el peso y estatura insertados por el usuario
     y los pasa a la siguiente actividad en el Intent.
     */
    @Override
    public void onClick(View v) {

        Log.d(getClass().getCanonicalName(),Constantes.ACCION_BOTONIMC);
        //Obtenemos referencia de la actividad
        Activity activity=(Activity)context;
        //Obtenemos la referencia de las cajas de texto de peso y estatura.
        EditText cajaEstatura=(EditText)activity.findViewById(R.id.caja_estatura);
        EditText cajaPeso=(EditText)activity.findViewById(R.id.caja_peso);
        //Obtenemos los valores de peso y estatura introducidos por el usuario.
        String estatura=cajaEstatura.getText().toString();
        Log.d(getClass().getCanonicalName(),Constantes.LOG_ESTATURA_INTRODUCIDA+estatura);
        String peso=cajaPeso.getText().toString();
        Log.d(getClass().getCanonicalName(),Constantes.LOG_PESO_INTRODUCIDO+peso);
        //Creamos el objeto Intent para indicar la acción de inicio de la actividad de calculo de IMC (ResultadoIMCActivity).
        Intent intent=new Intent(context,ResultadoIMCActivity.class);
        //Le pasamos al intent los valores de estatura y peso obtenidos.
        intent.putExtra("peso",peso);
        intent.putExtra("estatura",estatura);
        //Lanzamos la acción del Intent
        Log.d(getClass().getCanonicalName(),Constantes.LOG_INICIO_INTENT);
        activity.startActivity(intent);
    }
}
