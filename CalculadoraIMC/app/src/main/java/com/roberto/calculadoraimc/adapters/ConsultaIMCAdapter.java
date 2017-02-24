package com.roberto.calculadoraimc.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roberto.calculadoraimc.bbdd.BaseDatosCredenciales;
import com.roberto.calculadoraimc.R;
import com.roberto.calculadoraimc.entidades.Seguimiento;

import java.util.List;

/**
 * Created by PCCasa on 16/02/2017.
 */

public class ConsultaIMCAdapter extends BaseAdapter {

    /**
     * Contexto de la aplicación
     */
    private static Context context;

    private List<Seguimiento> listaSeguimiento;


    /**
     * Constructor de la clase Adapter.
     * @param context
     */
    public ConsultaIMCAdapter(Context context) {
        this.context = context;
    }

    public ConsultaIMCAdapter(Context context,List<Seguimiento> listaSeguimiento){
        this.context=context;
        this.listaSeguimiento=listaSeguimiento;

    }


    @Override
    public int getCount() {
        return getNumeroFilas();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View filaInflada=null;
        Log.d(getClass().getCanonicalName(),"Entrada al método getView() del historico de IMC se añade el elemento en la posición "+position);
        if(convertView==null){
            //Creamos la activity para poder recuperar el objeto LayoutInflater
            Activity activity=(Activity)context;
            //Obtenemos una referencia de objeto LayoutInflater.
            LayoutInflater layoutInflater=activity.getLayoutInflater();
            //Inflamos la vista.
            filaInflada=layoutInflater.inflate(R.layout.fila_imc,parent,false);
        }else{
            //Reciclamos la vista.
            filaInflada=convertView;
        }

        if(listaSeguimiento==null) {
            listaSeguimiento = getRegistrosUsuario();
        }
        //Obtenemos el objeto de la lista de la posicion indicada por la variable position.
        Seguimiento seguimiento=(Seguimiento)listaSeguimiento.get(position);
        //Rellenamos la fila con los datos de los atributos del objeto Seguimiento recuperado.
        TextView textoIMC=(TextView)filaInflada.findViewById(R.id.lista_valor_IMC);
        String valorIMC=seguimiento.getImc();
        String literalIMC=seguimiento.getLiteralIMC();
        textoIMC.setText(valorIMC+"("+literalIMC+")");
        TextView textoFechaMedicion=(TextView)filaInflada.findViewById(R.id.lista_fecha_medicion);
        String fechaMedicion=seguimiento.getFechaRegistro();
        textoFechaMedicion.setText(fechaMedicion);
        //Devolvemos la fila inflada.
        return filaInflada;
    }

    /**
     * Devuelve el número de filas a pintar en el ListView de historio de IMC.
     * @return
     */
    public int getNumeroFilas(){
        int numFilas=0;
        List<Seguimiento> listaIMC=null;
        listaIMC=getRegistrosUsuario();
        if(listaIMC==null){
            numFilas=0;
        }else {
            numFilas = listaIMC.size();
        }
        return numFilas;
    }

    /**
     * Obtenemos los IMC pasados del usuario logeado en la aplicación.
     * @return
     */
    public static List<Seguimiento> getRegistrosUsuario(){
        List<Seguimiento> lista=null;
        //Obtenemos el nombre del usuario logeado.
        Activity activity=(Activity)context;
        SharedPreferences sharedPreferences=activity.getSharedPreferences("loginUsuario", Context.MODE_PRIVATE);
        String nombreUsuarioLogeado=sharedPreferences.getString("nombreUsuarioLogeado","");
        //Obtenemos el identificador del usuario.
        BaseDatosCredenciales baseDatosCredenciales=new BaseDatosCredenciales(context,"credenciales", null, 2);
        int idUsuario=baseDatosCredenciales.devuelveIdUsuario(nombreUsuarioLogeado);
        //Obtenemos
        lista=baseDatosCredenciales.devuelveRegistrosUsuario(idUsuario);
        return lista;

    }


}
