package com.roberto.calculadoraimc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by PCCasa on 07/02/2017.
 * En esta clase se encuentran definidos todos los alertDialog
 * de la aplicacion
 */


public class AlertDialogApp {

    /**
     * Atributo que representa el contexto de la aplicacion.
     */
    private Context context;

    /**
     * Constructor de la clase AlertDialogApp
     *
     * @param context
     */
    public AlertDialogApp(Context context) {
        this.context = context;
    }

    /**
     * Método que construye el alertDialog para preguntar
     * al usuario si desea salir de la aplicacion
     *
     *
     */
    public void getDialogSalirApp() {
        //Creamos la referencia del objeto AlertDialog.
        final AlertDialog dialogSalir = new AlertDialog.Builder(context).create();
        //Seteamos el titulo del alertDialog.
        dialogSalir.setTitle(R.string.titulo_dialog_salir);
        //Seteamos el mensaje del alertDialog.
        String mensajeDialogSalir=context.getResources().getText(R.string.mensaje_dialog_salir).toString();
        dialogSalir.setMessage(mensajeDialogSalir);
        //Creamos el boton afirmativo
        CharSequence textoBotonPositivo=context.getResources().getText(R.string.literal_Si);
        dialogSalir.setButton(AlertDialog.BUTTON_POSITIVE, textoBotonPositivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(getClass().getCanonicalName(),"El usuario ha pulsado el botón Si y saldrá de la aplicacion");
                //Cuando pulsamos el boton "Si" se cierra la aplicación
                Activity activity=(Activity)context;
                activity.finish();
            }
        });

        //Creamos el botón negativo.
        CharSequence textoBotonNegativo=context.getResources().getText(R.string.literal_No);
        dialogSalir.setButton(AlertDialog.BUTTON_NEGATIVE, textoBotonNegativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Cuando pulsamos el boton "No" se cierra el alertDialog ya que el usuario cancela la acción.
                Log.d(getClass().getCanonicalName(),"El usuario ha pulsado el botón No y no saldrá de la aplicacion");
                dialogInterface.cancel();
            }
        });

        //Mostramos el alertDialog.
        dialogSalir.show();
    }

    public void getDialogSalirMenuIMC(){
        //Creamos la referencia del objeto AlertDialog.
        final AlertDialog dialogSalirIMC = new AlertDialog.Builder(context).create();
        //Seteamos el titulo del alertDialog.
        dialogSalirIMC.setTitle(R.string.titulo_dialog_salirIMC);
        //Seteamos el mensaje del alertDialog.
        String mensajeDialogSalirIMC=context.getResources().getText(R.string.mensaje_dialog_salirIMC).toString();
        dialogSalirIMC.setMessage(mensajeDialogSalirIMC);
        //Creamos el boton afirmativo
        CharSequence textoBotonPositivo=context.getResources().getText(R.string.literal_Si);
        dialogSalirIMC.setButton(AlertDialog.BUTTON_POSITIVE, textoBotonPositivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(getClass().getCanonicalName(),"El usuario ha pulsado el botón Si y cerrará la sesión de su usuario");
                //Cuando pulsamos el boton "Si" volvemos a la pantalla del login
                //Creamos una sharedPreferences del usuario;
                //Al volver atras quitamos el logeo del usuario.
                SharedPreferences sharedPreferences=context.getSharedPreferences("loginUsuario", Context.MODE_PRIVATE);
                //Creamos una shared preferences para indicar que el usuario ya se ha logeado.
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("usuarioLogeado",false);
                editor.commit();
                Activity activity=(Activity)context;
                activity.finish();

            }
        });
        //Creamos el botón negativo.
        CharSequence textoBotonNegativo=context.getResources().getText(R.string.literal_No);
        dialogSalirIMC.setButton(AlertDialog.BUTTON_NEGATIVE, textoBotonNegativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Cuando pulsamos el boton "No" se cierra el alertDialog ya que el usuario cancela la acción.
                Log.d(getClass().getCanonicalName(),"El usuario ha pulsado el botón No y no cerrará su sesión");
                dialogInterface.cancel();
            }
        });

        //Mostramos el alertDialog.
        dialogSalirIMC.show();
    }

}
