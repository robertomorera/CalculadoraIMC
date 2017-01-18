package com.roberto.calculadoraimc;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListView;

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

        //Obtenemos referencia de la actividad
        Activity activity=(Activity)context;
        //Fijamos el layout del ListView.
        activity.setContentView(R.layout.lista);
        //Obtenemos el ListView.
        ListView listView=(ListView)activity.findViewById(R.id.lista_rangos);
       ///Seteamos el adapter asociado al ListView.
        listView.setAdapter(new ConsultaRangosAdapter(context));


    }
}
