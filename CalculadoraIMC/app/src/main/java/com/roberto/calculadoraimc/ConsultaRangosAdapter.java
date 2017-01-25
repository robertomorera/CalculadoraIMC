package com.roberto.calculadoraimc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PCCasa on 18/01/2017.
 */

public class ConsultaRangosAdapter extends BaseAdapter {
    /**
     * Atributo context, es el contexto de la app.
     */
    private Context context;

    //Galeria de imagenes del ListView.

    private Integer[] galeriaImagenes= {R.drawable.desnutrido,R.drawable.bajopeso,R.drawable.normalpeso,R.drawable.sobrepeso,R.drawable.obeso};

    //Array con los literales de los rangos.
    private Integer[] listaRangos={R.string.literal_desnutrido,R.string.literal_bajopeso,R.string.literal_normal,R.string.literal_sobrepeso,R.string.literal_obeso};

    //Array de descripciones de listados.
    private Integer[] listaDescripcionRangos={R.string.Desnutrido,R.string.BajoPeso,R.string.Normal,R.string.SobrePeso,R.string.Obeso};
    /**
     * Constructor de la clase ConsultaRangosAdapter
     * @param context Contexto de la aplicacion
     */
    public ConsultaRangosAdapter(Context context) {
        this.context = context;
    }

    /**
     * Método que devuelve el número de elementos que tendrá el ListView.
     * @return int Devuelve el número de filas que tendrá el ListView
     */
    @Override
    public int getCount() {
        Log.d(getClass().getCanonicalName(),"Número de elementos del ListView "+galeriaImagenes.length);
        return galeriaImagenes.length;
    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Método que añade un nuevo elemento al ListView.
     * @param position Posición del elemento a añadir.
     * @param convertView
     * @param parent
     * @return View Devuelve el elemento a añadi al ListView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View filaInflada=null;
        Log.d(getClass().getCanonicalName(),"Entrada al método getView() se añade el elemento en la posición "+position);
        //Creamos la activity para poder recuperar el objeto LayoutInflater
        if(convertView==null) {
            Activity activity = (Activity) context;
            //Obtenemos un objeto LayOutInflater.
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            //Inflamos la vista.
            filaInflada = layoutInflater.inflate(R.layout.fila_rango, parent, false);
        }else{
            filaInflada=convertView;
        }
            //Obtenemos los elementos que forman la fila para configurarlos.
        ImageView imagenRangoView=(ImageView)filaInflada.findViewById(R.id.imagenRango);
        //Insertamos la imagen correspondiente.
        imagenRangoView.setImageResource(galeriaImagenes[position]);
        //Obtenemos la descripción del texto
        TextView textoRangoView=(TextView)filaInflada.findViewById(R.id.textoRango);
        //Fijamos el literal del rango en el textView
        textoRangoView.setText(listaRangos[position]);
        //Asociamos el texto del rango a cada elemento;
        filaInflada.setTag(listaDescripcionRangos[position]);
        Log.d(getClass().getCanonicalName(),"Asociamos a la fila inflada el rango: "+listaDescripcionRangos[position]);
        //Devolvemos la fila inflada al ListView.
        return filaInflada;

    }
}
