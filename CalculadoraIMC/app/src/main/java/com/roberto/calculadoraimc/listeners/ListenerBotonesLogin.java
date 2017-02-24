package com.roberto.calculadoraimc.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.roberto.calculadoraimc.utilidades.Codificar;
import com.roberto.calculadoraimc.R;
import com.roberto.calculadoraimc.activities.MainActivity;
import com.roberto.calculadoraimc.bbdd.BaseDatosCredenciales;
import com.roberto.calculadoraimc.entidades.Usuario;

/**
 * Created by PCCasa on 01/02/2017.
 */

public class ListenerBotonesLogin implements View.OnClickListener {

    /**
     * Atributo con el contexto de la aplicacion
     */
    private Context context;

    /**
     * Constructor de la clase
     * @param context
     */
    public ListenerBotonesLogin(Context context) {
        this.context = context;
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.boton_registro:
                funcionRegistrarUsuario();
                break;
            case R.id.boton_login:
                funcionLoginUsuario();
            default:
                Log.d(getClass().getCanonicalName(),"No es ningún botón definido en esta actividad");

        }
    }


    /**
     * Método que implementa la lógica de registros de usuarios
     */
    private void funcionRegistrarUsuario() {

        //Lógica de negocio del botón registro.
        Log.d(getClass().getCanonicalName(), "El usuario ha pulsado el botón de registro de la app");
        //Obtenemos el nombre de usuario de la caja de texto.
        Activity activity = (Activity) context;
        EditText editTextNombreUsuario = (EditText) activity.findViewById(R.id.caja_loginUsuario);
        //Recuperamos el texto.
        String nombreUsuario = editTextNombreUsuario.getText().toString();
        //Comprobamos si el usuario ya está registrado en la base de datos.
        BaseDatosCredenciales baseDatosCredenciales = new BaseDatosCredenciales(context,"credenciales", null, 2);
        //Comprobamos si el campo viene informado.
        if (nombreUsuario.equals("")) {
            Toast.makeText(context,R.string.usuario_no_informado, Toast.LENGTH_SHORT).show();
        } else {

            if (baseDatosCredenciales.existeNombreUsuario(nombreUsuario)) {
                //Si el nombre de usuario ya está registrado en la base de datos no se registrado en BD
                //Creamos el Toast que indicará al usuario la información del rango por pantalla.
                Toast.makeText(context,R.string.usuario__existe, Toast.LENGTH_SHORT).show();
            } else {
                //Si el usuario no existe en base de datos obtenemos el valor de la contraseña.
                EditText editTextContrasenaUsuario = (EditText) activity.findViewById(R.id.caja_loginContrasena);
                //Recuperamos la contraseña.
                String contrasena = editTextContrasenaUsuario.getText().toString();
                //Verificamos si el campo contraseña viene informado.
                if (contrasena.equals("")) {
                    Toast.makeText(context,R.string.contrasena_no_informada, Toast.LENGTH_SHORT).show();
                } else {
                    //Codificamos la contraseña para guardar en la BBDD.
                    contrasena = Codificar.codifica(contrasena);
                    //Creamos el objeto Usuaario.
                    Usuario usuario = new Usuario(nombreUsuario, contrasena);
                    //Registramos el usuario en la BD.
                    baseDatosCredenciales.insertarUsuario(usuario);
                    Log.d(getClass().getCanonicalName(), "El usuario:" + nombreUsuario + "se ha registrado correctamente");
                    Toast.makeText(context,R.string.registro__correcto, Toast.LENGTH_SHORT).show();
                }
            }

        }

    }


    /**
     * Método que implementa la lógica de acceso de usuarios a la aplicacion
     */
    private void funcionLoginUsuario(){
        //Lógica de negocio del botón registro.
        Log.d(getClass().getCanonicalName(), "El usuario ha pulsado el botón de login de la app");
        //Obtenemos los valores de las credenciales del usuario.
        Activity activity=(Activity)context;
        //Obtenemos el nombre de usuario de la caja de texto.
        EditText editTextNombreUsuario = (EditText) activity.findViewById(R.id.caja_loginUsuario);
        //Recuperamos el texto.
        String nombreUsuario = editTextNombreUsuario.getText().toString();
        //Creamos una sharedPreferences del usuario;
        SharedPreferences sharedPreferences=context.getSharedPreferences("loginUsuario",Context.MODE_PRIVATE);
        sharedPreferences.getBoolean("usuarioLogeado",false);
        //Comprobamos si el campo viene informado.
        if (nombreUsuario.equals("")) {
            Toast.makeText(context,R.string.usuario_no_informado, Toast.LENGTH_SHORT).show();
        }else {
            //Si el campo del usuario viene informado.
            //Comprobamos si existe en la base de datos.
            BaseDatosCredenciales baseDatosCredenciales = new BaseDatosCredenciales(context, "credenciales", null, 2);
            if (!baseDatosCredenciales.existeNombreUsuario(nombreUsuario)) {
                Toast.makeText(context,R.string.usuario_no_existe, Toast.LENGTH_SHORT).show();
            } else {
                EditText editTextContrasenaUsuario = (EditText) activity.findViewById(R.id.caja_loginContrasena);
                //Recuperamos la contraseña.
                String contrasena = editTextContrasenaUsuario.getText().toString();
                //Verificamos si el campo contraseña viene informado.
                if (contrasena.equals("")) {
                    Toast.makeText(context,R.string.contrasena_no_informada, Toast.LENGTH_SHORT).show();
                } else {
                    //Creamos el usario con el que consultaremos las credenciales en base de datos.
                    Usuario usuario = new Usuario(nombreUsuario, contrasena);
                    if (validarCredenciales(usuario, baseDatosCredenciales)) {
                        //Damos acceso a la actividad de calculo de IMC.
                        Log.d(getClass().getCanonicalName(), "Acceso correcto a la app");
                        //Creamos una shared preferences para indicar que el usuario ya se ha logeado.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("usuarioLogeado", true);
                        editor.putString("nombreUsuarioLogeado",nombreUsuario);
                        editor.commit();
                        Intent intent = new Intent(context, MainActivity.class);
                        //Guardamos en el intent el usuario logeado.
                        intent.putExtra("nombreUsuarioLogeado",nombreUsuario);
                        activity.startActivity(intent);


                    } else {
                        Toast.makeText(context,R.string.password__incorrecta, Toast.LENGTH_SHORT).show();
                        //Creamos una sharedPreferences por usuario para controlar el número de veces que se le permite logearse.
                        SharedPreferences sharedPreferencesVeces = context.getSharedPreferences("nombreUsuario", Context.MODE_PRIVATE);
                        int nIncorrectas = sharedPreferencesVeces.getInt("nIncorrectas", -1);
                        if (nIncorrectas == -1) {
                            nIncorrectas = 1;
                        } else {
                            //Se incrementa el número de veces de logins incorrectos
                            nIncorrectas++;

                        }

                        //Se actualiza el valor en la SharedPreferences.
                        SharedPreferences.Editor editor = sharedPreferencesVeces.edit();
                        SharedPreferences.Editor editorLogin = sharedPreferences.edit();
                        editor.putInt("nIncorrectas", nIncorrectas);
                        editor.commit();
                        editorLogin.putBoolean("usuarioLogeado", false);
                        editorLogin.commit();
                        //Si llega al tercer intento se le expulsa al usuario de la aplicación.
                        if (nIncorrectas == 3) {
                            editor.putInt("nIncorrectas", -1);
                            editor.commit();
                            editorLogin.putBoolean("usuarioLogeado", false);
                            editorLogin.commit();
                            Toast.makeText(context,R.string.expulsion_app, Toast.LENGTH_SHORT).show();
                            activity.finishAffinity();
                        }

                    }
                }

            }
        }
    }

    /**
     * Valida el nombre de usuario
     * @param usuario
     * @return
     */
    private boolean validarCredenciales(Usuario usuario, BaseDatosCredenciales baseDatosCredenciales){
        boolean credencialesValidas=false;

        //Obtenemos el nombre del usuario guardado en BD.
        String nombreUsuario=baseDatosCredenciales.consultaNombreUsuario(usuario);
        Log.d(getClass().getCanonicalName(),"El valor del nombre obtenido en base de datos es: "+nombreUsuario);
        //Obtenemos el valor de la contraseña del usuario.
        String contrasena=baseDatosCredenciales.consultaContraseñaUsuario(usuario);
        Log.d(getClass().getCanonicalName(),"El valor de la contraseña obtenida en base de datos es: "+contrasena);
        //Realizamos la validacion de las credenciales.

        if((nombreUsuario.equals(usuario.getNombre())&&(contrasena.equals(usuario.getContrasena())))){
            Log.d(getClass().getCanonicalName(),"Las credenciales son correctas");
            credencialesValidas=true;
        }
        return credencialesValidas;

    }


}
