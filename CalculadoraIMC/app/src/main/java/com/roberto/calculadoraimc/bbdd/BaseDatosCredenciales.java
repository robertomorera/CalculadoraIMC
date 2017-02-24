package com.roberto.calculadoraimc.bbdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.roberto.calculadoraimc.utilidades.Codificar;
import com.roberto.calculadoraimc.entidades.Seguimiento;
import com.roberto.calculadoraimc.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by PCCasa on 01/02/2017.
 */

public class BaseDatosCredenciales extends SQLiteOpenHelper {


    private static final String SQL_CREAR_TABLA_USUARIOS="CREATE TABLE USUARIOS (id INTEGER PRIMARY KEY AUTOINCREMENT , nombre TEXT, contrasena TEXT)";

    private static final String SQL_CREAR_TABLA_SEGUIMIENTO="CREATE TABLE SEGUIMIENTO (id INTEGER PRIMARY KEY AUTOINCREMENT , imc TEXT, textoimc TEXT, fecharegistro TEXT, idusuario INTEGER, FOREIGN KEY (idusuario) REFERENCES USUARIOS(id))";


    /**
     * Constructor de la base de datos de usuarios/contraseñas de la app.
     * @param context
     * @param nombre
     * @param cursor
     * @param version
     */

    public BaseDatosCredenciales(Context context, String nombre, SQLiteDatabase.CursorFactory cursor, int version){
        super(context,nombre,cursor,version);
    }


    /**
     * Método que crea la tabla USUARIOS
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
     //Ejecutamos la sentencia para crear la tabla de usuarios.
      db.execSQL(SQL_CREAR_TABLA_USUARIOS);
    //Ejecutamos la sentencia para crear la tabla de seguimiento de IMC.
      db.execSQL(SQL_CREAR_TABLA_SEGUIMIENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Método que registra al usuario en la tabla USUARIOS.
     * @param usuario
     */
    public void insertarUsuario(Usuario usuario){
        //Inicializamos la BBDD en modo L/E.
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //
        sqLiteDatabase.execSQL("INSERT INTO USUARIOS (nombre,contrasena) VALUES('"+usuario.getNombre()+"' , '"+usuario.getContrasena()+"')");
        Log.d(getClass().getCanonicalName(),"Se ha insertado las credenciales del usuario:["+usuario.getNombre()+","+usuario.getContrasena()+"]");
        //Cerramos la conexión de la base de datos.
        sqLiteDatabase.close();
    }

    /**
     * Método que registra el IMC calculado del usuario logeado en la tabla de SEGUIMIENTO DE IMC.
     * @param seguimiento
     */
    public void  insertarIMCCalculado(Seguimiento seguimiento){
        //Inicializamos la base de datosen modo L/E.
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO SEGUIMIENTO (imc,textoimc,fecharegistro,idusuario) VALUES('"+seguimiento.getImc()+"' , '"+seguimiento.getLiteralIMC()+"' , '"+seguimiento.getFechaRegistro()+"' ,"+
        seguimiento.getIdUsuario()+")");
        Log.d(getClass().getCanonicalName(),"Se ha registrado el IMC:"+seguimiento.getImc());
        //Cerramos la base de datos.
        sqLiteDatabase.close();

    }


    /**
     * Método que busca en la base de datos si el usuario existe en base de datos
     * @param usuario
     * @return
     */
    public boolean existeNombreUsuario(String nombreUsuario){
        boolean existeUsuario=false;
        String consulta="SELECT nombre FROM USUARIOS WHERE nombre='"+nombreUsuario+"'";
        //Inicializamos la base de datos en modo consulta.
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(consulta,null);
        if(cursor!=null&&(cursor.getCount()>0)){
            //Hemos encontrado el usuario en BBDD y actualizamos el booleano.
            existeUsuario=true;
            Log.d(getClass().getCanonicalName(),"El usuario "+nombreUsuario+" ya existe en la BD");
            //Cerramos el cursor.
            cursor.close();
        }
        //Cerramos la conexión a BD.
        sqLiteDatabase.close();
        return existeUsuario;
    }

    /**
     * Obtenemos la contraseña de un usuario.
     * @param usuario
     * @return
     */
    public String consultaContraseñaUsuario(Usuario usuario){
        String contrasena="";
        String consulta="SELECT contrasena FROM USUARIOS WHERE nombre='"+usuario.getNombre()+"'";
        //Inicializamos la base de datos en modo consulta.
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(consulta,null);
        if(cursor!=null&&(cursor.getCount()>0)){
            cursor.moveToFirst();
            //Recuperamos la contraseña del usuario.
            contrasena=cursor.getString(0);
            //Decodificamos la contraseña almacenada en base de datos.
            contrasena= Codificar.decodifica(contrasena);
            Log.d(getClass().getCanonicalName(),"La contraseña recuperado de BD del usuario "+usuario.getNombre()+"es "+contrasena);
            cursor.close();
        }
        sqLiteDatabase.close();
        return contrasena;
    }


    /**
     * Obtenemos la nombre de un usuario.
     * @param usuario
     * @return
     */
    public String consultaNombreUsuario(Usuario usuario){
        String nombre="";
        String consulta="SELECT nombre FROM USUARIOS WHERE nombre='"+usuario.getNombre()+"'";
        //Inicializamos la base de datos en modo consulta.
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(consulta,null);
        if(cursor!=null&&(cursor.getCount()>0)){
            cursor.moveToFirst();
            //Recuperamos la contraseña del usuario.
            nombre=cursor.getString(0);
            Log.d(getClass().getCanonicalName(),"El nombre recuperado de BD es:"+usuario.getNombre());
            cursor.close();
        }
        sqLiteDatabase.close();
        return nombre;
    }

    /**
     * Obtiene el identificador del usuario a partir del nombre del usuario logeado.
     * @param
     * @return
     */
    public int devuelveIdUsuario(String nombreUsuario){
        int idUsuario=0;
        String consulta="SELECT id FROM USUARIOS WHERE nombre='"+nombreUsuario+"'";
        //Inicializamos la base de datos en modo consulta.
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(consulta,null);
        if(cursor!=null&&(cursor.getCount()>0)){
            cursor.moveToFirst();
            //Recuperamos el identificador del usuario.
            idUsuario=cursor.getInt(0);
            Log.d(getClass().getCanonicalName(),"El identificador de usuario recuperado de BD es"+idUsuario);
            cursor.close();
        }
        //Cerramos la conexión con la BD
        sqLiteDatabase.close();
        return idUsuario;
    }

    public List<Seguimiento> devuelveRegistrosUsuario(int idUsuario){
        List<Seguimiento> listaRegistrosIMC=null;
        String consulta="SELECT imc,textoimc,fecharegistro,idusuario FROM SEGUIMIENTO WHERE idusuario="+idUsuario;
        //Inicializamos la base de datos en modo consulta.
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(consulta,null);
        if(cursor!=null&&cursor.getCount()>0){
            cursor.moveToFirst();
            listaRegistrosIMC= new ArrayList<Seguimiento>();
            String IMC=null;
            String textoIMC=null;
            String fechaRegistro=null;
            int idUsuarioRecuperado=0;
            Seguimiento seguimiento=null;

            do{
                IMC=cursor.getString(0);
                textoIMC=cursor.getString(1);
                fechaRegistro=cursor.getString(2);
                idUsuarioRecuperado=cursor.getInt(3);
                //Con los datos recuperador creamos el objeto Seguimiento.
                seguimiento=new Seguimiento(IMC,textoIMC,fechaRegistro,idUsuarioRecuperado);
                listaRegistrosIMC.add(seguimiento);
            }while(cursor.moveToNext());
            cursor.close();

        }
        //Cerramos la conexión de la base de datos.
       sqLiteDatabase.close();
        return listaRegistrosIMC;
    }

}
