package com.roberto.calculadoraimc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

/**
 * Clase que escucha los eventos asociados a los menuItem de la aplicacion
 * Created by PCCasa on 07/02/2017.
 */

public class ListenerMenuItem implements MenuItem.OnMenuItemClickListener {

    /**
     * Atributo de representa el contexto de la actividad.
     */
    private Context context;

    /**
     * Constructor de la clase ListenerMenuItem
     * @param context
     */
    public ListenerMenuItem(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Log.d(getClass().getCanonicalName(),"Entramos en el escuchador de las vistas menu Item.");
        switch(menuItem.getItemId()){
            case R.id.opcionWebView:
                Log.d(getClass().getCanonicalName(),"El usuario seleccion el icono de consulta de rangos");
                //Creamos el intent a la actividad del webView.
                Intent intent=new Intent(context,WebRangosActivity.class);
                Activity activity=(Activity)context;
                activity.startActivity(intent);
                break;
            default:
                Log.d(getClass().getCanonicalName(),"MenuItem no definido para esta aplicación");
                break;
        }

        return true;
    }
}
