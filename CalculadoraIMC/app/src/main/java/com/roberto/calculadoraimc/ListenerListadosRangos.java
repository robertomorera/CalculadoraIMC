package com.roberto.calculadoraimc;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by PCCasa on 22/01/2017.
 */

public class ListenerListadosRangos implements AdapterView.OnItemClickListener {


    private Context context;

    /**
     * Constructor de la clase ListenerListadosRangos
     * @param context
     */
    public ListenerListadosRangos(Context context) {
        this.context = context;
    }

    /**
     * Método que implementa la funcionalidad de los distitos elementos inflados
     * del ListView.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Obtenemos el valor almacenado en la vista en su TAG.
        Integer valorRango=(Integer)view.getTag();
        Log.d(getClass().getCanonicalName(),"El valor obtenido del TAG es: "+valorRango);
        //Creamos el Toast que indicará al usuario la información del rango por pantalla.
        Toast.makeText(context,valorRango,Toast.LENGTH_SHORT).show();
    }
}
