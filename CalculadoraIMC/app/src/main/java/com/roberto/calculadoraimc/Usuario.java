package com.roberto.calculadoraimc;

/**
 * Created by PCCasa on 01/02/2017.
 */

public class Usuario {

    /**
     * Atributo que representa el nombre del usuario.
     */
    private String nombre;

    /**
     * Atributo que representa el valor de la contraseña
     */
    private String contrasena;
    /**
     * Constructor de la clase Usuario.
     * @param nombre
     */
    public Usuario(String nombre,String contrasena) {
        this.nombre = nombre;
        this.contrasena=contrasena;
    }

    /**
     * Devuelve el valor del atributo nombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setea el valor del parámetro en el atributo nombre.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el valor de la contraseña
     * @return
     */

    public String getContrasena() {
        return contrasena;
    }

    /**
     * Setea el valor de la contraseña
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
