package com.roberto.calculadoraimc.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.roberto.calculadoraimc.R;
import com.roberto.calculadoraimc.activities.ConsultaIMCActivity;
import com.roberto.calculadoraimc.activities.WebRangosActivity;
import com.roberto.calculadoraimc.bbdd.BaseDatosCredenciales;
import com.roberto.calculadoraimc.entidades.Seguimiento;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        Activity activity=null;
        Intent intent=null;
        switch(menuItem.getItemId()){
            case R.id.opcionWebView:
                Log.d(getClass().getCanonicalName(),"El usuario seleccion el icono de consulta de rangos");
                //Creamos el intent a la actividad del webView.
                intent=new Intent(context,WebRangosActivity.class);
                activity=(Activity)context;
                activity.startActivity(intent);
                break;
            case R.id.opcionRegistroIMC:
                Log.d(getClass().getCanonicalName(),"El usuario ha seleccionado la opción de registro de IMC");
                activity=(Activity)context;
                //Recuperamos el valor del IMC calculado.
                EditText cajaIMC=(EditText)activity.findViewById(R.id.caja_IMC);
                String valorIMC=cajaIMC.getText().toString();
                Log.d(getClass().getCanonicalName(),"Se ha recuperado el valor de IMC: "+valorIMC);
                //Recuperamos el valor del literal asociado al IMC calculado.
                EditText cajaLiteralIMC=(EditText)activity.findViewById(R.id.caja_Rango);
                String literalIMC=cajaLiteralIMC.getText().toString();
                Log.d(getClass().getCanonicalName(),"Se ha recuperado el literal de IMC: "+literalIMC);
                //Obtenemos la fecha de registro (fecha actual).
                String fechaRegistro= new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                //Recuperamos el usuario Logeado.
                String nombreUsuarioLogeado=activity.getIntent().getStringExtra("nombreUsuarioLogeado");
                //Si el nombre es nulo ya que el usuario está ya logeado lo recuperamos de la SharedPreferences.
                if(nombreUsuarioLogeado==null){
                    SharedPreferences sharedPreferences=activity.getSharedPreferences("loginUsuario", Context.MODE_PRIVATE);
                    nombreUsuarioLogeado=sharedPreferences.getString("nombreUsuarioLogeado","");
                }
                //Creamos la referencia a la base de datos de la app.
                BaseDatosCredenciales baseDatosCredenciales = new BaseDatosCredenciales(context,"credenciales", null, 2);
                //Obtenemos el identificador del usuario logeado.
                int idUsuarioLogeado=baseDatosCredenciales.devuelveIdUsuario(nombreUsuarioLogeado);
                Log.d(getClass().getCanonicalName(),"Se ha recuperado el identificador de usuario: "+idUsuarioLogeado);
                //Creamos el objeto a registrar en la base de datos.
                Seguimiento seguimiento=new Seguimiento(valorIMC,literalIMC,fechaRegistro,idUsuarioLogeado);
                //Registramos el calculo de IMC del usuario logeado en la base de datos.
                baseDatosCredenciales.insertarIMCCalculado(seguimiento);
                Toast.makeText(context,R.string.registro_IMC_correcto, Toast.LENGTH_SHORT).show();
                break;
            case R.id.opcionListaRangos:
                Log.d(getClass().getCanonicalName(),"El usuario ha seleccionado la opción de consulta de IMCs antiguos");
                //Creamos el intent a la actividad.
                activity=(Activity)context;
                intent=new Intent(context,ConsultaIMCActivity.class);
                activity.startActivity(intent);
            default:
                Log.d(getClass().getCanonicalName(),"MenuItem no definido para esta aplicación");
                break;
        }

        return true;
    }
}
